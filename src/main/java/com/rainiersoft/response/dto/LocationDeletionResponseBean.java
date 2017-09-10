package com.rainiersoft.response.dto;

public class LocationDeletionResponseBean 
{
	private boolean successFlag;
	private String message;
	public boolean getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
