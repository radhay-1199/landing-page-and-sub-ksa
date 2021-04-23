package com.gl.combo.model;

import org.springframework.stereotype.Component;

@Component
public class InAppTrans {

	public String action;
	public String msisdn;
	public String interfacee;
	public String response;
	public String billerId;
	public int code;
	public InAppTrans() {
		super();
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getInterfacee() {
		return interfacee;
	}
	public void setInterfacee(String interfacee) {
		this.interfacee = interfacee;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public InAppTrans(String action, String msisdn, String interfacee, String response, String billerId, int code) {
		super();
		this.action = action;
		this.msisdn = msisdn;
		this.interfacee = interfacee;
		this.response = response;
		this.billerId = billerId;
		this.code = code;
	}
	@Override
	public String toString() {
		return "InAppTrans [action=" + action + ", msisdn=" + msisdn + ", interfacee=" + interfacee + ", response="
				+ response + ", billerId=" + billerId + ", code=" + code + "]";
	}
	
}
