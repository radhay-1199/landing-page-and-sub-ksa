package com.gl.combo.bean;

public class SubscribedUsersDetails {
	  private int detailId;
	  private String billerId;
	  private String publisher;
	  private String msisdn;
	  private int packId;
	  private String productId;
	  private int downloadCount;
	  private int downloadLimit;
	  private String subscriptionDate;
	  private String unsubscriptionDate;
	  private int status;
	  private String expDate;
	  private String renewalDate;
	  private String lastStatus;
	  private String nextRenewalDate;
	  private String sessionId;
	  private String billedDate;
	  
	  public SubscribedUsersDetails() {}
	  
	  public SubscribedUsersDetails(String billerId,String publisher,String msisdn,int packId,String subscriptionDate,String unsubscriptionDate,int status,String expDate,String sessionId,String productId,String billedDate) {
		  this.billerId=billerId;
		  this.publisher=publisher;
		  this.msisdn=msisdn;
		  this.packId=packId;
		  this.subscriptionDate=subscriptionDate;
		  this.unsubscriptionDate=unsubscriptionDate;
		  this.status=status;
		  this.expDate=expDate;
		  this.sessionId=sessionId;
		  this.productId=productId;
		  this.billedDate=billedDate;
	  }

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getBillerId() {
		return billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public int getDownloadLimit() {
		return downloadLimit;
	}

	public void setDownloadLimit(int downloadLimit) {
		this.downloadLimit = downloadLimit;
	}

	public String getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getUnsubscriptionDate() {
		return unsubscriptionDate;
	}

	public void setUnsubscriptionDate(String unsubscriptionDate) {
		this.unsubscriptionDate = unsubscriptionDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getNextRenewalDate() {
		return nextRenewalDate;
	}

	public void setNextRenewalDate(String nextRenewalDate) {
		this.nextRenewalDate = nextRenewalDate;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getBilledDate() {
		return billedDate;
	}

	public void setBilledDate(String billedDate) {
		this.billedDate = billedDate;
	}

	@Override
	public String toString() {
		return "SubscribedUsersDetails [detailId=" + detailId + ", billerId=" + billerId + ", publisher=" + publisher
				+ ", msisdn=" + msisdn + ", packId=" + packId + ", productId=" + productId + ", downloadCount="
				+ downloadCount + ", downloadLimit=" + downloadLimit + ", subscriptionDate=" + subscriptionDate
				+ ", unsubscriptionDate=" + unsubscriptionDate + ", status=" + status + ", expDate=" + expDate
				+ ", renewalDate=" + renewalDate + ", lastStatus=" + lastStatus + ", nextRenewalDate=" + nextRenewalDate
				+ ", sessionId=" + sessionId + ", billedDate=" + billedDate + "]";
	}
	  	  
}
