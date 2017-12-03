package com.rainiersoft.response.dto;

public class TruckFillingReportDataBean 
{
	private String truckNo;
	private String fanSlipNum;
	private String presetQty;
	private String loadedQty;
	private String startTime;
	private String endTime;
	
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getFanSlipNum() {
		return fanSlipNum;
	}
	public void setFanSlipNum(String fanSlipNum) {
		this.fanSlipNum = fanSlipNum;
	}
	public String getPresetQty() {
		return presetQty;
	}
	public void setPresetQty(String presetQty) {
		this.presetQty = presetQty;
	}
	public String getLoadedQty() {
		return loadedQty;
	}
	public void setLoadedQty(String loadedQty) {
		this.loadedQty = loadedQty;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}