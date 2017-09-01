package com.rainiersoft.response.dto;

public class BayCreationResponseBean 
{
	public boolean successFlag;
	public String message;
	
	public BayCreationResponseBean()
	{
		
	}
	
	public boolean isSuccessFlag() {
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
