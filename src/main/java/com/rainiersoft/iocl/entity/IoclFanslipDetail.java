package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iocl_fanslip_details database table.
 * 
 */
@Entity
@Table(name="iocl_fanslip_details")
@NamedQuery(name="IoclFanslipDetail.findAll", query="SELECT i FROM IoclFanslipDetail i")
public class IoclFanslipDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int fanId;

	private String bayNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fanCreationOn;

	private String fanPin;

	private String FANPinStatus;

	private int truckId;

	public IoclFanslipDetail() {
	}

	public int getFanId() {
		return this.fanId;
	}

	public void setFanId(int fanId) {
		this.fanId = fanId;
	}

	public String getBayNo() {
		return this.bayNo;
	}

	public void setBayNo(String bayNo) {
		this.bayNo = bayNo;
	}

	public Date getFanCreationOn() {
		return this.fanCreationOn;
	}

	public void setFanCreationOn(Date fanCreationOn) {
		this.fanCreationOn = fanCreationOn;
	}

	public String getFanPin() {
		return this.fanPin;
	}

	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}

	public String getFANPinStatus() {
		return this.FANPinStatus;
	}

	public void setFANPinStatus(String FANPinStatus) {
		this.FANPinStatus = FANPinStatus;
	}

	public int getTruckId() {
		return this.truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

}