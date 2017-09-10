package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_location_details database table.
 * 
 */
@Entity
@Table(name="iocl_location_details")
@NamedQueries({
	@NamedQuery(name="IoclLocationDetail.findAll", query="SELECT i FROM IoclLocationDetail i"),
	@NamedQuery(name="findLocationIdByLocationCode", query="SELECT i FROM IoclLocationDetail i where i.locationCode=:locationCode"),
	@NamedQuery(name="findLocationByLocationName", query="SELECT i FROM IoclLocationDetail i where i.locationName=:locationName"),
	@NamedQuery(name="findLocationByLocationId", query="SELECT i FROM IoclLocationDetail i where i.locationID=:locationId")
})
public class IoclLocationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="LocationID")
	private int locationID;

	@Column(name="LocationCode")
	private String locationCode;

	@Column(name="LocationName")
	private String locationName;

	@Column(name="Address")
	private String locationAddress;

	//bi-directional many-to-one association to IoclSupportedLocationstatus
	@ManyToOne
	@JoinColumn(name="LocationStatusId")
	private IoclSupportedLocationstatus ioclSupportedLocationstatus;

	public IoclLocationDetail() {
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public IoclSupportedLocationstatus getIoclSupportedLocationstatus() {
		return this.ioclSupportedLocationstatus;
	}

	public void setIoclSupportedLocationstatus(IoclSupportedLocationstatus ioclSupportedLocationstatus) {
		this.ioclSupportedLocationstatus = ioclSupportedLocationstatus;
	}

	public int getLocationID() {
		return this.locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}