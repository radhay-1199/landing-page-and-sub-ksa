package com.gl.combo.model;

import org.springframework.stereotype.Component;

@Component
public class UserHits {
private int hitID;
private String msisdn;
private String maskMdn;
private String ipAddress;
private String interfaceMedium;
private Integer packID;
private String date;
private String userAgent;
private String billerID;
private String publisher;
private String usermobiledata;

public UserHits() {
	super();
}

public UserHits(int hitID, String msisdn, String maskMdn, String ipAddress, String interfaceMedium, Integer packID,
		String date, String userAgent, String billerID, String publisher, String usermobiledata) {
	super();
	this.hitID = hitID;
	this.msisdn = msisdn;
	this.maskMdn = maskMdn;
	this.ipAddress = ipAddress;
	this.interfaceMedium = interfaceMedium;
	this.packID = packID;
	this.date = date;
	this.userAgent = userAgent;
	this.billerID = billerID;
	this.publisher = publisher;
	this.usermobiledata = usermobiledata;
}

public int getHitID() {
	return hitID;
}

public void setHitID(int hitID) {
	this.hitID = hitID;
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

public String getInterfaceMedium() {
	return interfaceMedium;
}

public void setInterfaceMedium(String interfaceMedium) {
	this.interfaceMedium = interfaceMedium;
}

public Integer getPackID() {
	return packID;
}

public void setPackID(Integer packID) {
	this.packID = packID;
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

public String getBillerID() {
	return billerID;
}

public void setBillerID(String billerID) {
	this.billerID = billerID;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

public String getUsermobiledata() {
	return usermobiledata;
}

public void setUsermobiledata(String usermobiledata) {
	this.usermobiledata = usermobiledata;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("UserHits [hitID=");
	builder.append(hitID);
	builder.append(", msisdn=");
	builder.append(msisdn);
	builder.append(", maskMdn=");
	builder.append(maskMdn);
	builder.append(", ipAddress=");
	builder.append(ipAddress);
	builder.append(", interfaceMedium=");
	builder.append(interfaceMedium);
	builder.append(", packID=");
	builder.append(packID);
	builder.append(", date=");
	builder.append(date);
	builder.append(", userAgent=");
	builder.append(userAgent);
	builder.append(", billerID=");
	builder.append(billerID);
	builder.append(", publisher=");
	builder.append(publisher);
	builder.append(", usermobiledata=");
	builder.append(usermobiledata);
	builder.append("]");
	return builder.toString();
}

}
