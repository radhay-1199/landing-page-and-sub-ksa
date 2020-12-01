package com.gl.combo.bean;

public class UserHits {
	private int hitId;
	private String msisdn;
	private String maskMdn;
	private String ipAddress;
	private String interfacee;
	private int packId;
	public String date;
	private String userAgent;
	private String billerId;
	private String publisher;
	private String userMobileData;
	
	public UserHits() {}
	
	public UserHits(String ipAddress,String interfacee,int packId,String date,String userAgent,String billerId,String publisher){
		this.ipAddress=ipAddress;
		this.interfacee=interfacee;
		this.packId=packId;
		this.date=date;
		this.userAgent=userAgent;
		this.billerId=billerId;
		this.publisher=publisher;
	}

	public int getHitId() {
		return hitId;
	}
	public void setHitId(int hitId) {
		this.hitId = hitId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getMaskMdn() {
		return maskMdn;
	}
	public void setMaskMdn(String maskMdn) {
		this.maskMdn = maskMdn;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getInterfacee() {
		return interfacee;
	}
	public void setInterfacee(String interfacee) {
		this.interfacee = interfacee;
	}
	public int getPackId() {
		return packId;
	}
	public void setPackId(int packId) {
		this.packId = packId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
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
	public String getUserMobileData() {
		return userMobileData;
	}
	public void setUserMobileData(String userMobileData) {
		this.userMobileData = userMobileData;
	}
	
	@Override
	public String toString() {
		return "UserHits [hitId=" + hitId + ", msisdn=" + msisdn + ", maskMdn=" + maskMdn + ", ipAddress=" + ipAddress
				+ ", interfacee=" + interfacee + ", packId=" + packId + ", date=" + date + ", userAgent=" + userAgent
				+ ", billerId=" + billerId + ", publisher=" + publisher + ", userMobileData=" + userMobileData + "]";
	}
	
}

