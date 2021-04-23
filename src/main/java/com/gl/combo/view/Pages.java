package com.gl.combo.view;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.combo.bean.ComTxn;
import com.gl.combo.configuration.Values;
import com.gl.combo.service.TransactionService;
import com.gl.combo.service.UserHitsService;

@Controller
public class Pages {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	UserHitsService userHitsService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	Values val;
	
	@GetMapping("/")
	public ModelAndView msisdnNoPath(HttpServletRequest request) {
		//user hits table, after msisdn com_transaction table
		String operator="stc";
		String hostName = request.getHeader("User-Agent");
		String hostIp = request.getRemoteAddr();
		String bp="";
		int packId=0;
		int key=0;
		int serviceId=0;
		if(operator.equals("stc")) {
			packId=70;
			serviceId=val.getStcServiceId();
			bp=val.getBillerIdStc();
		}else if(operator.equals("mobily")) {
			packId=68;
			serviceId=val.getMobilyServiceId();
			bp=val.getBillerIdMobily();
		}else if(operator.equals("zain")) {
			packId=69;
			serviceId=val.getZainServiceId();
			bp=val.getBillerIdZain();
		}
		logger.info("saving user hit details in user hit table IP:" + hostIp + " User Agent:" + hostName+" PackId:"+packId);
		int userHitId = userHitsService.saveUserHitInfo(hostName, hostIp, packId,bp,val.getPublisher(),val.getInterfacee());
		long response = transactionService.saveTransactionInfo(serviceId,val.getInterfacee(),bp,val.getPublisher(),"1",packId,"LP","");
		
		ModelAndView mv = new ModelAndView("MSISDNForm");
		if(operator.equals("stc")) {
			key=1;
		}else if (operator.equals("mobily")) {
			key=2;
		}else if(operator.equals("zain")) {
			key=3;
		}
		mv.addObject("key", key);
		mv.addObject("interfacee",val.getInterfacee());
		mv.addObject("t_id",response);
		return mv;
	}
	
	@GetMapping("/{operator}")
	public ModelAndView msisdn(HttpServletRequest request,@PathVariable String operator,@RequestParam(name = "bp",defaultValue = "sa") String bp,@RequestParam(name = "publisher",defaultValue = "supercombo") String publisher,@RequestParam(name = "interface",defaultValue = "supercombo_1_daily") String interfacee,String clickId) {
		//user hits table, after msisdn com_transaction table
		String hostName = request.getHeader("User-Agent");
		String hostIp = request.getRemoteAddr();
		int packId=0;
		int key=0;
		int serviceId=0;
		if(operator.equals("stc")) {
			packId=70;
			serviceId=val.getStcServiceId();
			bp=val.getBillerIdStc();
		}else if(operator.equals("mobily")) {
			packId=68;
			serviceId=val.getMobilyServiceId();
			bp=val.getBillerIdMobily();
		}else if(operator.equals("zain")) {
			packId=69;
			serviceId=val.getZainServiceId();
			bp=val.getBillerIdZain();
		}
		logger.info("saving user hit details in user hit table IP:" + hostIp + " User Agent:" + hostName+" PackId:"+packId);
		int userHitId = userHitsService.saveUserHitInfo(hostName, hostIp, packId,bp,publisher,interfacee);
		long response = transactionService.saveTransactionInfo(serviceId,interfacee,bp,publisher,clickId,packId,"LP","");
		logger.info("Transaction Insertion Response: "+response);
		
		ModelAndView mv = new ModelAndView("MSISDNForm");
		if(operator.equals("stc")) {
			key=1;
		}else if (operator.equals("mobily")) {
			key=2;
		}else if(operator.equals("zain")) {
			key=3;
		}
		mv.addObject("key", key);
		mv.addObject("interfacee",interfacee);
		mv.addObject("t_id",response);
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
