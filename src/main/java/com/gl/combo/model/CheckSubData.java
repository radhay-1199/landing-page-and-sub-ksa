package com.gl.combo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckSubData {

	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("is_subscribed")
	private boolean isSubscribed;

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	@Override
	public String toString() {
		return "CheckSubData [userId=" + userId + ", isSubscribed=" + isSubscribed + "]";
	}

}
