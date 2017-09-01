package com.rainiersoft.iocl.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the iocl_userrole_mapping database table.
 * 
 */
@Entity
@Table(name="iocl_userrole_mapping")
public class IoclUserroleMapping implements Serializable {
	private static final long serialVersionUID = 1999119919191L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	//bi-directional many-to-one association to IoclSupportedUserrole
	@ManyToOne
	@JoinColumn(name="UserTypeId")
	private IoclSupportedUserrole ioclSupportedUserrole;

	//bi-directional many-to-one association to IoclUserDetail
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserId")
	private IoclUserDetail ioclUserDetail;

	public IoclUserroleMapping() {
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public IoclSupportedUserrole getIoclSupportedUserrole() {
		return this.ioclSupportedUserrole;
	}

	public void setIoclSupportedUserrole(IoclSupportedUserrole ioclSupportedUserrole) {
		this.ioclSupportedUserrole = ioclSupportedUserrole;
	}

	public IoclUserDetail getIoclUserDetail() {
		return this.ioclUserDetail;
	}

	public void setIoclUserDetail(IoclUserDetail ioclUserDetail) {
		this.ioclUserDetail = ioclUserDetail;
	}
}