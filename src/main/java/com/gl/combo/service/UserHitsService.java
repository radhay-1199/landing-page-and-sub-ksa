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
	
	public int saveUserHitInfo(String hostName,String hostIp,int packId) {
		UserHits userhit = new UserHits(hostIp,propertiesReader.getInterfacee(),packId,"CURRENT_TIMESTAMP()",hostName,propertiesReader.getBp(),propertiesReader.getPublisher());
		return userhitdao.insertdetails(userhit);
	}
}
