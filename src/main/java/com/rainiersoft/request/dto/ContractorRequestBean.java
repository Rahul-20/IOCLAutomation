package com.rainiersoft.request.dto;

public class ContractorRequestBean 
{
	@Override
	public String toString() {
		return "ContractorRequestBean [contractorName=" + contractorName + ", contractorType=" + contractorType
				+ ", contractorAddress=" + contractorAddress + ", contractorCity=" + contractorCity
				+ ", contractorState=" + contractorState + ", contractorPinCode=" + contractorPinCode
				+ ", contractorOperationalStatus=" + contractorOperationalStatus + ", editContractorNameFlag="
				+ editContractorNameFlag + ", contractorId=" + contractorId + "]";
	}
	private String contractorName;
	private String contractorType;
	private String contractorAddress;
	private String contractorCity;
	private String contractorState;
	private String contractorPinCode;
	private String contractorOperationalStatus;
	private boolean editContractorNameFlag;
	private int contractorId;
	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean getEditContractorNameFlag() {
		return editContractorNameFlag;
	}
	public void setEditContractorNameFlag(boolean editContractorNameFlag) {
		this.editContractorNameFlag = editContractorNameFlag;
	}
	public int getContractorId() {
		return contractorId;
	}
	public void setContractorId(int contractorId) {
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
