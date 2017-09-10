package com.rainiersoft.response.dto;

public class AllBayDetailsResponseBean 
{
	@Override
	public String toString() {
		return "AllBayDetailsResponseBean [bayName=" + bayName + ", bayNum=" + bayNum + ", bayType=" + bayType
				+ ", functionalStatus=" + functionalStatus + ", bayId=" + bayId + "]";
	}
	private String bayName;
	private int bayNum;
	private String bayType;
	private String functionalStatus;
	private int bayId;

	public int getBayId() {
		return bayId;
	}
	public void setBayId(int bayId) {
		this.bayId = bayId;
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
