package com.gl.combo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.dao.SubscriptionDao;

@Service
public class SubscriptionService {

	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	SubscriptionDao subDao;
	
	public boolean checkSubscriber(String msisdn) {
		logger.info("Inside check subscriber in subscription service");
		Integer count = subDao.checkSubscriber(msisdn);
		if(count>0) {
			return true;
		}else {
			return false;
		}
	}

	public String checkForActiveAndGraceState(String msisdn) {
		logger.info("Inside check active and grace state in subscription service");
		String status = subDao.checkForActiveAndGraceState(msisdn);
		return status;
	}
}
