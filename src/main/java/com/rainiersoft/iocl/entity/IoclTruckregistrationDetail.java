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
@NamedQuery(name="findTruckByTruckNo", query="SELECT ioclTruckregistrationDetail FROM IoclTruckregistrationDetail ioclTruckregistrationDetail where truckNo=:truckNo"),
@NamedQuery(name="findTruckByTruckId", query="SELECT ioclTruckregistrationDetail FROM IoclTruckregistrationDetail ioclTruckregistrationDetail where truckId=:truckId")
})
public class IoclTruckregistrationDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TruckId")
	private int truckId;

	@Column(name="Customer")
	private String customer;

	@Column(name="DriverLicNo")
	private String driverLicNo;

	@Column(name="DriverName")
	private String driverName;

	@Column(name="MobileNumber")
	private String mobileNumber;

	@Column(name="TruckNo")
	private String truckNo;

	public IoclTruckregistrationDetail() {
	}

	public int getTruckId() {
		return this.truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTruckNo() {
		return this.truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
}