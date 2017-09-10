package com.rainiersoft.response.dto;

public class BayDeletionResponseBean 
{
	private boolean successFlag;
	private String msg;
	public boolean getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
