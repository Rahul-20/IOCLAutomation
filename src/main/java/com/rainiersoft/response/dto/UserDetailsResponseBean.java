package com.rainiersoft.response.dto;

import java.util.List;

public class UserDetailsResponseBean 
{
	@Override
	public String toString() {
		return "UserDetailsResponseBean [UserID=" + UserID + ", UserName=" + UserName + ", UserFirstName="
				+ UserFirstName + ", UserLastName=" + UserLastName + ", UserDOB=" + UserDOB + ", UserAadharNum="
				+ UserAadharNum + ", UserMobileNum=" + UserMobileNum + ", UserType=" + UserType + ", UserStatus="
				+ UserStatus + ", UserEditFlag=" + UserEditFlag + "]";
	}
	private int UserID;
	private String UserName;
	private String UserFirstName;
	private String UserLastName;
	private String UserDOB;
	private String UserAadharNum;
	private String UserMobileNum;
	private String UserPassword;
	private List<String> UserType;
	private String UserStatus;
	private boolean UserEditFlag;

	public boolean getUserEditFlag() {
		return UserEditFlag;
	}
	public void setUserEditFlag(boolean userEditFlag) {
		UserEditFlag = userEditFlag;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserFirstName() {
		return UserFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		UserFirstName = userFirstName;
	}
	public String getUserLastName() {
		return UserLastName;
	}
	public void setUserLastName(String userLastName) {
		UserLastName = userLastName;
	}
	public String getUserDOB() {
		return UserDOB;
	}
	public void setUserDOB(String userDOB) {
		UserDOB = userDOB;
	}
	public String getUserAadharNum() {
		return UserAadharNum;
	}
	public void setUserAadharNum(String userAadharNum) {
		UserAadharNum = userAadharNum;
	}
	public String getUserMobileNum() {
		return UserMobileNum;
	}
	public void setUserMobileNum(String userMobileNum) {
		UserMobileNum = userMobileNum;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public List<String> getUserType() {
		return UserType;
	}
	public void setUserType(List<String> userType) {
		UserType = userType;
	}
	public String getUserStatus() {
		return UserStatus;
	}
	public void setUserStatus(String userStatus) {
		UserStatus = userStatus;
	}
}
