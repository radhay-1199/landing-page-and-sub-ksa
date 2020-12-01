package com.gl.combo.bean;

public class Packs {
int packId;
String name;
String price;
String productId;
String publisher;
String duration;
String billerId;
String serviceId;
String serviceNode;

public Packs() {
	super();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getServiceNode() {
	return serviceNode;
}
public void setServiceNode(String serviceNode) {
	this.serviceNode = serviceNode;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String getDuration() {
	return duration;
}
public void setDuration(String duration) {
	this.duration = duration;
}
public String getBillerId() {
	return billerId;
}
public void setBillerId(String billerId) {
	this.billerId = billerId;
}
public String getServiceId() {
	return serviceId;
}
public void setServiceId(String serviceId) {
	this.serviceId = serviceId;
}

public int getPackId() {
	return packId;
}
public void setPackId(int packId) {
	this.packId = packId;
}
@Override
public String toString() {
	return "Packs [packId=" + packId + ", name=" + name + ", price=" + price + ", productId=" + productId
			+ ", publisher=" + publisher + ", duration=" + duration + ", billerId=" + billerId + ", serviceId="
			+ serviceId + ", serviceNode=" + serviceNode + "]";
}

}
