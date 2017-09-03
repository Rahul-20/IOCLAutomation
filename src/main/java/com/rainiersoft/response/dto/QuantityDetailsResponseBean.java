package com.rainiersoft.response.dto;

public class QuantityDetailsResponseBean 
{
	private String quantityName;
	private String quantityUnit;
	private String quantity;
	private String operationalStatus;
	
	public String getQuantityName() {
		return quantityName;
	}
	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
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
