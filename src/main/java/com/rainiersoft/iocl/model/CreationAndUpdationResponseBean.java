package com.rainiersoft.iocl.model;

public class CreationAndUpdationResponseBean 
{
	private String message;
	private boolean successFlag;

	public CreationAndUpdationResponseBean()
	{
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
}
