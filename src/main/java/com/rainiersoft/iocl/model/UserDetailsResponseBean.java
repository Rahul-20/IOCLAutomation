package com.rainiersoft.iocl.model;

import java.util.List;

public class UserDetailsResponseBean 
{
	private String UserName;
	private String UserFirstName;
	private String UserLastName;
	private String UserDOB;
	private String UserAadharNum;
	private String UserMobileNum;
	private String UserPassword;
	private List<String> UserType;
	private String UserStatus;

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
