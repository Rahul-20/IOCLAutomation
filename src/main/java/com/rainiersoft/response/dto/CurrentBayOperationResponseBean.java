package com.rainiersoft.response.dto;

public class CurrentBayOperationResponseBean 
{
	@Override
	public String toString() {
		return "CurrentBayOperationResponseBean [bayNumber=" + bayNumber + ", bayName=" + bayName
				+ ", bayFunctionalStatus=" + bayFunctionalStatus + ", bayAvailableStatus=" + bayAvailableStatus
				+ ", fanId=" + fanId + ", truckNumber=" + truckNumber + ", driverName=" + driverName
				+ ", driverLicenceNumber=" + driverLicenceNumber + ", fanPin=" + fanPin + ", customer=" + customer
				+ ", quantity=" + quantity + ", preSet=" + preSet + ", vehicleWeight=" + vehicleWeight
				+ ", destination=" + destination + ", locationCode=" + locationCode + ", contractorName="
				+ contractorName + ", fanPinStatus=" + fanPinStatus + ", fanPinCreation=" + fanPinCreation
				+ ", fanPinExpiration=" + fanPinExpiration + ", quantityID=" + quantityID + "]";
	}

	private int bayNumber;
	private String bayName;
	private String bayFunctionalStatus;
	private String bayAvailableStatus;
	private int fanId;
	private String truckNumber;
	private String driverName;
	private String driverLicenceNumber;
	private String fanPin;
	private String customer;
	private String quantity;
	private String preSet;
	private String vehicleWeight;
	private String destination;
	private String locationCode;
	private String contractorName;
	private String fanPinStatus;
	private String fanPinCreation;
	private String fanPinExpiration;
	private int quantityID;
	private boolean isStoppedFlag;

	public CurrentBayOperationResponseBean()
	{

	}
	
	public boolean isStoppedFlag() {
		return isStoppedFlag;
	}

	public void setStoppedFlag(boolean isStoppedFlag) {
		this.isStoppedFlag = isStoppedFlag;
	}

	public int getFanId() {
		return fanId;
	}

	public void setFanId(int fanId) {
		this.fanId = fanId;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}

	public String getFanPin() {
		return fanPin;
	}

	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPreSet() {
		return preSet;
	}

	public void setPreSet(String preSet) {
		this.preSet = preSet;
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

	public int getQuantityID() {
		return quantityID;
	}

	public void setQuantityID(int quantityID) {
		this.quantityID = quantityID;
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
}