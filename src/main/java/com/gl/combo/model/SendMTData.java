package com.gl.combo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMTData {

	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("mt_id")
	private String mtId;

	@Override
	public String toString() {
		return "SendMTData [userId=" + userId + ", mtId=" + mtId + "]";
	}
	
}
