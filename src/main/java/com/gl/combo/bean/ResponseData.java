package com.gl.combo.bean;

public class ResponseData {

	public String transactionId;
	public String subscriptionResult;
	public String subsciptionError;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getSubscriptionResult() {
		return subscriptionResult;
	}
	public void setSubscriptionResult(String subscriptionResult) {
		this.subscriptionResult = subscriptionResult;
	}
	public String getSubsciptionError() {
		return subsciptionError;
	}
	public void setSubsciptionError(String subsciptionError) {
		this.subsciptionError = subsciptionError;
	}
	
	@Override
	public String toString() {
		return "ResponseData [transactionId=" + transactionId + ", subscriptionResult=" + subscriptionResult
				+ ", subsciptionError=" + subsciptionError + "]";
	}
	
}
