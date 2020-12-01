package com.gl.combo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendPinData {

	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("sent")
	private Integer sent;

	@Override
	public String toString() {
		return "SendPinData [userId=" + userId + ", sent=" + sent + "]";
	}
	
}
