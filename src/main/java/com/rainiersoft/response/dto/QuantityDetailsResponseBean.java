package com.rainiersoft.response.dto;

public class QuantityDetailsResponseBean 
{
	private int quantityId;
	private String quantityName;
	//private String quantityUnit;
	private String quantity;
	private String operationalStatus;
	private String quantityCreatedOn;
	private String quantityUpdatedOn;
	private String quantityCreatedBy;
	private String quantityUpdatedBy;

	public int getQunatityId() {
		return quantityId;
	}
	public void setQunatityId(int quantityId) {
		this.quantityId = quantityId;
	}
	public int getQuantityId() {
		return quantityId;
	}
	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}
	public String getQuantityCreatedOn() {
		return quantityCreatedOn;
	}
	public void setQuantityCreatedOn(String quantityCreatedOn) {
		this.quantityCreatedOn = quantityCreatedOn;
	}
	public String getQuantityUpdatedOn() {
		return quantityUpdatedOn;
	}
	public void setQuantityUpdatedOn(String quantityUpdatedOn) {
		this.quantityUpdatedOn = quantityUpdatedOn;
	}
	public String getQuantityCreatedBy() {
		return quantityCreatedBy;
	}
	public void setQuantityCreatedBy(String quantityCreatedBy) {
		this.quantityCreatedBy = quantityCreatedBy;
	}
	public String getQuantityUpdatedBy() {
		return quantityUpdatedBy;
	}
	public void setQuantityUpdatedBy(String quantityUpdatedBy) {
		this.quantityUpdatedBy = quantityUpdatedBy;
	}
	public String getQuantityName() {
		return quantityName;
	}
	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
	}
	/*public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}*/
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}
