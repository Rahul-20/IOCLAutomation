package com.rainiersoft.iocl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the iocl_userprivileges_mapping database table.
 * 
 */
@Entity
@Table(name="iocl_userprivileges_mapping")
@NamedQueries({
	@NamedQuery(name="findPrivilegeNamesByRole", query="SELECT ioclUserPrivilegesMapping FROM IoclUserPrivilegesMapping ioclUserPrivilegesMapping where userRoleId=:userRoleId"),
})
public class IoclUserPrivilegesMapping 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PrivilegeId")
	private int privilegeId;

	@Column(name="UserRoleId")
	private int userRoleId;

	@Column(name="PrivilegesName")
	private String privilegeNames;

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeNames() {
		return privilegeNames;
	}

	public void setPrivilegeNames(String privilegeNames) {
		this.privilegeNames = privilegeNames;
	}

	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
}