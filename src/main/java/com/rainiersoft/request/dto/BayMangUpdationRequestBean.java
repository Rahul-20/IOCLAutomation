package com.rainiersoft.request.dto;

import java.util.List;
import java.util.Map;

public class BayMangUpdationRequestBean 
{
	private List<Map<String,String>>  bayName;
	private List<Map<String,String>>  bayNum;
	private int bayId;
	private String bayType;
	private String bayFunctionalStatus;

	public int getBayId() {
		return bayId;
	}
	public void setBayId(int bayId) {
		this.bayId = bayId;
	}
	public String getBayType() {
		return bayType;
	}
	public void setBayType(String bayType) {
		this.bayType = bayType;
	}
	public String getBayFunctionalStatus() {
		return bayFunctionalStatus;
	}
	public void setBayFunctionalStatus(String bayFunctionalStatus) {
		this.bayFunctionalStatus = bayFunctionalStatus;
	}

	public List<Map<String, String>> getBayName() {
		return bayName;
	}
	public void setBayName(List<Map<String, String>> bayName) {
		this.bayName = bayName;
	}
	public List<Map<String, String>> getBayNum() {
		return bayNum;
	}
	public void setBayNum(List<Map<String, String>> bayNum) {
		this.bayNum = bayNum;
	}
}
