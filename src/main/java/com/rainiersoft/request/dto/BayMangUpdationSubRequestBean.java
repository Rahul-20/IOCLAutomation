package com.rainiersoft.request.dto;

public class BayMangUpdationSubRequestBean 
{
	private String bayName;
	private String bayNum;
	private String editBayNumFlag;
	private String editBayNameFlag;
	
	public String getBayName() {
		return bayName;
	}
	public void setBayName(String bayName) {
		this.bayName = bayName;
	}
	public String getBayNum() {
		return bayNum;
	}
	public void setBayNum(String bayNum) {
		this.bayNum = bayNum;
	}
	public String getEditBayNumFlag() {
		return editBayNumFlag;
	}
	public void setEditBayNumFlag(String editBayNumFlag) {
		this.editBayNumFlag = editBayNumFlag;
	}
	public String geteditBayNameFlag() {
		return editBayNameFlag;
	}
	public void setEditBayNameFlag(String editBayNameFlag) {
		this.editBayNameFlag = editBayNameFlag;
	}
}
