package com.gl.combo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.combo.bean.ComTxn;
import com.gl.combo.configuration.Values;
import com.gl.combo.feign.Feign;
import com.gl.combo.model.CheckSubscription;
import com.gl.combo.model.SendFreeMt;
import com.gl.combo.model.SendPincode;
import com.gl.combo.model.SubscribeWithPincode;
import com.gl.combo.model.Unsubscribe;
import com.gl.combo.service.BillingResponseService;
import com.gl.combo.service.SubscribedUsersDetailsService;
import com.gl.combo.service.TransactionService;
import com.google.gson.Gson;

@RestController
@CrossOrigin
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired
	Values val;
	
	@Autowired
	Feign feign;
	
	@Autowired
	CheckSubscription checkSubscription;
	
	@Autowired
	SendPincode sendPincode;
	
	@Autowired
	SubscribeWithPincode subscribeWithPincode;
	
	@Autowired
	Unsubscribe unsubscribe;
	
	@Autowired
	SendFreeMt sendFreeMt;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	SubscribedUsersDetailsService subscribedUsersDetailsService;
	
	@Autowired
	BillingResponseService billingResponseService;
	
	@GetMapping("/notification")
	public ResponseEntity<?> notificationForward(@RequestParam int service_connection_id, @RequestParam String msisdn, @RequestParam String user_id, @RequestParam String notification_id, @RequestParam String notification_time, @RequestParam String action){
		logger.info("Request to notification forward controller: msisdn=>"+msisdn+" | service_connection_id=>"+service_connection_id+" | user_id=>"+user_id+" | notification_id=>"+notification_id+" | notification_time=>"+notification_time+" | action=>"+action);
		String notificationTime= null;
		try {
			notificationTime = URLDecoder.decode(notification_time, StandardCharsets.UTF_8.toString());
			//com transaction, com_subscriber, com_billing
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception in Main Controller while decoding notification_time");
			e.printStackTrace();
		}
		logger.info("service_connection_id: "+service_connection_id+" | msisdn: "+msisdn+" | user_id: "+user_id+" | notification_id: "+notification_id+" | notification_time: "+notification_time+" | notificationTime(decoded): "+notificationTime+" | action: "+action);
		try {
			if(action.equals("sub")) {
				subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, notification_id);
				transactionService.updateBillingTransactionCharge(msisdn, Integer.toString(service_connection_id));
				billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "SN", notification_id);
				//send content
				this.sendFreeMt(msisdn, service_connection_id);
			}else if(action.equals("unsub")) {
				subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn);
			}else if(action.equals("renewal")) {
				billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "RR", notification_id);
				subscribedUsersDetailsService.updateRenewalInfo(msisdn, Integer.toString(service_connection_id));
			}else if(action.equals("deactivated")) {
				if(service_connection_id == val.getStcServiceId()) {
					subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn);
				}else if(service_connection_id == val.getMobilyServiceId() || service_connection_id == val.getZainServiceId()) {
					subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,0);
				}
			}else if(action.equals("suspend")) {
				subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,0);
			}else if(action.equals("unsuspend")) {
				subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,1);
			}else if(action.equals("first_charge")) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in Notification Forward in Main Controller: "+e);
			e.printStackTrace();
		}
		return new ResponseEntity("OK", HttpStatus.OK);
	}
	
	@GetMapping("/check_sub")
	public ResponseEntity<?> checkSubscription(@RequestParam String msisdn,@RequestParam int key){	
		logger.info("Request to check subscription controller: MSISDN=>"+msisdn+" | key=>"+key);
		try {
			//1 for stc  2 for mobily  3 for zain
			if(key == 1) {
				checkSubscription = feign.checkSubscription(val.getApiKey(), msisdn, val.getStcServiceId());
			}else if(key == 2) {
				checkSubscription = feign.checkSubscription(val.getApiKey(), msisdn, val.getMobilyServiceId());
			}else if(key == 3) {
				checkSubscription = feign.checkSubscription(val.getApiKey(), msisdn, val.getZainServiceId());
			}
			
			Optional<CheckSubscription> optional = Optional.ofNullable(checkSubscription);
			if(optional.isPresent()) {
				logger.info("Reponse from check subscriptional API: "+checkSubscription);
				logger.info("User Subscription Status: "+checkSubscription.getData().isSubscribed());
				logger.info("Response To Json: "+new Gson().toJson(checkSubscription));
				return new ResponseEntity<>(checkSubscription, HttpStatus.OK);
			}	
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in check subscription controller: "+e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(Optional.empty(), HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("/send_pincode")
	public ResponseEntity<?> sendPincode(@RequestParam String msisdn,@RequestParam int key){
		logger.info("Request to send pincode controller: MSISDN=>"+msisdn+" | key=>"+key);
		try {
			//1 for stc  2 for mobily  3 for zain
			if(key == 1) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getStcServiceId(), 0);
			}else if(key == 2) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getMobilyServiceId(), 0);
			}else if(key == 3) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getZainServiceId(), 0);
			}
			
			Optional<SendPincode> optional = Optional.ofNullable(sendPincode);
			if(optional.isPresent()) {
				logger.info("Response from send pincode controller: "+sendPincode);
				logger.info("Response To Json: "+new Gson().toJson(sendPincode));
				return new ResponseEntity<>(sendPincode, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in send pincode controller: "+e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(Optional.empty(), HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("/verify_pin")
	public ResponseEntity<?> verifyPin(@RequestParam String msisdn,@RequestParam int key,@RequestParam String pincode){
		logger.info("Request to verify pin controller: MSISDN=>"+msisdn+" | key=>"+key+" | pincode=>"+pincode);
		try {
			//1 for stc  2 for mobily  3 for zain
			if(key == 1) {
				subscribeWithPincode = feign.subWithPin(val.getApiKey(), msisdn, val.getStcServiceId(), pincode);
			}else if(key == 2) {
				subscribeWithPincode = feign.subWithPin(val.getApiKey(), msisdn, val.getMobilyServiceId(), pincode);
			}else if(key == 3) {
				subscribeWithPincode = feign.subWithPin(val.getApiKey(), msisdn, val.getZainServiceId(), pincode);
			}
			
			Optional<SubscribeWithPincode> optional = Optional.ofNullable(subscribeWithPincode);
			if(optional.isPresent()) {
				logger.info("Response from verify pincode controller: "+subscribeWithPincode);
				logger.info("Response To Json: "+new Gson().toJson(subscribeWithPincode));
				return new ResponseEntity<>(subscribeWithPincode, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in verify pincode controller: "+e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(Optional.empty(), HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("/unsubscribe")
	public ResponseEntity<?> unsub(@RequestParam String msisdn,@RequestParam int key){
		logger.info("Request to check subscription controller: MSISDN=>"+msisdn+" | key=>"+key);
		try {
			//1 for stc  2 for mobily  3 for zain
			if(key == 1) {
				unsubscribe = feign.unsub(val.getApiKey(), msisdn, val.getStcServiceId());
			}else if(key == 2) {
				unsubscribe = feign.unsub(val.getApiKey(), msisdn, val.getMobilyServiceId());
			}else if(key == 3) {
				unsubscribe = feign.unsub(val.getApiKey(), msisdn, val.getZainServiceId());
			}
			
			Optional<Unsubscribe> optional = Optional.ofNullable(unsubscribe);
			if(optional.isPresent()) {
				logger.info("Response from unsubscribe controller: "+unsubscribe);
				logger.info("Response To Json: "+new Gson().toJson(unsubscribe));
				return new ResponseEntity<>(unsubscribe, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in unsubscribe controller: "+e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(Optional.empty(), HttpStatus.EXPECTATION_FAILED);
	}
	
	public void sendFreeMt(String msisdn,int serviceId){
		logger.info("Request to check subscription controller: MSISDN=>"+msisdn+" | serviceId=>"+serviceId);
		try {
			String tokenId = Base64.getEncoder().encodeToString(msisdn.getBytes());
			logger.info("Encoded msisdn: "+tokenId);
			String message=val.getContentUrl();
			String finalMessage = message.replaceAll("<TOKEN>", tokenId);
			logger.info("Content Url for the msisdn: "+finalMessage);
			String ecodedMessage = URLEncoder.encode(finalMessage, StandardCharsets.UTF_8.name());
			logger.info("Encoded Content Url for the msisdn: "+finalMessage);
			//1 for stc  2 for mobily  3 for zain
			if(serviceId == val.getStcServiceId()) {
				sendFreeMt = feign.sendMt(val.getApiKey(), msisdn, val.getStcServiceId(), finalMessage);
			}else if(serviceId == val.getMobilyServiceId()) {
				sendFreeMt = feign.sendMt(val.getApiKey(), msisdn, val.getMobilyServiceId(), finalMessage);
			}else if(serviceId == val.getZainServiceId()) {
				sendFreeMt = feign.sendMt(val.getApiKey(), msisdn, val.getZainServiceId(), finalMessage);
			}
			
			Optional<SendFreeMt> optional = Optional.ofNullable(sendFreeMt);
			if(optional.isPresent()) {
				logger.info("Response from send content controller: "+sendFreeMt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in send content controller: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@PostMapping("/comTxn") 
	public ResponseEntity<?> userHit(@RequestBody ComTxn comTxn){
		logger.info("inside comTxn controller: "+comTxn);
		int response = transactionService.saveTransactionInfo(comTxn.getServiceID());
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	/*
	 * @GetMapping("/test") public ResponseEntity<?> test(){
	 * sendFreeMt("966548979064", val.getMobilyServiceId()); return new
	 * ResponseEntity<>("OK", HttpStatus.OK); }
	 */
}
