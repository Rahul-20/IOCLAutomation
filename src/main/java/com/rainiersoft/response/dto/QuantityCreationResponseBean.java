package com.rainiersoft.response.dto;

public class QuantityCreationResponseBean 
{
	private boolean successFlag;
	private String message;
	private Long quantityId;
	private String qunatityName;
	private String qunatity;
	private String operationalStatus;
	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getQuantityId() {
		return quantityId;
	}
	public void setQuantityId(Long quantityId) {
		this.quantityId = quantityId;
	}
	public String getQunatityName() {
		return qunatityName;
	}
	public void setQunatityName(String qunatityName) {
		this.qunatityName = qunatityName;
	}
	public String getQunatity() {
		return qunatity;
	}
	public void setQunatity(String qunatity) {
		this.qunatity = qunatity;
	}
	public String getOperationalStatus() {
		return operationalStatus;
	}
	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}
