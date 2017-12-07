package com.rainiersoft.response.dto;

public class TotalizerReportDataBean 
{
	private String date;
	private int bayNum;
	private String fanSlipNum;
	private String StartTime;
	private String EndTime;
	private String openingReading;
	private String closingReading;
	private String loadedQuantity;
	
	public String getFanSlipNum() {
		return fanSlipNum;
	}
	public void setFanSlipNum(String fanSlipNum) {
		this.fanSlipNum = fanSlipNum;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBayNum() {
		return bayNum;
	}
	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}
	public String getOpeningReading() {
		return openingReading;
	}
	public void setOpeningReading(String openingReading) {
		this.openingReading = openingReading;
	}
	public String getClosingReading() {
		return closingReading;
	}
	public void setClosingReading(String closingReading) {
		this.closingReading = closingReading;
	}
	public String getLoadedQuantity() {
		return loadedQuantity;
	}
	public void setLoadedQuantity(String loadedQuantity) {
		this.loadedQuantity = loadedQuantity;
	}
}