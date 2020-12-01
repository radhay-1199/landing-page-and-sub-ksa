package com.gl.combo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.dao.OtpVerificationDao;

@Service
public class OtpVerificationService {

	@Autowired
	OtpVerificationDao otpVerificationDao;
	
	public void insertDetails(String msisdn, String authCode, String status,String productId) {
		otpVerificationDao.insertInTable(msisdn,authCode,status,productId);
	}

}
