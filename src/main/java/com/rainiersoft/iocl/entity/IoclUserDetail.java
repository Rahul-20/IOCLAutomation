package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnTransformer;


/**
 * The persistent class for the iocl_user_details database table.
 * 
 */
@Entity
@Table(name="iocl_user_details")
@NamedQueries({
	@NamedQuery(name="IoclUserDetail.findAll", query="SELECT i FROM IoclUserDetail i"),
	@NamedQuery(name="findUserByUserName", query="SELECT iUserDetails FROM IoclUserDetail iUserDetails where userName=:userName"),
})
public class IoclUserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "IoclUserDetail [userId=" + userId + ", pwdExpiryDate=" + pwdExpiryDate + ", userAadharNum="
				+ userAadharNum + ", userCreatedOn=" + userCreatedOn + ", userDeletedOn=" + userDeletedOn + ", userDOB="
				+ userDOB + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userMobileNum="
				+ userMobileNum + ", userName=" + userName + ", userPassword=" + userPassword + ", userStatus="
				+ userStatus + ", userUpdatedOn=" + userUpdatedOn + ", ioclUserroleMappings=" + ioclUserroleMappings
				+ "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PwdExpiryDate")
	private Date pwdExpiryDate;

	@Column(name="UserAadharNum")
	private String userAadharNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UserCreatedOn")
	private Date userCreatedOn;

	@Column(name="UserDeletedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userDeletedOn;

	@Column(name="UserDOB")
	private String userDOB;

	@Column(name="UserFirstName")
	private String userFirstName;

	@Column(name="UserLastName")
	private String userLastName;

	@Column(name="UserMobileNum")
	private String userMobileNum;

	@Column(name="UserName")
	private String userName;

	@Column(name="UserPassword")
	@ColumnTransformer(read = "userPassword", 
	write = "sha1(?)")
	private String userPassword;

	@Column(name="UserStatus")
	private String userStatus;

	/*	@Column(name="UserType")
	private String userType;*/

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UserUpdatedOn")
	private Date userUpdatedOn;

	//bi-directional many-to-one association to IoclUserroleMapping
	@OneToMany(mappedBy="ioclUserDetail", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<IoclUserroleMapping> ioclUserroleMappings;

	public IoclUserDetail() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getPwdExpiryDate() {
		return this.pwdExpiryDate;
	}

	public void setPwdExpiryDate(Date pwdExpiryDate) {
		this.pwdExpiryDate = pwdExpiryDate;
	}

	public String getUserAadharNum() {
		return this.userAadharNum;
	}

	public void setUserAadharNum(String userAadharNum) {
		this.userAadharNum = userAadharNum;
	}

	public Date getUserCreatedOn() {
		return this.userCreatedOn;
	}

	public void setUserCreatedOn(Date userCreatedOn) {
		this.userCreatedOn = userCreatedOn;
	}

	public Date getUserDeletedOn() {
		return this.userDeletedOn;
	}

	public void setUserDeletedOn(Date userDeletedOn) {
		this.userDeletedOn = userDeletedOn;
	}

	public String getUserDOB() {
		return this.userDOB;
	}

	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserFirstName() {
		return this.userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return this.userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserMobileNum() {
		return this.userMobileNum;
	}

	public void setUserMobileNum(String userMobileNum) {
		this.userMobileNum = userMobileNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/*	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}*/

	public Date getUserUpdatedOn() {
		return this.userUpdatedOn;
	}

	public void setUserUpdatedOn(Date userUpdatedOn) {
		this.userUpdatedOn = userUpdatedOn;
	}

	public List<IoclUserroleMapping> getIoclUserroleMappings() {
		return this.ioclUserroleMappings;
	}

	public void setIoclUserroleMappings(List<IoclUserroleMapping> ioclUserroleMappings) {
		this.ioclUserroleMappings = ioclUserroleMappings;
	}

}