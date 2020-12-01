package com.gl.combo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.bean.Packs;
import com.gl.combo.bean.SubscribedUsersDetails;
import com.gl.combo.configuration.Values;
import com.gl.combo.dao.PacksDao;
import com.gl.combo.dao.SubscribedUsersDetailsDao;



@Service
public class SubscribedUsersDetailsService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SubscribedUsersDetailsDao subscribedusersdetailsdao;
	
	@Autowired
	Values propertiesReader;
	
	@Autowired
	PacksDao packs;
	
	public int subscribedUsersDetailsInfo(String msisdn,String sessionId) {
		SubscribedUsersDetails subscribedusersdetails=new SubscribedUsersDetails(propertiesReader.getBp(),propertiesReader.getPublisher(),msisdn,0,"CURRENT_TIMESTAMP()","CURRENT_TIMESTAMP()",1,"CURRENT_TIMESTAMP() + INTERVAL 24 HOUR",sessionId,propertiesReader.getProductId());
		return subscribedusersdetailsdao.insertdetails(subscribedusersdetails);
	}
	
	public int unsubscribedUsersDetailsInfo(String msisdn) {
		return subscribedusersdetailsdao.updateDetails(msisdn,0);
	}
	
	public int suspendAndUnsuspendUser(String msisdn,int status) {
		return subscribedusersdetailsdao.suspend(msisdn,status);
	}
	
	public int updateRenewalInfo(String msisdn, String serviceId) {
		Packs cp = packs.getPackDetails(serviceId);
		String validity = cp.getDuration();
		logger.info("inserting into com_subscribed_users_details for renewal");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDate now = LocalDate.now().plusDays(Long.parseLong(validity));
		LocalDate nextDate = LocalDate.now().plusDays(Long.parseLong(validity+1));
		return subscribedusersdetailsdao.updateDetailsDaily(msisdn,dtf.format(now),dtf.format(nextDate));
	}
}
