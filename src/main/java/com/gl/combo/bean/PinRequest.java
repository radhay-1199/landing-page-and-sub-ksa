package com.gl.combo.bean;

public class PinRequest {

	//optional param
	public String userIdentifier;
	public String userIdentifierType;
	public String productId;
	public Integer mcc;
	public Integer mnc;
	public String entryChannel;
	public String largeAccount;
	public String subKeyword;
	public String trackingId;
	public String clientIp;
	public String campaignUrl;
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
	public Integer getMcc() {
		return mcc;
	}
	public void setMcc(Integer mcc) {
		this.mcc = mcc;
	}
	public Integer getMnc() {
		return mnc;
	}
	public void setMnc(Integer mnc) {
		this.mnc = mnc;
	}
	public String getEntryChannel() {
		return entryChannel;
	}
	public void setEntryChannel(String entryChannel) {
		this.entryChannel = entryChannel;
	}
	public String getLargeAccount() {
		return largeAccount;
	}
	public void setLargeAccount(String largeAccount) {
		this.largeAccount = largeAccount;
	}
	public String getSubKeyword() {
		return subKeyword;
	}
	public void setSubKeyword(String subKeyword) {
		this.subKeyword = subKeyword;
	}
	public String getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getCampaignUrl() {
		return campaignUrl;
	}
	public void setCampaignUrl(String campaignUrl) {
		this.campaignUrl = campaignUrl;
	}
	public String getPartnerRoleId() {
		return partnerRoleId;
	}
	public void setPartnerRoleId(String partnerRoleId) {
		this.partnerRoleId = partnerRoleId;
	}
	
	@Override
	public String toString() {
		return "PinRequest [userIdentifier=" + userIdentifier + ", userIdentifierType=" + userIdentifierType
				+ ", productId=" + productId + ", mcc=" + mcc + ", mnc=" + mnc + ", entryChannel=" + entryChannel
				+ ", largeAccount=" + largeAccount + ", subKeyword=" + subKeyword + ", trackingId=" + trackingId
				+ ", clientIp=" + clientIp + ", campaignUrl=" + campaignUrl + ", partnerRoleId=" + partnerRoleId + "]";
	}
	
}
