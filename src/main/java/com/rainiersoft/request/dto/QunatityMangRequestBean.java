package com.rainiersoft.request.dto;

public class QunatityMangRequestBean 
{
	@Override
	public String toString() {
		return "QunatityMangRequestBean [quantityId=" + quantityId + ", quantityName=" + quantityName + ", quantity="
				+ quantity + ", quantityStatus=" + quantityStatus + ", editQuantityFlag=" + editQuantityFlag
				+ ", editQuantityNameFlag=" + editQuantityNameFlag + "]";
	}
	private int quantityId;
	private String quantityName;
	private String quantity;
	private String quantityStatus;
	private boolean editQuantityFlag;
	private boolean editQuantityNameFlag;
	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQuantityName() {
		return quantityName;
	}
	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
	}
	public String getQuantity() {
		return quantity;
	}
	public boolean getEditQuantityFlag() {
		return editQuantityFlag;
	}
	public void setEditQuantityFlag(boolean editQuantityFlag) {
		this.editQuantityFlag = editQuantityFlag;
	}
	public boolean getEditQuantityNameFlag() {
		return editQuantityNameFlag;
	}
	public void setEditQuantityNameFlag(boolean editQuantityNameFlag) {
		this.editQuantityNameFlag = editQuantityNameFlag;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getQuantityStatus() {
		return quantityStatus;
	}
	public void setQuantityStatus(String quantityStatus) {
		this.quantityStatus = quantityStatus;
	}
	public int getQuantityId() {
		return quantityId;
	}
	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}
}
