package com.rainiersoft.iocl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="iocl_userprivileges_mapping")
@NamedQueries({
	@NamedQuery(name="findPrivilegeNamesByRole", query="SELECT ioclUserPrivilegesMapping FROM IoclUserPrivilegesMapping ioclUserPrivilegesMapping where userRole=:userRole"),
})
public class IoclUserPrivilegesMapping 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PrivilegeId")
	private int privilegeId;

	@Column(name="UserRole")
	private String userRole;

	@Column(name="PrivilegesName")
	private String privilegeNames;

	public int getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(int privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPrivilegeNames() {
		return privilegeNames;
	}

	public void setPrivilegeNames(String privilegeNames) {
		this.privilegeNames = privilegeNames;
	}
}