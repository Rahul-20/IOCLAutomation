package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	//bi-directional many-to-one association to IoclSupportedBaystatus
	@ManyToOne
	@JoinColumn(name="FunctionalStatusId")
	private IoclSupportedBaystatus ioclSupportedBaystatus;

	//bi-directional many-to-one association to IoclBayType
	@OneToMany(mappedBy="ioclBayDetail",cascade=CascadeType.ALL)
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
}