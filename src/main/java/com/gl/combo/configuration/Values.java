package com.gl.combo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Values {

	@Value("${api_key}")
	private String apiKey;

	@Value("${content_url}")
	private String contentUrl;
	
	@Value("${STC_service_id}")
	private int stcServiceId;

	@Value("${STC_shortcode}")
	private int stcShortcode;
	
	@Value("${mobily_service_id}")
	private int mobilyServiceId;
	
	@Value("${mobily_shortcode}")
	private int mobilyShortcode;
	
	@Value("${zain_service_id}")
	private int zainServiceId;
	
	@Value("${zain_shortcode}")
	private int zainShortcode;
	
	@Value("${biller_id}")
	private String bp;
	
	@Value("${publisher}")
	private String publisher;
	
	@Value("${interface}")
	private String interfacee;
	
	@Value("${product_id}")
	private String productId;
	
	@Value("${requested_price}")
	private Float requestedPrice;
	
	@Value("${pack_id}")
	private int packId;
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public int getStcServiceId() {
		return stcServiceId;
	}

	public void setStcServiceId(int stcServiceId) {
		this.stcServiceId = stcServiceId;
	}

	public int getStcShortcode() {
		return stcShortcode;
	}

	public void setStcShortcode(int stcShortcode) {
		this.stcShortcode = stcShortcode;
	}

	public int getMobilyServiceId() {
		return mobilyServiceId;
	}

	public void setMobilyServiceId(int mobilyServiceId) {
		this.mobilyServiceId = mobilyServiceId;
	}

	public int getMobilyShortcode() {
		return mobilyShortcode;
	}

	public void setMobilyShortcode(int mobilyShortcode) {
		this.mobilyShortcode = mobilyShortcode;
	}

	public int getZainServiceId() {
		return zainServiceId;
	}

	public void setZainServiceId(int zainServiceId) {
		this.zainServiceId = zainServiceId;
	}

	public int getZainShortcode() {
		return zainShortcode;
	}

	public void setZainShortcode(int zainShortcode) {
		this.zainShortcode = zainShortcode;
	}

	public String getBp() {
		return bp;
	}

	public void setBp(String bp) {
		this.bp = bp;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getInterfacee() {
		return interfacee;
	}

	public void setInterfacee(String interfacee) {
		this.interfacee = interfacee;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Float getRequestedPrice() {
		return requestedPrice;
	}

	public void setRequestedPrice(Float requestedPrice) {
		this.requestedPrice = requestedPrice;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}
	
}
