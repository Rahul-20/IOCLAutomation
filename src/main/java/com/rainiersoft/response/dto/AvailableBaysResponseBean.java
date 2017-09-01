package com.rainiersoft.response.dto;

import java.util.List;

public class AvailableBaysResponseBean 
{
	@Override
	public String toString() {
		return "AvailableBaysResponseBean [bayNumber=" + bayNumber + ", bayName=" + bayName + ", bayFunctionalStatus="
				+ bayFunctionalStatus + ", bayAvailableStatus=" + bayAvailableStatus + ", fanslipsAssignedBean="
				+ fanslipsAssignedBean + "]";
	}

	private int bayNumber;
	private String bayName;
	private String bayFunctionalStatus;
	private String bayAvailableStatus;
	private List<FanslipsAssignedBean> fanslipsAssignedBean;

	public AvailableBaysResponseBean()
	{

	}

	public String getBayAvailableStatus() {
		return bayAvailableStatus;
	}

	public void setBayAvailableStatus(String bayAvailableStatus) {
		this.bayAvailableStatus = bayAvailableStatus;
	}

	public int getBayNumber() {
		return bayNumber;
	}

	public void setBayNumber(int bayNumber) {
		this.bayNumber = bayNumber;
	}

	public String getBayName() {
		return bayName;
	}

	public void setBayName(String bayName) {
		this.bayName = bayName;
	}

	public String getBayFunctionalStatus() {
		return bayFunctionalStatus;
	}

	public void setBayFunctionalStatus(String bayFunctionalStatus) {
		this.bayFunctionalStatus = bayFunctionalStatus;
	}

	public List<FanslipsAssignedBean> getFanslipsAssignedBean() {
		return fanslipsAssignedBean;
	}

	public void setFanslipsAssignedBean(List<FanslipsAssignedBean> fanslipsAssignedBean) {
		this.fanslipsAssignedBean = fanslipsAssignedBean;
	}
}
