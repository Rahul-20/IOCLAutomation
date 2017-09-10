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
