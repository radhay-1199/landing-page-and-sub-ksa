package com.gl.combo.bean;

import org.springframework.stereotype.Component;

@Component

public class ComTxn {
private String msisdn;
private int packID;
private String productID;
private String transactionUniqueID;
private float requestedPrice;
private String interfaceMedium;
private String billerID;
private String publisher;
private String serviceID;
private String price;

public ComTxn() {
	super();
}

public ComTxn(String msisdn, int packID, String productID, String transactionUniqueID, float requestedPrice,
		String interfaceMedium, String billerID, String publisher, String serviceID, String price) {
	super();
	this.msisdn = msisdn;
	this.packID = packID;
	this.productID = productID;
	this.transactionUniqueID = transactionUniqueID;
	this.requestedPrice = requestedPrice;
	this.interfaceMedium = interfaceMedium;
	this.billerID = billerID;
	this.publisher = publisher;
	this.serviceID = serviceID;
	this.price = price;
}

public String getMsisdn() {
	return msisdn;
}

public void setMsisdn(String msisdn) {
	this.msisdn = msisdn;
}

public int getPackID() {
	return packID;
}

public void setPackID(int packID) {
	this.packID = packID;
}

public String getProductID() {
	return productID;
}

public void setProductID(String productID) {
	this.productID = productID;
}

public String getTransactionUniqueID() {
	return transactionUniqueID;
}

public void setTransactionUniqueID(String transactionUniqueID) {
	this.transactionUniqueID = transactionUniqueID;
}

public float getRequestedPrice() {
	return requestedPrice;
}

public void setRequestedPrice(float requestedPrice) {
	this.requestedPrice = requestedPrice;
}

public String getInterfaceMedium() {
	return interfaceMedium;
}

public void setInterfaceMedium(String interfaceMedium) {
	this.interfaceMedium = interfaceMedium;
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

public String getServiceID() {
	return serviceID;
}

public void setServiceID(String serviceID) {
	this.serviceID = serviceID;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("ComTxn [msisdn=");
	builder.append(msisdn);
	builder.append(", packID=");
	builder.append(packID);
	builder.append(", productID=");
	builder.append(productID);
	builder.append(", transactionUniqueID=");
	builder.append(transactionUniqueID);
	builder.append(", requestedPrice=");
	builder.append(requestedPrice);
	builder.append(", interfaceMedium=");
	builder.append(interfaceMedium);
	builder.append(", billerID=");
	builder.append(billerID);
	builder.append(", publisher=");
	builder.append(publisher);
	builder.append(", serviceID=");
	builder.append(serviceID);
	builder.append(", price=");
	builder.append(price);
	builder.append("]");
	return builder.toString();
}

}
