package com.rainiersoft.response.dto;

public class QuantityDetailsResponseBean 
{
	private int qunatityId;
	private String quantityName;
	//private String quantityUnit;
	private String quantity;
	private String operationalStatus;

	public int getQunatityId() {
		return qunatityId;
	}
	public void setQunatityId(int qunatityId) {
		this.qunatityId = qunatityId;
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
