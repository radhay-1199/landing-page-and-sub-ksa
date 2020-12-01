package com.gl.combo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.combo.model.CheckSubscription;
import com.gl.combo.model.SendFreeMt;
import com.gl.combo.model.SendPincode;
import com.gl.combo.model.SubscribeWithPincode;
import com.gl.combo.model.Unsubscribe;

@Service
@FeignClient(url = "${operatorFeignPath}", value = "operator")
public interface Feign {

	@GetMapping(value="/users.check_subscription", produces = "application/json")
	public CheckSubscription checkSubscription(@RequestParam(value="api_key") String apiKey,@RequestParam(value="msisdn") String msisdn,@RequestParam(value="service_connection_id") int serviceConnecitonId);
	
	@GetMapping(value="/users.send_pincode", produces = "application/json")	
	public SendPincode sendPincode(@RequestParam(value="api_key") String apiKey,@RequestParam(value="msisdn") String msisdn,@RequestParam(value="service_connection_id") int serviceConnecitonId,@RequestParam(value="async") int async);
	
	@GetMapping(value="/users.subscribe_pincode", produces = "application/json")
	public SubscribeWithPincode subWithPin(@RequestParam(value="api_key") String apiKey,@RequestParam(value="msisdn") String msisdn,@RequestParam(value="service_connection_id") int serviceConnecitonId,@RequestParam(value="pincode") String pincode);
	
	@GetMapping(value="/users.unsub_user", produces = "application/json")
	public Unsubscribe unsub(@RequestParam(value="api_key") String apiKey,@RequestParam(value="msisdn") String msisdn,@RequestParam(value="service_connection_id") int serviceConnecitonId);
	
	@GetMapping(value="/users.send_mt", produces = "application/json")
	public SendFreeMt sendMt(@RequestParam(value="api_key") String apiKey,@RequestParam(value="msisdn") String msisdn,@RequestParam(value="service_connection_id") int serviceConnecitonId,@RequestParam(value="message") String message);
	
	
}
