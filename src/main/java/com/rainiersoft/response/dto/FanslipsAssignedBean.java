package com.rainiersoft.response.dto;

public class FanslipsAssignedBean 
{
	@Override
	public String toString() {
		return "FanslipsAssignedBean [fanPin=" + fanPin + ", fanPinStatus=" + fanPinStatus + "]";
	}
	private String fanPin;
	private String fanPinStatus;

	public String getFanPinStatus() {
		return fanPinStatus;
	}
	public void setFanPinStatus(String fanPinStatus) {
		this.fanPinStatus = fanPinStatus;
	}
	public String getFanPin() {
		return fanPin;
	}
	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}
}
