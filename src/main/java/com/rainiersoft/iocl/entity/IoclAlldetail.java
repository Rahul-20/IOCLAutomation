package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iocl_alldetails database table.
 * 
 */
@Entity
@Table(name="iocl_alldetails")
@NamedQuery(name="IoclAlldetail.findAll", query="SELECT i FROM IoclAlldetail i")
public class IoclAlldetail implements Serializable {
	@Override
	public String toString() {
		return "IoclAlldetail [fanslipnum=" + fanslipnum + ", bayNo=" + bayNo + ", bccompletedtime=" + bccompletedtime
				+ ", bcinputtime=" + bcinputtime + ", comments=" + comments + ", contractorName=" + contractorName
				+ ", destination=" + destination + ", fanCreatedBy=" + fanCreatedBy + ", fanCreationOn=" + fanCreationOn
				+ ", fanExpirationOn=" + fanExpirationOn + ", fanPin=" + fanPin + ", fanPinStatus=" + fanPinStatus
				+ ", fanUpdatedBy=" + fanUpdatedBy + ", fanUpdatedOn=" + fanUpdatedOn + ", loadedQuantity="
				+ loadedQuantity + ", locationName=" + locationName + ", preset=" + preset + ", quantity=" + quantity
				+ ", startTime=" + startTime + ", totalizerendvalue=" + totalizerendvalue + ", totalizerstartvalue="
				+ totalizerstartvalue + ", truckNo=" + truckNo + ", vehicleWgt=" + vehicleWgt + ", customer=" + customer
				+ "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	private int fanslipnum;

	private int bayNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bccompletedtime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date bcinputtime;

	private String comments;

	private String contractorName;

	private String destination;

	private int fanCreatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fanCreationOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fanExpirationOn;

	private String fanPin;

	private String fanPinStatus;

	private int fanUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fanUpdatedOn;

	private String loadedQuantity;

	private String locationName;

	private String preset;

	private String quantity;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	private String totalizerendvalue;

	private String totalizerstartvalue;

	private String truckNo;

	private String vehicleWgt;
	
	private String customer;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public IoclAlldetail() {
	}

	public int getBayNo() {
		return this.bayNo;
	}

	public void setBayNo(int bayNo) {
		this.bayNo = bayNo;
	}

	public Date getBccompletedtime() {
		return this.bccompletedtime;
	}

	public void setBccompletedtime(Date bccompletedtime) {
		this.bccompletedtime = bccompletedtime;
	}

	public Date getBcinputtime() {
		return this.bcinputtime;
	}

	public void setBcinputtime(Date bcinputtime) {
		this.bcinputtime = bcinputtime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getFanCreatedBy() {
		return this.fanCreatedBy;
	}

	public void setFanCreatedBy(int fanCreatedBy) {
		this.fanCreatedBy = fanCreatedBy;
	}

	public Date getFanCreationOn() {
		return this.fanCreationOn;
	}

	public void setFanCreationOn(Date fanCreationOn) {
		this.fanCreationOn = fanCreationOn;
	}

	public Date getFanExpirationOn() {
		return this.fanExpirationOn;
	}

	public void setFanExpirationOn(Date fanExpirationOn) {
		this.fanExpirationOn = fanExpirationOn;
	}

	public String getFanPin() {
		return this.fanPin;
	}

	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}

	public String getFanPinStatus() {
		return this.fanPinStatus;
	}

	public void setFanPinStatus(String fanPinStatus) {
		this.fanPinStatus = fanPinStatus;
	}

	public int getFanslipnum() {
		return this.fanslipnum;
	}

	public void setFanslipnum(int fanslipnum) {
		this.fanslipnum = fanslipnum;
	}

	public int getFanUpdatedBy() {
		return this.fanUpdatedBy;
	}

	public void setFanUpdatedBy(int fanUpdatedBy) {
		this.fanUpdatedBy = fanUpdatedBy;
	}

	public Date getFanUpdatedOn() {
		return this.fanUpdatedOn;
	}

	public void setFanUpdatedOn(Date fanUpdatedOn) {
		this.fanUpdatedOn = fanUpdatedOn;
	}

	public String getLoadedQuantity() {
		return this.loadedQuantity;
	}

	public void setLoadedQuantity(String loadedQuantity) {
		this.loadedQuantity = loadedQuantity;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getPreset() {
		return this.preset;
	}

	public void setPreset(String preset) {
		this.preset = preset;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getTotalizerendvalue() {
		return this.totalizerendvalue;
	}

	public void setTotalizerendvalue(String totalizerendvalue) {
		this.totalizerendvalue = totalizerendvalue;
	}

	public String getTotalizerstartvalue() {
		return this.totalizerstartvalue;
	}

	public void setTotalizerstartvalue(String totalizerstartvalue) {
		this.totalizerstartvalue = totalizerstartvalue;
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