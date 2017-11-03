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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@NamedQuery(name="findUserByUserId", query="SELECT iUserDetails FROM IoclUserDetail iUserDetails where userId=:userId")
})
public class IoclUserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UserUpdatedOn")
	private Date userUpdatedOn;
	
	@Column(name="UserDOB")
	@Temporal(TemporalType.DATE)
	private Date userDOB;

	@Column(name="UserFirstName")
	private String userFirstName;

	@Column(name="UserLastName")
	private String userLastName;

	@Column(name="UserMobileNum")
	private String userMobileNum;

	@Column(name="UserName")
	private String userName;

	@Column(name="UserPassword")
	/*@ColumnTransformer(read = "userPassword", write = "sha1(?)")*/
	private String userPassword;

	@Column(name="UserCreatedBy")
	private int userCreatedBy;
	
	@Column(name="UserUpdatedBy")
	private int userUpdatedBy;

	//bi-directional many-to-one association to IoclSupportedUserstatus
	@ManyToOne
	@JoinColumn(name="UserStatusId")
	private IoclSupportedUserstatus ioclSupportedUserstatus;

	//bi-directional many-to-one association to IoclUserroleMapping
	@OneToMany(mappedBy="ioclUserDetail", cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<IoclUserroleMapping> ioclUserroleMappings;

	public IoclUserDetail() {
	}
	
	public int getUserUpdatedBy() {
		return userUpdatedBy;
	}

	public void setUserUpdatedBy(int userUpdatedBy) {
		this.userUpdatedBy = userUpdatedBy;
	}
	
	public int getUserCreatedBy() {
		return userCreatedBy;
	}

	public void setUserCreatedBy(int userCreatedBy) {
		this.userCreatedBy = userCreatedBy;
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

	public Date getUserDOB() {
		return this.userDOB;
	}

	public void setUserDOB(Date userDOB) {
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
	public IoclSupportedUserstatus getIoclSupportedUserstatus() {
		return this.ioclSupportedUserstatus;
	}

	public void setIoclSupportedUserstatus(IoclSupportedUserstatus ioclSupportedUserstatus) {
		this.ioclSupportedUserstatus = ioclSupportedUserstatus;
	}
}