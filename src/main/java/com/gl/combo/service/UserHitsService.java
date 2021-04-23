package com.gl.combo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.bean.UserHits;
import com.gl.combo.configuration.Values;
import com.gl.combo.dao.UserHitsDao;



@Service
public class UserHitsService {
	@Autowired
	UserHitsDao userhitdao;
	
	@Autowired
	Values propertiesReader;
	
	public int saveUserHitInfo(String hostName,String hostIp,int packId,String bp,String publisher,String interfacee) {
		UserHits userhit = new UserHits(hostIp,interfacee,packId,"CURRENT_TIMESTAMP()",hostName,bp,publisher);
		return userhitdao.insertdetails(userhit);
	}
}
