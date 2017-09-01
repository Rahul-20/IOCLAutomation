package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_userstatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_userstatus")
@NamedQueries({
@NamedQuery(name="IoclSupportedUserstatus.findAll", query="SELECT i FROM IoclSupportedUserstatus i"),
@NamedQuery(name="findUserStatusIdByUserStatus", query="SELECT i FROM IoclSupportedUserstatus i where i.userStatus=:userStatus")
})
public class IoclSupportedUserstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="StatusId")
	private int statusId;

	@Column(name="UserStatus")
	private String userStatus;

	//bi-directional many-to-one association to IoclUserDetail
	@OneToMany(mappedBy="ioclSupportedUserstatus")
	private List<IoclUserDetail> ioclUserDetails;

	public IoclSupportedUserstatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public List<IoclUserDetail> getIoclUserDetails() {
		return this.ioclUserDetails;
	}

	public void setIoclUserDetails(List<IoclUserDetail> ioclUserDetails) {
		this.ioclUserDetails = ioclUserDetails;
	}

	public IoclUserDetail addIoclUserDetail(IoclUserDetail ioclUserDetail) {
		getIoclUserDetails().add(ioclUserDetail);
		ioclUserDetail.setIoclSupportedUserstatus(this);
		return ioclUserDetail;
	}

	public IoclUserDetail removeIoclUserDetail(IoclUserDetail ioclUserDetail) {
		getIoclUserDetails().remove(ioclUserDetail);
		ioclUserDetail.setIoclSupportedUserstatus(null);
		return ioclUserDetail;
	}
}