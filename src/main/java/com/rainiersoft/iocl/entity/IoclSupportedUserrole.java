package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_userroles database table.
 * 
 */
@Entity
@Table(name="iocl_supported_userroles")
@NamedQueries({
@NamedQuery(name="IoclSupportedUserrole.findAll", query="SELECT i FROM IoclSupportedUserrole i"),
@NamedQuery(name="findUserRoleIdByUserRole", query="SELECT i FROM IoclSupportedUserrole i where i.roleName=:roleName")
})
public class IoclSupportedUserrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RoleId")
	private int roleId;

	@Column(name="RoleName")
	private String roleName;

	//bi-directional many-to-one association to IoclUserroleMapping
	@OneToMany(mappedBy="ioclSupportedUserrole")
	private List<IoclUserroleMapping> ioclUserroleMappings;

	public IoclSupportedUserrole() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<IoclUserroleMapping> getIoclUserroleMappings() {
		return this.ioclUserroleMappings;
	}

	public void setIoclUserroleMappings(List<IoclUserroleMapping> ioclUserroleMappings) {
		this.ioclUserroleMappings = ioclUserroleMappings;
	}

	public IoclUserroleMapping addIoclUserroleMapping(IoclUserroleMapping ioclUserroleMapping) {
		getIoclUserroleMappings().add(ioclUserroleMapping);
		ioclUserroleMapping.setIoclSupportedUserrole(this);
		return ioclUserroleMapping;
	}

	public IoclUserroleMapping removeIoclUserroleMapping(IoclUserroleMapping ioclUserroleMapping) {
		getIoclUserroleMappings().remove(ioclUserroleMapping);
		ioclUserroleMapping.setIoclSupportedUserrole(null);
		return ioclUserroleMapping;
	}
}