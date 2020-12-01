package com.gl.combo.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.combo.configuration.Values;
import com.gl.combo.service.UserHitsService;

@Controller
public class Pages {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	UserHitsService userHitsService;
	
	@Autowired
	Values val;
	
	@GetMapping("/")
	public ModelAndView msisdnNoPath(HttpServletRequest request) {
		//user hits table, after msisdn com_transaction table
		String operator="stc";
		String hostName = request.getHeader("User-Agent");
		String hostIp = request.getRemoteAddr();
		int packId=0;
		int key=0;
		if(operator.equals("stc")) {
			packId=70;
		}else if(operator.equals("mobily")) {
			packId=68;
		}else if(operator.equals("zain")) {
			packId=69;
		}
		logger.info("saving user hit details in user hit table IP:" + hostIp + " User Agent:" + hostName+" PackId:"+packId);
		int userHitId = userHitsService.saveUserHitInfo(hostName, hostIp, packId);
		
		ModelAndView mv = new ModelAndView("MSISDNForm");
		if(operator.equals("stc")) {
			key=1;
		}else if (operator.equals("mobily")) {
			key=2;
		}else if(operator.equals("zain")) {
			key=3;
		}
		mv.addObject("key", key);
		return mv;
	}
	
	@GetMapping("/{operator}")
	public ModelAndView msisdn(HttpServletRequest request,@PathVariable String operator) {
		//user hits table, after msisdn com_transaction table
		String hostName = request.getHeader("User-Agent");
		String hostIp = request.getRemoteAddr();
		int packId=0;
		int key=0;
		if(operator.equals("stc")) {
			packId=70;
		}else if(operator.equals("mobily")) {
			packId=68;
		}else if(operator.equals("zain")) {
			packId=69;
		}
		logger.info("saving user hit details in user hit table IP:" + hostIp + " User Agent:" + hostName+" PackId:"+packId);
		int userHitId = userHitsService.saveUserHitInfo(hostName, hostIp, packId);
		
		ModelAndView mv = new ModelAndView("MSISDNForm");
		if(operator.equals("stc")) {
			key=1;
		}else if (operator.equals("mobily")) {
			key=2;
		}else if(operator.equals("zain")) {
			key=3;
		}
		mv.addObject("key", key);
		return mv;
	}

	@GetMapping("otp/form")
	public ModelAndView otp() {
		return new ModelAndView("OTPForm");
	}
	
	@GetMapping("/unsub")
	public ModelAndView unsub() {
		return new ModelAndView("Unsubscribe");
	}
	
	@GetMapping("/check-user")
	public ModelAndView checkUser() {
		return new ModelAndView("check_user");
	}

}
