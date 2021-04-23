package com.gl.combo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.combo.dao.InAppTransDao;
import com.gl.combo.model.InAppTrans;

@Service
public class InAppTransService {

	@Autowired
	InAppTransDao dao;
	
	public int insertIntoInAppTrans(InAppTrans entity) {
		return dao.insertDetails(entity);
	}
}
