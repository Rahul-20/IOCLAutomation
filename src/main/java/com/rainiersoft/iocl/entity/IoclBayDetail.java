package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the iocl_bay_details database table.
 * 
 */
@Entity
@Table(name="iocl_bay_details")
@NamedQueries({
@NamedQuery(name="IoclBayDetail.findAll", query="SELECT i FROM IoclBayDetail i"),
@NamedQuery(name="findBayByBayNum", query="select i from IoclBayDetail i where i.bayNum=:bayNum"),
@NamedQuery(name="findBayByBayId", query="select i from IoclBayDetail i where i.bayId=:bayId"),
@NamedQuery(name="findBayByBayName", query="select i from IoclBayDetail i where i.bayName=:bayName")
})
public class IoclBayDetail implements Serializable {
	
	@Override
	public String toString() {
		return "IoclBayDetail [bayId=" + bayId + ", bayName=" + bayName + ", bayNum=" + bayNum
				+ ", ioclSupportedBaystatus=" + ioclSupportedBaystatus + ", ioclBayTypes=" + ioclBayTypes + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BayId")
	private int bayId;

	@Column(name="BayName")
	private String bayName;

	@Column(name="BayNum")
	private int bayNum;
	
	@Column(name="BayCreatedBy")
	private int bayCreatedBy;
	
	@Column(name="BayUpdatedBy")
	private int bayUpdatedBy;

	@Column(name="BayCreatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bayCreatedOn;
	
	@Column(name="BayUpdatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date bayUpdatedOn;

	//bi-directional many-to-one association to IoclSupportedBaystatus
	@ManyToOne
	@JoinColumn(name="FunctionalStatusId")
	private IoclSupportedBaystatus ioclSupportedBaystatus;

	//bi-directional many-to-one association to IoclBayType
	@OneToMany(mappedBy="ioclBayDetail",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<IoclBayType> ioclBayTypes;

	public IoclBayDetail() {
	}

	public int getBayId() {
		return this.bayId;
	}

	public void setBayId(int bayId) {
		this.bayId = bayId;
	}

	public String getBayName() {
		return this.bayName;
	}

	public void setBayName(String bayName) {
		this.bayName = bayName;
	}

	public int getBayNum() {
		return this.bayNum;
	}

	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}

	public IoclSupportedBaystatus getIoclSupportedBaystatus() {
		return this.ioclSupportedBaystatus;
	}

	public void setIoclSupportedBaystatus(IoclSupportedBaystatus ioclSupportedBaystatus) {
		this.ioclSupportedBaystatus = ioclSupportedBaystatus;
	}

	public List<IoclBayType> getIoclBayTypes() {
		return this.ioclBayTypes;
	}

	public void setIoclBayTypes(List<IoclBayType> ioclBayTypes) {
		this.ioclBayTypes = ioclBayTypes;
	}
	
	public int getBayCreatedBy() {
		return this.bayCreatedBy;
	}

	public void setBayCreatedBy(int bayCreatedBy) {
		this.bayCreatedBy = bayCreatedBy;
	}

	public Date getBayCreatedOn() {
		return this.bayCreatedOn;
	}

	public void setBayCreatedOn(Date bayCreatedOn) {
		this.bayCreatedOn = bayCreatedOn;
	}
	
	public int getBayUpdatedBy() {
		return this.bayUpdatedBy;
	}

	public void setBayUpdatedBy(int bayUpdatedBy) {
		this.bayUpdatedBy = bayUpdatedBy;
	}

	public Date getBayUpdatedOn() {
		return this.bayUpdatedOn;
	}

	public void setBayUpdatedOn(Date bayUpdatedOn) {
		this.bayUpdatedOn = bayUpdatedOn;
	}
}