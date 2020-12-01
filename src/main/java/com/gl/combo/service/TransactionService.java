package com.gl.combo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.bean.Packs;
import com.gl.combo.bean.Transaction;
import com.gl.combo.configuration.Values;
import com.gl.combo.dao.PacksDao;
import com.gl.combo.dao.TransactionDao;



@Service
public class TransactionService {
	
	@Autowired
	TransactionDao transactiondao;
	
	@Autowired
	Values propertiesReader;
	
	@Autowired
	PacksDao packs;
	
	public int saveTransactionInfo(String serviceId) {
		DateTimeFormatter tagDtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime tagNow = LocalDateTime.now();
		String uniqId=tagDtf.format(tagNow);
		Packs cp = packs.getPackDetails(serviceId);
		Transaction transaction=new Transaction(0,uniqId,cp.getPackId(),propertiesReader.getInterfacee(),propertiesReader.getBp(),0,propertiesReader.getPublisher(),"NA",propertiesReader.getProductId(),propertiesReader.getRequestedPrice());
		return transactiondao.insertdetails(transaction);
	}
	public int updateMsisdn(String msisdn,Integer transactionId,String sessionId) {
		Transaction transaction=new Transaction(msisdn,transactionId,sessionId);
		return transactiondao.updateMsisdn(transaction);
	}
	public int updateBillingTransaction(String msisdn,String sessionId) {
		Transaction transaction=new Transaction(msisdn,sessionId);
		return transactiondao.updateBillingTransaction(transaction);
	}
	public int updateBillingTransactionCharge(String msisdn,String serviceId) {
		 transactiondao.updateChargeAmmountAndBillingStatus(msisdn,serviceId);
		 return 1;
	}
	public int updateCgStatus(String sessionId) {
		return transactiondao.updateCgStatusDetails(sessionId);
	}
	public int updateBillingStatusAndResponse(String sessionId) {
		return transactiondao.updateBillingStatusDetails(sessionId);
	}

}
