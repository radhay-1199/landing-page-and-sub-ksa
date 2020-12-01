package com.gl.combo.bean;

public class Transaction {
	private int transactionId;
	private int subscribedUserId;
	private String msisdn;
	private String type;
	private int packId;
	private String productId;
	private String transactionTime;
	private String transactionUniqueId;
	private float requestedPrice;
	private float priceDeducted;
	private String cgResponseId;
	private String cgStatus;
	private String cgStatusCode;
	private String cgResponseTime;
	private String billingStatus;
	private String billingResponseTime;
	private String billingResponse;
	private String billingId;
	private String browserCode;
	private String interfacee;
	private String adnetworkId;
	private int callbackStatus;
	private String billerId;
	private int hitId;
	private String circleName;
	private String publisher;
	private int contentId;
	private int sameDayChurn;
	private String v3mobiMsisdn;
	private String appUserId;
	private String userType;
	private int process;
	private int consent;
	private String pubId;
	private String sessionId;
	
	public Transaction() {}
	
	public Transaction(int subscribedUserId,String transactionUniqueId,int packId,String interfacee,String billerId,int hitId,String publisher,String clickId,String productId,float requestedPrice) {
		this.subscribedUserId=subscribedUserId;
		this.transactionUniqueId=transactionUniqueId;
		this.packId=packId;
		this.interfacee=interfacee;
		this.billerId=billerId;
		this.hitId=hitId;
		this.publisher=publisher;
		this.adnetworkId=clickId;
		this.productId=productId;
		this.requestedPrice=requestedPrice;
	}
	
	public Transaction(String msisdn,Integer transactionId,String sessionId) {
		this.msisdn=msisdn;
		this.transactionId=transactionId;
	}
	
	public Transaction(String msisdn, String sessionId) {
		this.msisdn=msisdn;
		this.sessionId=sessionId;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getSubscribedUserId() {
		return subscribedUserId;
	}
	public void setSubscribedUserId(int subscribedUserId) {
		this.subscribedUserId = subscribedUserId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPackId() {
		return packId;
	}
	public void setPackId(int packId) {
		this.packId = packId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getTransactionUniqueId() {
		return transactionUniqueId;
	}
	public void setTransactionUniqueId(String transactionUniqueId) {
		this.transactionUniqueId = transactionUniqueId;
	}
	public float getRequestedPrice() {
		return requestedPrice;
	}
	public void setRequestedPrice(float requestedPrice) {
		this.requestedPrice = requestedPrice;
	}
	public float getPriceDeducted() {
		return priceDeducted;
	}
	public void setPriceDeducted(float priceDeducted) {
		this.priceDeducted = priceDeducted;
	}
	public String getCgResponseId() {
		return cgResponseId;
	}
	public void setCgResponseId(String cgResponseId) {
		this.cgResponseId = cgResponseId;
	}
	public String getCgStatus() {
		return cgStatus;
	}
	public void setCgStatus(String cgStatus) {
		this.cgStatus = cgStatus;
	}
	public String getCgStatusCode() {
		return cgStatusCode;
	}
	public void setCgStatusCode(String cgStatusCode) {
		this.cgStatusCode = cgStatusCode;
	}
	public String getCgResponseTime() {
		return cgResponseTime;
	}
	public void setCgResponseTime(String cgResponseTime) {
		this.cgResponseTime = cgResponseTime;
	}
	public String getBillingStatus() {
		return billingStatus;
	}
	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}
	public String getBillingResponseTime() {
		return billingResponseTime;
	}
	public void setBillingResponseTime(String billingResponseTime) {
		this.billingResponseTime = billingResponseTime;
	}
	public String getBillingResponse() {
		return billingResponse;
	}
	public void setBillingResponse(String billingResponse) {
		this.billingResponse = billingResponse;
	}
	public String getBillingId() {
		return billingId;
	}
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}
	public String getBrowserCode() {
		return browserCode;
	}
	public void setBrowserCode(String browserCode) {
		this.browserCode = browserCode;
	}
	public String getInterfacee() {
		return interfacee;
	}
	public void setInterfacee(String interfacee) {
		this.interfacee = interfacee;
	}
	public String getAdnetworkId() {
		return adnetworkId;
	}
	public void setAdnetworkId(String adnetworkId) {
		this.adnetworkId = adnetworkId;
	}
	public int getCallbackStatus() {
		return callbackStatus;
	}
	public void setCallbackStatus(int callbackStatus) {
		this.callbackStatus = callbackStatus;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public int getHitId() {
		return hitId;
	}
	public void setHitId(int hitId) {
		this.hitId = hitId;
	}
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getSameDayChurn() {
		return sameDayChurn;
	}
	public void setSameDayChurn(int sameDayChurn) {
		this.sameDayChurn = sameDayChurn;
	}
	public String getV3mobiMsisdn() {
		return v3mobiMsisdn;
	}
	public void setV3mobiMsisdn(String v3mobiMsisdn) {
		this.v3mobiMsisdn = v3mobiMsisdn;
	}
	public String getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getConsent() {
		return consent;
	}
	public void setConsent(int consent) {
		this.consent = consent;
	}
	public String getPubId() {
		return pubId;
	}
	public void setPubId(String pubId) {
		this.pubId = pubId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", subscribedUserId=" + subscribedUserId + ", msisdn="
				+ msisdn + ", type=" + type + ", packId=" + packId + ", productId=" + productId + ", transactionTime="
				+ transactionTime + ", transactionUniqueId=" + transactionUniqueId + ", requestedPrice="
				+ requestedPrice + ", priceDeducted=" + priceDeducted + ", cgResponseId=" + cgResponseId + ", cgStatus="
				+ cgStatus + ", cgStatusCode=" + cgStatusCode + ", cgResponseTime=" + cgResponseTime
				+ ", billingStatus=" + billingStatus + ", billingResponseTime=" + billingResponseTime
				+ ", billingResponse=" + billingResponse + ", billingId=" + billingId + ", browserCode=" + browserCode
				+ ", interfacee=" + interfacee + ", adnetworkId=" + adnetworkId + ", callbackStatus=" + callbackStatus
				+ ", billerId=" + billerId + ", hitId=" + hitId + ", circleName=" + circleName + ", publisher="
				+ publisher + ", contentId=" + contentId + ", sameDayChurn=" + sameDayChurn + ", v3mobiMsisdn="
				+ v3mobiMsisdn + ", appUserId=" + appUserId + ", userType=" + userType + ", process=" + process
				+ ", consent=" + consent + ", pubId=" + pubId + ", sessionId=" + sessionId + "]";
	}
	
}
