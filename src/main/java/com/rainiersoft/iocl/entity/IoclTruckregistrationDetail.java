package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_truckregistration_details database table.
 * 
 */
@Entity
@Table(name="iocl_truckregistration_details")
@NamedQueries({
@NamedQuery(name="IoclTruckregistrationDetail.findAll", query="SELECT i FROM IoclTruckregistrationDetail i"),
@NamedQuery(name="findTruckByTruckNo", query="SELECT ioclTruckregistrationDetail FROM IoclTruckregistrationDetail ioclTruckregistrationDetail where truckNo=:truckNo")
})
public class IoclTruckregistrationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int truckId;

	private String cutomer;

	private String destination;

	private String driverLicNo;

	private String driverName;

	private String locationCode;

	private String mobileNumber;

	private String quantity;

	private String truckNo;

	private String vehicleWgt;

	public IoclTruckregistrationDetail() {
	}

	public int getTruckId() {
		return this.truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public String getCutomer() {
		return this.cutomer;
	}

	public void setCutomer(String cutomer) {
		this.cutomer = cutomer;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDriverLicNo() {
		return this.driverLicNo;
	}

	public void setDriverLicNo(String driverLicNo) {
		this.driverLicNo = driverLicNo;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTruckNo() {
		return this.truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getVehicleWgt() {
		return this.vehicleWgt;
	}

	public void setVehicleWgt(String vehicleWgt) {
		this.vehicleWgt = vehicleWgt;
	}

}