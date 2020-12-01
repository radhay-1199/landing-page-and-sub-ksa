package com.gl.combo.bean;

public class VerifyPin {

	public String userIdentifier;
	public String userIdentifierType;
	public String productId;
	public String mcc;
	public String mnc;
	public String entryChannel;
	public String clientIp;
	public String transactionAuthCode;
	public String partnerRoleId;
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public String getUserIdentifierType() {
		return userIdentifierType;
	}
	public void setUserIdentifierType(String userIdentifierType) {
		this.userIdentifierType = userIdentifierType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getEntryChannel() {
		return entryChannel;
	}
	public void setEntryChannel(String entryChannel) {
		this.entryChannel = entryChannel;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getTransactionAuthCode() {
		return transactionAuthCode;
	}
	public void setTransactionAuthCode(String transactionAuthCode) {
		this.transactionAuthCode = transactionAuthCode;
	}
	public String getPartnerRoleId() {
		return partnerRoleId;
	}
	public void setPartnerRoleId(String partnerRoleId) {
		this.partnerRoleId = partnerRoleId;
	}
	
	@Override
	public String toString() {
		return "VerifyPin [userIdentifier=" + userIdentifier + ", userIdentifierType=" + userIdentifierType
				+ ", productId=" + productId + ", mcc=" + mcc + ", mnc=" + mnc + ", entryChannel=" + entryChannel
				+ ", clientIp=" + clientIp + ", transactionAuthCode=" + transactionAuthCode + ", partnerRoleId="
				+ partnerRoleId + "]";
	}
	
}
