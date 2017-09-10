package com.rainiersoft.response.dto;

public class BayCreationResponseBean 
{
	@Override
	public String toString() {
		return "BayCreationResponseBean [bayId=" + bayId + ", bayName=" + bayName + ", bayNum=" + bayNum + ", bayType="
				+ bayType + ", bayStatus=" + bayStatus + ", successFlag=" + successFlag + ", message=" + message + "]";
	}

	public Long bayId;
	public String bayName;
	public int bayNum;
	public String bayType;
	public String bayStatus;
	public boolean successFlag;
	public String message;
	
	public Long getBayId() {
		return bayId;
	}

	public void setBayId(Long bayId) {
		this.bayId = bayId;
	}
	
	public String getBayName() {
		return bayName;
	}

	public void setBayName(String bayName) {
		this.bayName = bayName;
	}

	public int getBayNum() {
		return bayNum;
	}

	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}

	public String getBayType() {
		return bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}

	public String getBayStatus() {
		return bayStatus;
	}

	public void setBayStatus(String bayStatus) {
		this.bayStatus = bayStatus;
	}

	public BayCreationResponseBean()
	{
		
	}
	
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
}
