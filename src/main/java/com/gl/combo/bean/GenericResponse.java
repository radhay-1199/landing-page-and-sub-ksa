package com.gl.combo.bean;

public class GenericResponse {
	
	public String message;
	public String inError;
	public String requestId;
	public String code;
	public ResponseData reponseData;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getInError() {
		return inError;
	}
	public void setInError(String inError) {
		this.inError = inError;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ResponseData getReponseData() {
		return reponseData;
	}
	public void setReponseData(ResponseData reponseData) {
		this.reponseData = reponseData;
	}
	
	@Override
	public String toString() {
		return "MsisdnResponse [message=" + message + ", inError=" + inError + ", requestId=" + requestId + ", code="
				+ code + ", reponseData=" + reponseData + "]";
	}
	
}
