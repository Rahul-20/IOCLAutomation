package com.rainiersoft.response.dto;


/*
 * Response Bean Class
 */

public class FanPinCancellationResponseBean 
{
	private String message;
	private boolean successFlag;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
}
