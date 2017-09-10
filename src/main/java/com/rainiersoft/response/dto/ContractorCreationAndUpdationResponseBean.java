package com.rainiersoft.response.dto;

public class ContractorCreationAndUpdationResponseBean 
{
	private String contractorName;
	private String contractorType;
	private String contractorAddress;
	private String contractorCity;
	private String contractorState;
	private String contractorPinCode;
	private String contractorOperationalStatus;
	private String message;
	private boolean successFlag;
	private Long contractorId;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public Long getContractorId() {
		return contractorId;
	}
	public void setContractorId(Long contractorId) {
		this.contractorId = contractorId;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getContractorType() {
		return contractorType;
	}
	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}
	public String getContractorAddress() {
		return contractorAddress;
	}
	public void setContractorAddress(String contractorAddress) {
		this.contractorAddress = contractorAddress;
	}
	public String getContractorCity() {
		return contractorCity;
	}
	public void setContractorCity(String contractorCity) {
		this.contractorCity = contractorCity;
	}
	public String getContractorState() {
		return contractorState;
	}
	public void setContractorState(String contractorState) {
		this.contractorState = contractorState;
	}
	public String getContractorPinCode() {
		return contractorPinCode;
	}
	public void setContractorPinCode(String contractorPinCode) {
		this.contractorPinCode = contractorPinCode;
	}
	public String getContractorOperationalStatus() {
		return contractorOperationalStatus;
	}
	public void setContractorOperationalStatus(String contractorOperationalStatus) {
		this.contractorOperationalStatus = contractorOperationalStatus;
	}
}
