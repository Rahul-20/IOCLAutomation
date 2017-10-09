package com.rainiersoft.response.dto;

import java.util.Date;

public class GetAllLatestFanSlipsDataResponseBean 
{
	@Override
	public String toString() {
		return "GetAllLatestFanSlipsDataResponseBean [fanId=" + fanId + ", truckNumber=" + truckNumber + ", driverName="
				+ driverName + ", fanPin=" + fanPin + ", customer=" + customer + ", bayNum=" + bayNum + ", bayStatus="
				+ bayStatus + ", quantity=" + quantity + ", vehicleWeight=" + vehicleWeight + ", destination="
				+ destination + ", locationCode=" + locationCode + ", contractorName=" + contractorName
				+ ", fanPinStatus=" + fanPinStatus + ", fanPinCreation=" + fanPinCreation + ", fanPinExpiration="
				+ fanPinExpiration + "]";
	}
	private int fanId;
	private String truckNumber;
	private String driverName;
	private String driverLicenceNumber;
	private String fanPin;
	private String customer;
	private int bayNum;
	private String bayStatus;
	private String quantity;
	private String vehicleWeight;
	private String destination;
	private String locationCode;
	private String contractorName;
	private String fanPinStatus;
	private String fanPinCreation;
	private String fanPinExpiration;
	
	public int getFanId() {
		return fanId;
	}
	public void setFanId(int fanId) {
		this.fanId = fanId;
	}
	public String getTruckNumber() {
		return truckNumber;
	}
	public String getDriverName() {
		return driverName;
	}
	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}
	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getBayStatus() {
		return bayStatus;
	}
	public void setBayStatus(String bayStatus) {
		this.bayStatus = bayStatus;
	}
	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}
	public String getFanPin() {
		return fanPin;
	}
	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}
	public int getBayNum() {
		return bayNum;
	}
	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getVehicleWeight() {
		return vehicleWeight;
	}
	public void setVehicleWeight(String vehicleWeight) {
		this.vehicleWeight = vehicleWeight;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getFanPinStatus() {
		return fanPinStatus;
	}
	public void setFanPinStatus(String fanPinStatus) {
		this.fanPinStatus = fanPinStatus;
	}
	public String getFanPinCreation() {
		return fanPinCreation;
	}
	public void setFanPinCreation(String fanPinCreation) {
		this.fanPinCreation = fanPinCreation;
	}
	public String getFanPinExpiration() {
		return fanPinExpiration;
	}
	public void setFanPinExpiration(String fanPinExpiration) {
		this.fanPinExpiration = fanPinExpiration;
	}
}