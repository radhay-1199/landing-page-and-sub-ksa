package com.gl.combo.bean;

public class ContentUrlReponseBean {

	public String cpcgFlag;
	public String requestType;
	public String resultCode;
	public String result;
	public String sequenceNumber;
	public String calledParty;
	public String serviceId;
	public String startTime;
	public String getCpcgFlag() {
		return cpcgFlag;
	}
	public void setCpcgFlag(String cpcgFlag) {
		this.cpcgFlag = cpcgFlag;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getCalledParty() {
		return calledParty;
	}
	public void setCalledParty(String calledParty) {
		this.calledParty = calledParty;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public ContentUrlReponseBean(String cpcgFlag, String requestType, String resultCode, String result,
			String sequenceNumber, String calledParty, String serviceId, String startTime) {
		super();
		this.cpcgFlag = cpcgFlag;
		this.requestType = requestType;
		this.resultCode = resultCode;
		this.result = result;
		this.sequenceNumber = sequenceNumber;
		this.calledParty = calledParty;
		this.serviceId = serviceId;
		this.startTime = startTime;
	}
	
	@Override
	public String toString() {
		return "ContentUrlReponseBean [cpcgFlag=" + cpcgFlag + ", requestType=" + requestType + ", resultCode="
				+ resultCode + ", result=" + result + ", sequenceNumber=" + sequenceNumber + ", calledParty="
				+ calledParty + ", serviceId=" + serviceId + ", startTime=" + startTime + "]";
	}
	
}
