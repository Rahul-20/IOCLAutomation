package com.rainiersoft.response.dto;

public class LocationDetailsResponseBean 
{
	private int locationId;
	private String locationName;
	private String locationCode;
	private String locationAddress;
	private String state;
	private String pinCode;
	private String city;
	private String locationCreatedBy;
	private String locationUpdatedBy;
	private String locationCreatedOn;
	private String locationUpdatedOn;
	
	public String getState() {
		return state;
	}
	public String getLocationCreatedBy() {
		return locationCreatedBy;
	}
	public void setLocationCreatedBy(String locationCreatedBy) {
		this.locationCreatedBy = locationCreatedBy;
	}
	public String getLocationUpdatedBy() {
		return locationUpdatedBy;
	}
	public void setLocationUpdatedBy(String locationUpdatedBy) {
		this.locationUpdatedBy = locationUpdatedBy;
	}
	public String getLocationCreatedOn() {
		return locationCreatedOn;
	}
	public void setLocationCreatedOn(String locationCreatedOn) {
		this.locationCreatedOn = locationCreatedOn;
	}
	public String getLocationUpdatedOn() {
		return locationUpdatedOn;
	}
	public void setLocationUpdatedOn(String locationUpdatedOn) {
		this.locationUpdatedOn = locationUpdatedOn;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String operationalStatus;

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
	public String getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
}
