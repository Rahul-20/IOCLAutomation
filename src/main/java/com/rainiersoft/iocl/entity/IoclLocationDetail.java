package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

	@Column(name="city")
	private String city;

	@Column(name="PinCode")
	private String pinCode;

	@Column(name="LocationCreatedBy")
	private int locationCreatedBy;

	@Column(name="LocationCreatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date locationCreatedOn;

	@Column(name="LocationUpdatedBy")
	private int locationUpdatedBy;

	@Column(name="LocationUpdatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date locationUpdatedOn;


	//bi-directional many-to-one association to IoclSupportedLocationstatus
	@ManyToOne
	@JoinColumn(name="LocationStatusId")
	private IoclSupportedLocationstatus ioclSupportedLocationstatus;

	//bi-directional many-to-one association to IoclStatesDetail
	@ManyToOne
	@JoinColumn(name="StateId")
	private IoclStatesDetail ioclStatesDetail;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public IoclStatesDetail getIoclStatesDetail() {
		return ioclStatesDetail;
	}

	public void setIoclStatesDetail(IoclStatesDetail ioclStatesDetail) {
		this.ioclStatesDetail = ioclStatesDetail;
	}
	
	public int getLocationCreatedBy() {
		return this.locationCreatedBy;
	}

	public void setLocationCreatedBy(int locationCreatedBy) {
		this.locationCreatedBy = locationCreatedBy;
	}

	public Date getLocationCreatedOn() {
		return this.locationCreatedOn;
	}

	public void setLocationCreatedOn(Date locationCreatedOn) {
		this.locationCreatedOn = locationCreatedOn;
	}

	public int getLocationUpdatedBy() {
		return this.locationUpdatedBy;
	}

	public void setLocationUpdatedBy(int locationUpdatedBy) {
		this.locationUpdatedBy = locationUpdatedBy;
	}

	public Date getLocationUpdatedOn() {
		return this.locationUpdatedOn;
	}

	public void setLocationUpdatedOn(Date locationUpdatedOn) {
		this.locationUpdatedOn = locationUpdatedOn;
	}
}