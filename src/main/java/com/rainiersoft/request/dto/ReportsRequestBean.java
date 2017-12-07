package com.rainiersoft.request.dto;

import java.util.Date;

public class ReportsRequestBean 
{
	private int pageNumber;
	private int pageSize;
	private String startDate;
	private String endDate;
	private String BayNumber;
	
	public String getBayNumber() {
		return BayNumber;
	}
	public void setBayNumber(String bayNumber) {
		BayNumber = bayNumber;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
