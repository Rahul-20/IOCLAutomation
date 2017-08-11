package com.rainiersoft.iocl.model;

public class BaysMangRequestBean 
{
	private String bayName;
	private String bayNum;
	private String bayType;
	private String functionalStatus;
	private String operationalStatus;
	
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
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}
