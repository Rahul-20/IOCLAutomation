package com.rainiersoft.response.dto;

public class CreationAndUpdationResponseBean 
{
	@Override
	public String toString() {
		return "CreationAndUpdationResponseBean [UserName=" + UserName + ", FirstName=" + FirstName + ", LastName="
				+ LastName + ", DOB=" + DOB + ", Aadhaar=" + Aadhaar + ", MobileNo=" + MobileNo + ", UserType="
				+ UserType + ", Status=" + Status + ", message=" + message + ", successFlag=" + successFlag
				+ ", UserID=" + UserID + "]";
	}
	private String UserName;
	private String FirstName;
	private String LastName;
	private String DOB;
	private String Aadhaar;
	private String MobileNo;
	private String UserType;
	private String Status;
	private String message;
	private boolean successFlag;
	private Long UserID;
	
	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getAadhaar() {
		return Aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		Aadhaar = aadhaar;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public CreationAndUpdationResponseBean()
	{
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
}
