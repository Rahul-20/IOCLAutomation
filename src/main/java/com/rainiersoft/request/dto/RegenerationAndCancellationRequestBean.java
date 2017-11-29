package com.rainiersoft.request.dto;

public class RegenerationAndCancellationRequestBean 
{
	private int FanId;
	private String UserName;
	private String comments;
	
	public int getFanId() {
		return FanId;
	}
	public void setFanId(int fanId) {
		FanId = fanId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
