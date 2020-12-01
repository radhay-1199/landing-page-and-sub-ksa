package com.gl.combo.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@Component
public class Unsubscribe {

	@JsonProperty("error")
	private boolean error;
	
	@JsonProperty("code")
	private int code;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("x-tracking-id")
	private String trackingId;
	
	@JsonProperty("data")
	@SerializedName("data")
	private UnsubData data;

	public Unsubscribe() {
		super();
	}

	public Unsubscribe(boolean error, int code, String message, String trackingId, UnsubData data) {
		super();
		this.error = error;
		this.code = code;
		this.message = message;
		this.trackingId = trackingId;
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	public UnsubData getData() {
		return data;
	}

	public void setData(UnsubData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Unsubscribe [error=" + error + ", code=" + code + ", message=" + message + ", trackingId=" + trackingId
				+ ", data=" + data + "]";
	}

}
