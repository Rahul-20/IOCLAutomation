package com.rainiersoft.iocl.model;

public class ResponseBean 
{
	private String UserName;
	private String Msg;
	
	public ResponseBean()
	{
		
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

}
