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
 * The persistent class for the iocl_fanslip_details database table.
 * 
 */
@Entity
@Table(name="iocl_fanslip_details")
@NamedQueries({
	@NamedQuery(name="IoclFanslipDetail.findAll", query="SELECT i FROM IoclFanslipDetail i"),
	@NamedQuery(name="findFanPinStatusByFanPin", query="SELECT i FROM IoclFanslipDetail i where fanPin=:fanPin"),
	@NamedQuery(name="findFanSlipDetailsByFanPinAndBayNum", query="SELECT i FROM IoclFanslipDetail i where fanPin=:fanPin and bayNo=:bayNo"),
	@NamedQuery(name="findFanPinByFanId", query="SELECT i FROM IoclFanslipDetail i where fanId=:fanId"),
	@NamedQuery(name="findAnyBayIsAssignedInPast",query="select f from IoclFanslipDetail f where f.fanCreationOn >= :pastDate and f.fanCreationOn <= :currDate and f.bayNo=:bayNo"),
	@NamedQuery(name="findAllLatestFanSlips",query="select f from IoclFanslipDetail f where f.fanCreationOn>= :pastDate and f.fanCreationOn<= :currDate")	
})
public class IoclFanslipDetail implements Serializable {
	@Override
	public String toString() {
		return "IoclFanslipDetail [fanId=" + fanId + ", bayNo=" + bayNo + ", destination=" + destination
				+ ", fanCreationOn=" + fanCreationOn + ", fanExpirationOn=" + fanExpirationOn + ", fanPin=" + fanPin
				+ ", quantity=" + quantity + ", truckId=" + truckId + ", vehicleWgt=" + vehicleWgt + ", fanUpdatedBy="
				+ fanUpdatedBy + ", fanCreatedBy=" + fanCreatedBy + ", fanUpdatedOn=" + fanUpdatedOn
				+ ", ioclContractorDetail=" + ioclContractorDetail + ", ioclLocationDetail=" + ioclLocationDetail
				+ ", ioclSupportedPinstatus=" + ioclSupportedPinstatus + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FanId")
	private int fanId;

	@Column(name="BayNo")
	private int bayNo;

	@Column(name="Destination")
	private String destination;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FanCreationOn")
	private Date fanCreationOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FanExpirationOn")
	private Date fanExpirationOn;

	@Column(name="FanPin")
	private String fanPin;

	@Column(name="Quantity")
	private String quantity;

	@Column(name="TruckId")
	private int truckId;

	@Column(name="VehicleWgt")
	private String vehicleWgt;

	@Column(name="FanUpdatedBy")
	private int fanUpdatedBy;

	@Column(name="FanCreatedBy")
	private int fanCreatedBy;

	@Column(name="FanUpdatedOn")
	private Date fanUpdatedOn;
	
	@Column(name="Comments")
	private String comments;

	//bi-directional many-to-one association to IoclContractorDetail
	@ManyToOne
	@JoinColumn(name="ContractorID")
	private IoclContractorDetail ioclContractorDetail;

	//bi-directional many-to-one association to IoclLocationDetail
	@ManyToOne
	@JoinColumn(name="LocationID")
	private IoclLocationDetail ioclLocationDetail;

	//bi-directional many-to-one association to IoclContractorDetail
	@ManyToOne
	@JoinColumn(name="QuantityID")
	private IoclQuantitiesDetail ioclQuantitiesDetail;

	//bi-directional many-to-one association to IoclSupportedPinstatus
	@ManyToOne
	@JoinColumn(name="FANPinStatusId")
	private IoclSupportedPinstatus ioclSupportedPinstatus;

	public IoclFanslipDetail() {
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getFanId() {
		return this.fanId;
	}

	public void setFanId(int fanId) {
		this.fanId = fanId;
	}

	public int getBayNo() {
		return this.bayNo;
	}

	public void setBayNo(int bayNo) {
		this.bayNo = bayNo;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getTruckId() {
		return this.truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public String getVehicleWgt() {
		return this.vehicleWgt;
	}

	public void setVehicleWgt(String vehicleWgt) {
		this.vehicleWgt = vehicleWgt;
	}

	public IoclLocationDetail getIoclLocationDetail() {
		return this.ioclLocationDetail;
	}

	public void setIoclLocationDetail(IoclLocationDetail ioclLocationDetail) {
		this.ioclLocationDetail = ioclLocationDetail;
	}

	public IoclSupportedPinstatus getIoclSupportedPinstatus() {
		return this.ioclSupportedPinstatus;
	}

	public void setIoclSupportedPinstatus(IoclSupportedPinstatus ioclSupportedPinstatus) {
		this.ioclSupportedPinstatus = ioclSupportedPinstatus;
	}

	public IoclContractorDetail getIoclContractorDetail() {
		return this.ioclContractorDetail;
	}

	public void setIoclContractorDetail(IoclContractorDetail ioclContractorDetail) {
		this.ioclContractorDetail = ioclContractorDetail;
	}
	
	public IoclQuantitiesDetail getIoclQuantitiesDetail() {
		return ioclQuantitiesDetail;
	}

	public void setIoclQuantitiesDetail(IoclQuantitiesDetail ioclQuantitiesDetail) {
		this.ioclQuantitiesDetail = ioclQuantitiesDetail;
	}

	public Date getFanExpirationOn() {
		return this.fanExpirationOn;
	}

	public void setFanExpirationOn(Date fanExpirationOn) {
		this.fanExpirationOn = fanExpirationOn;
	}

	public int getFanUpdatedBy() {
		return fanUpdatedBy;
	}

	public void setFanUpdatedBy(int fanUpdatedBy) {
		this.fanUpdatedBy = fanUpdatedBy;
	}

	public int getFanCreatedBy() {
		return fanCreatedBy;
	}

	public void setFanCreatedBy(int fanCreatedBy) {
		this.fanCreatedBy = fanCreatedBy;
	}

	public Date getFanUpdatedOn() {
		return fanUpdatedOn;
	}

	public void setFanUpdatedOn(Date fanUpdatedOn) {
		this.fanUpdatedOn = fanUpdatedOn;
	}
}