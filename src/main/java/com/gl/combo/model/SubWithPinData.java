package com.gl.combo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubWithPinData {

	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("subscribe")
	private boolean subscribe;

	@Override
	public String toString() {
		return "SubWithPinData [userId=" + userId + ", subscribe=" + subscribe + "]";
	}
	
}
