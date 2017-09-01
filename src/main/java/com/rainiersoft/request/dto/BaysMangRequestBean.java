package com.rainiersoft.request.dto;

public class BaysMangRequestBean 
{
	private String bayName;
	private int bayNum;
	private String bayType;
	private String functionalStatus;

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
