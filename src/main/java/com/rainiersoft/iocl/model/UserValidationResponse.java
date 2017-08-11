package com.rainiersoft.iocl.model;

import java.util.List;

public class UserValidationResponse 
{
	private String successfulMsg;
	private boolean successfulFlag;
	private List<String> userPrivilages;
	
	public String getSuccessfulMsg() {
		return successfulMsg;
	}
	public void setSuccessfulMsg(String successfulMsg) {
		this.successfulMsg = successfulMsg;
	}
	public boolean getSuccessfulFlag() {
		return successfulFlag;
	}
	public void setSuccessfulFlag(boolean successfulFlag) {
		this.successfulFlag = successfulFlag;
	}
	public List<String> getUserPrivilages() {
		return userPrivilages;
	}
	public void setUserPrivilages(List<String> userPrivilages) {
		this.userPrivilages = userPrivilages;
	}

}
