package com.rainiersoft.response.dto;

public class FanPinCreatedResponse 
{
	private String FanPin;
	private int BayNumber;
	private String TruckNumber;
	private String Quantity;

	public FanPinCreatedResponse()
	{

	}

	public String getFanPin() {
		return FanPin;
	}

	public void setFanPin(String fanPin) {
		FanPin = fanPin;
	}

	public int getBayNumber() {
		return BayNumber;
	}

	public void setBayNumber(int bayNumber) {
		BayNumber = bayNumber;
	}

	public String getTruckNumber() {
		return TruckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		TruckNumber = truckNumber;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
}
