package com.rainiersoft.request.dto;

public class BaysMangRequestBean 
{
	@Override
	public String toString() 
	{
		return "BaysMangRequestBean [bayId=" + bayId + ", bayName=" + bayName + ", bayNum=" + bayNum + ", bayType="
				+ bayType + ", functionalStatus=" + functionalStatus + ", editbayNumFlag=" + editbayNumFlag
				+ ", editbayNameFlag=" + editbayNameFlag + "]";
	}
	private int bayId;
	private String bayName;
	private int bayNum;
	private String bayType;
	private String functionalStatus;
	private boolean editbayNumFlag;
	private boolean editbayNameFlag;
	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBayId() {
		return bayId;
	}
	public void setBayId(int bayId) {
		this.bayId = bayId;
	}
	public boolean getEditbayNumFlag() {
		return editbayNumFlag;
	}
	public void setEditbayNumFlag(boolean editbayNumFlag) {
		this.editbayNumFlag = editbayNumFlag;
	}
	public boolean getEditbayNameFlag() {
		return editbayNameFlag;
	}
	public void setEditbayNameFlag(boolean editbayNameFlag) {
		this.editbayNameFlag = editbayNameFlag;
	}
	public String getBayName() {
		return bayName;
	}
	public void setBayName(String bayName) {
		this.bayName = bayName;
	}
	public int getBayNum() {
		return bayNum;
	}
	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}
	public String getBayType() {
		return bayType;
	}
	public void setBayType(String bayType) {
		this.bayType = bayType;
	}
	public String getFunctionalStatus() {
		return functionalStatus;
	}
	public void setFunctionalStatus(String functionalStatus) {
		this.functionalStatus = functionalStatus;
	}
}