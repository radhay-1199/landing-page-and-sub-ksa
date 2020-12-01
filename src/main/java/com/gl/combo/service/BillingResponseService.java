package com.gl.combo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.bean.BillingResponse;
import com.gl.combo.bean.Packs;
import com.gl.combo.dao.BillingResponseDao;
import com.gl.combo.dao.PacksDao;



@Service
public class BillingResponseService {
	@Autowired
	BillingResponseDao billingresponsedao;
	
	@Autowired
	PacksDao packs;
	
	public int billingResponseInfo(String msisdn, Integer serviceId, String bearerId, String operationId, String spTransactionId) {
		Packs cp = packs.getPackDetails(serviceId.toString());
		BillingResponse billingResponse=new BillingResponse(msisdn,"CURRENT_TIMESTAMP()",serviceId.toString(),bearerId.toString(),operationId,cp.getProductId(),cp.getPrice(),cp.getDuration(),spTransactionId);
		return billingresponsedao.insertdetails(billingResponse);
	}
}
