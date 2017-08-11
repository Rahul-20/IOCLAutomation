package com.rainiersoft.iocl.model;

public class ErrorResponseBean 
{
	private int errorCode;
	private String errorMessage;

	public ErrorResponseBean(int errorCode, String errorMessage) 
	{
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode()
	{
		return errorCode;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
