package com.rainiersoft.request.dto;

public class LocationMangRequestBean 
{
	@Override
	public String toString() {
		return "LocationMangRequestBean [locationName=" + locationName + ", locationCode=" + locationCode
				+ ", locationAddress=" + locationAddress + ", operationalStatus=" + operationalStatus + ", locationId="
				+ locationId + ", editLocationNameFlag=" + editLocationNameFlag + ", editLocationCodeFlag="
				+ editLocationCodeFlag + "]";
	}
	private String locationName;
	private String locationCode;
	private String locationAddress;
	private String operationalStatus;
	private int locationId;
	private boolean editLocationNameFlag;
	private boolean editLocationCodeFlag;
	private String city;
	private String pinCode;
	private String state;
	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public boolean getEditLocationNameFlag() {
		return editLocationNameFlag;
	}
	public void setEditLocationNameFlag(boolean editLocationNameFlag) {
		this.editLocationNameFlag = editLocationNameFlag;
	}
	public boolean getEditLocationCodeFlag() {
		return editLocationCodeFlag;
	}
	public void setEditLocationCodeFlag(boolean editLocationCodeFlag) {
		this.editLocationCodeFlag = editLocationCodeFlag;
	}
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}
