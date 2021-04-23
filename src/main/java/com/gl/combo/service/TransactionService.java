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
	
	public long saveTransactionInfo(int serviceId,String interfacee,String bp,String publisher,String clickId,int packId,String userType,String msisdn) {
		DateTimeFormatter tagDtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
		LocalDateTime tagNow = LocalDateTime.now();
		String uniqId=tagDtf.format(tagNow);
		//Packs cp = packs.getPackDetails(serviceId);
		Transaction transaction=new Transaction(0,uniqId,packId,interfacee,bp,0,publisher,clickId,propertiesReader.getProductId(),propertiesReader.getRequestedPrice());
		return transactiondao.insertdetails(transaction,userType,msisdn);
	}
	public int updateMsisdn(String transId,String msisdn) {
		return transactiondao.updateMsisdn(transId,msisdn);
	}
	public int updateMsisdn(String msisdn,Integer transactionId,String sessionId) {
		Transaction transaction=new Transaction(msisdn,transactionId,sessionId);
		return transactiondao.updateMsisdn(transaction);
	}
	public int updateBillingTransaction(String msisdn,String sessionId) {
		Transaction transaction=new Transaction(msisdn,sessionId);
		return transactiondao.updateBillingTransaction(transaction);
	}
	public int updateBillingTransactionCharge(String msisdn,String serviceId,String billingStatus) {
		 int count=transactiondao.updateChargeAmmountAndBillingStatus(msisdn,serviceId,billingStatus);
		 return count;
	}
	public int updateBillingTransactionChargeForMobily(String msisdn,String serviceId,String billingStatus) {
		 int count=transactiondao.updateChargeAmmountAndBillingStatusForMobily(msisdn,serviceId,billingStatus);
		 return count;
	}
	public int updateBillingTransactionChargeForZain(String msisdn,String serviceId,String billingStatus) {
		 int count=transactiondao.updateChargeAmmountAndBillingStatusForZain(msisdn,serviceId,billingStatus);
		 return count;
	}
	public int updateCgStatus(String sessionId) {
		return transactiondao.updateCgStatusDetails(sessionId);
	}
	public int updateBillingStatusAndResponse(String sessionId) {
		return transactiondao.updateBillingStatusDetails(sessionId);
	}

}
