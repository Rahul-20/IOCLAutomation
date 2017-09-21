package com.rainiersoft.request.dto;

public class FanSlipMangRequestBean 
{
	private String TruckNo;
	private String FanCreatedBy;
	private String DriverName;
	private String ContractorName;
	private String DriverLicNo;
	private String Customer;
	private String Quantity;
	private String VehicleWgt;
	private String Destination;
	private String LocationCode;
	private String MobileNumber;
	private int BayNum;
	private int FanId;

	public int getFanId() {
		return FanId;
	}
	public void setFanId(int fanId) {
		FanId = fanId;
	}

	public String getFanCreatedBy() {
		return FanCreatedBy;
	}
	public void setFanCreatedBy(String fanCreatedBy) {
		FanCreatedBy = fanCreatedBy;
	}

	public String getContractorName() {
		return ContractorName;
	}
	public void setContractorName(String contractorName) {
		ContractorName = contractorName;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getTruckNo() {
		return TruckNo;
	}
	public void setTruckNo(String truckNo) {
		TruckNo = truckNo;
	}
	public String getDriverName() {
		return DriverName;
	}
	public void setDriverName(String driverName) {
		DriverName = driverName;
	}
	public String getDriverLicNo() {
		return DriverLicNo;
	}
	public void setDriverLicNo(String driverLicNo) {
		DriverLicNo = driverLicNo;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getVehicleWgt() {
		return VehicleWgt;
	}
	public void setVehicleWgt(String vehicleWgt) {
		VehicleWgt = vehicleWgt;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public String getLocationCode() {
		return LocationCode;
	}
	public void setLocationCode(String locationCode) {
		LocationCode = locationCode;
	}
	public int getBayNum() {
		return BayNum;
	}
	public void setBayNum(int bayNum) {
		BayNum = bayNum;
	}
}
