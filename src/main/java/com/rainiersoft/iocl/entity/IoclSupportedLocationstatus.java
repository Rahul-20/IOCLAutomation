package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_locationstatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_locationstatus")
@NamedQueries(
		{
			@NamedQuery(name="IoclSupportedLocationstatus.findAll", query="SELECT i FROM IoclSupportedLocationstatus i"),
			@NamedQuery(name="findStatusIdByLocationStatus", query="SELECT i FROM IoclSupportedLocationstatus i where i.locationStatus=:locationStatus")
		})
public class IoclSupportedLocationstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int statusId;

	private String locationStatus;

	//bi-directional many-to-one association to IoclLocationDetail
	@OneToMany(mappedBy="ioclSupportedLocationstatus")
	private List<IoclLocationDetail> ioclLocationDetails;

	public IoclSupportedLocationstatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getLocationStatus() {
		return this.locationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	public List<IoclLocationDetail> getIoclLocationDetails() {
		return this.ioclLocationDetails;
	}

	public void setIoclLocationDetails(List<IoclLocationDetail> ioclLocationDetails) {
		this.ioclLocationDetails = ioclLocationDetails;
	}
}