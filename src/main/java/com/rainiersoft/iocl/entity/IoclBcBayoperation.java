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
 * The persistent class for the iocl_bc_bayoperations database table.
 * 
 */
@Entity
@Table(name="iocl_bc_bayoperations")
@NamedQueries({
	@NamedQuery(name="IoclBcBayoperation.findAll", query="SELECT i FROM IoclBcBayoperation i"),
	@NamedQuery(name="findBayUpdatesByBC",query="select f from IoclBcBayoperation f where f.BCInputTime > :pastDate and f.BCInputTime < :currDate and f.bayNum=:bayNum"),
	@NamedQuery(name="findBayUpdatesByFanPin",query="select f from IoclBcBayoperation f where f.fanPin=:fanPin"),
	@NamedQuery(name="findTopBayUpdatesByBC",query="select f from IoclBcBayoperation f where f.BCInputTime > :pastDate and f.BCInputTime < :currDate and f.bayNum=:bayNum order by BCInputTime desc")
})
public class IoclBcBayoperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	@Column(name="BayNum")
	private int bayNum;

	@Column(name="BcControllerId")
	private String bcControllerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BCInputTime")
	private Date BCInputTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BCUpdateTime")
	private Date BCUpdateTime;

	@Column(name="FanPin")
	private String fanPin;

	@ManyToOne
	@JoinColumn(name="OperationalStatusId")
	private IoclSupportedBayoperationalstatus ioclSupportedBayoperationalstatus;

	public IoclBcBayoperation() {
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public int getBayNum() {
		return this.bayNum;
	}

	public void setBayNum(int bayNum) {
		this.bayNum = bayNum;
	}

	public String getBcControllerId() {
		return this.bcControllerId;
	}

	public void setBcControllerId(String bcControllerId) {
		this.bcControllerId = bcControllerId;
	}

	public Date getBCInputTime() {
		return this.BCInputTime;
	}

	public void setBCInputTime(Date BCInputTime) {
		this.BCInputTime = BCInputTime;
	}

	public Date getBCUpdateTime() {
		return this.BCUpdateTime;
	}

	public void setBCUpdateTime(Date BCUpdateTime) {
		this.BCUpdateTime = BCUpdateTime;
	}

	public String getFanPin() {
		return this.fanPin;
	}

	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}

	public IoclSupportedBayoperationalstatus getIoclSupportedBayoperationalstatus() {
		return ioclSupportedBayoperationalstatus;
	}

	public void setIoclSupportedBayoperationalstatus(IoclSupportedBayoperationalstatus ioclSupportedBayoperationalstatus) {
		this.ioclSupportedBayoperationalstatus = ioclSupportedBayoperationalstatus;
	}
}