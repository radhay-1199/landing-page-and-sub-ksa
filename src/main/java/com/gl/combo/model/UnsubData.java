package com.gl.combo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnsubData {

	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("unsubscribe")
	private Boolean unsubscribe;
	
	@JsonProperty("msg")
	private String msg;

	@Override
	public String toString() {
		return "UnsubData [userId=" + userId + ", unsubscribe=" + unsubscribe + ", msg=" + msg + "]";
	}
	
}
