package com.gl.combo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.combo.bean.Packs;
import com.gl.combo.configuration.Values;
import com.gl.combo.dao.PacksDao;
import com.gl.combo.feign.Feign;
import com.gl.combo.model.CheckSubscription;
import com.gl.combo.model.InAppTrans;
import com.gl.combo.model.SendFreeMt;
import com.gl.combo.model.SendPincode;
import com.gl.combo.model.SubscribeWithPincode;
import com.gl.combo.model.Unsubscribe;
import com.gl.combo.service.BillingResponseService;
import com.gl.combo.service.InAppTransService;
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
	
	@Autowired
	InAppTransService inAppTransService;
	
	@Autowired
	PacksDao packs;
	
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
			String bp="";
			if(service_connection_id == val.getStcServiceId()) {
				bp=val.getBillerIdStc();
			}else if(service_connection_id == val.getMobilyServiceId()) {
				bp=val.getBillerIdMobily();
			}else if(service_connection_id == val.getZainServiceId()) {
				bp=val.getBillerIdZain();
			}
			if(action.equals("sub")) {
				//free trial mobily
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
				LocalDateTime now = LocalDateTime.now();
				if(service_connection_id == val.getMobilyServiceId()) {
					subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, notification_id,bp,dtf.format(now.plusDays(3)),"NA");
					transactionService.updateBillingTransactionChargeForMobily(msisdn, Integer.toString(service_connection_id),"DONE");
				}
				else if(service_connection_id == val.getZainServiceId())
				{
					subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, notification_id,bp,dtf.format(now),"NA");
					transactionService.updateBillingTransactionChargeForZain(msisdn, Integer.toString(service_connection_id),"DONE");
				}
				else {
					
					subscribedUsersDetailsService.subscribedUsersDetailsInfo(msisdn, notification_id,bp,dtf.format(now.plusDays(1)),dtf.format(now));
					transactionService.updateBillingTransactionCharge(msisdn, Integer.toString(service_connection_id),"DONE");
					billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "SN", notification_id,bp,"ACT");
				}
				//send content
				this.sendFreeMt(msisdn, service_connection_id);
			}else if(action.equals("unsub")) {
				subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn,service_connection_id);
				billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "SS", notification_id,bp,"DCT");
			}else if(action.equals("renewal")) {
				billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "RR", notification_id,bp,"REN");
				//transactionService.updateBillingTransactionCharge(msisdn, Integer.toString(service_connection_id));
				subscribedUsersDetailsService.updateRenewalInfo(msisdn, Integer.toString(service_connection_id));
			}else if(action.equals("deactivated")) {
				if(service_connection_id == val.getStcServiceId()) {
					subscribedUsersDetailsService.unsubscribedUsersDetailsInfo(msisdn,service_connection_id);
				}else if(service_connection_id == val.getMobilyServiceId() || service_connection_id == val.getZainServiceId()) {
					subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,0,service_connection_id);
				}	
			}else if(action.equals("suspend")) {
				subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,0,service_connection_id);
			}else if(action.equals("unsuspend")) {
				subscribedUsersDetailsService.suspendAndUnsuspendUser(msisdn,1,service_connection_id);
				billingResponseService.billingResponseInfo(msisdn, service_connection_id, user_id, "RR", notification_id,bp,"REN");
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
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
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
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
	
	@GetMapping("app/send_pin")
	public ResponseEntity<?> sendPinApp(@RequestParam String msisdn,@RequestParam int key,@RequestParam(name = "interface",defaultValue = "supercombo_1_daily") String interfacae){
		logger.info("Inside in app send_pin request");
		logger.info("Request to send pincode controller: MSISDN=>"+msisdn+" | key=>"+key);
		
		String bp="";
		if(key==1) {
			bp=val.getBillerIdStc();
		}else if(key==2) {
			bp=val.getBillerIdMobily();
		}else if(key==3) {
			bp=val.getBillerIdZain();
		}
		
		try {
			//1 for stc  2 for mobily  3 for zain
			if(key == 1) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getStcServiceId(), 0);
				Packs cp = packs.getPackDetails(val.getStcServiceId());
				//msisdn com_txn
				long response = transactionService.saveTransactionInfo(val.getStcServiceId(),interfacae,val.getBillerIdStc(),val.getPublisher(),"0",cp.getPackId(),"app",msisdn);
			}else if(key == 2) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getMobilyServiceId(), 0);
				Packs cp = packs.getPackDetails(val.getMobilyServiceId());
				long response = transactionService.saveTransactionInfo(val.getMobilyServiceId(),interfacae,val.getBillerIdMobily(),val.getPublisher(),"0",cp.getPackId(),"app",msisdn);
			}else if(key == 3) {
				sendPincode = feign.sendPincode(val.getApiKey(), msisdn, val.getZainServiceId(), 0);
				Packs cp = packs.getPackDetails(val.getZainServiceId());
				long response = transactionService.saveTransactionInfo(val.getZainServiceId(),interfacae,val.getBillerIdZain(),val.getPublisher(),"0",cp.getPackId(),"app",msisdn);
			}
			
			Optional<SendPincode> optional = Optional.ofNullable(sendPincode);
			if(optional.isPresent()) {
				logger.info("Response from send pincode controller: "+sendPincode);
				logger.info("Response To Json: "+new Gson().toJson(sendPincode));
				inAppTransService.insertIntoInAppTrans(new InAppTrans("send_pin", msisdn, interfacae,new Gson().toJson(sendPincode),bp,sendPincode.getCode()));
				return new ResponseEntity<>(sendPincode, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in send pincode controller: "+e.getMessage());
			inAppTransService.insertIntoInAppTrans(new InAppTrans("send_pin", msisdn, interfacae,e.getMessage(),bp,0));
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(Optional.empty(), HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping("app/verify_pin")
	public ResponseEntity<?> verifyPinApp(@RequestParam String msisdn,@RequestParam int key,@RequestParam String pincode,@RequestParam(name = "interface",defaultValue = "supercombo_1_daily") String interfacae){
		logger.info("Inside in app verify_pin request");
		logger.info("Request to verify pin controller: MSISDN=>"+msisdn+" | key=>"+key+" | pincode=>"+pincode);
		
		String bp="";
		if(key==1) {
			bp=val.getBillerIdStc();
		}else if(key==2) {
			bp=val.getBillerIdMobily();
		}else if(key==3) {
			bp=val.getBillerIdZain();
		}
		
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
				inAppTransService.insertIntoInAppTrans(new InAppTrans("verify_pin", msisdn, interfacae,new Gson().toJson(subscribeWithPincode),bp,subscribeWithPincode.getCode()));
				return new ResponseEntity<>(subscribeWithPincode, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in verify pincode controller: "+e.getMessage());
			inAppTransService.insertIntoInAppTrans(new InAppTrans("verify_pin", msisdn, interfacae,e.getMessage(),bp,0));
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
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
	
	@GetMapping("/comTxn") 
	public ResponseEntity<?> userHit(@RequestParam String tid,@RequestParam String msisdn){
		logger.info("inside comTxn controller: TransId=>"+tid);
		int response = transactionService.updateMsisdn(tid, msisdn);
		return new ResponseEntity<>("Ok", HttpStatus.OK);	
	}
	/*
	 * @GetMapping("/test") public ResponseEntity<?> test(){
	 * sendFreeMt("966548979064", val.getMobilyServiceId()); return new
	 * ResponseEntity<>("OK", HttpStatus.OK); }
	 */
}
