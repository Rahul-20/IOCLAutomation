package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the iocl_bc_audit_bayoperations database table.
 * 
 */
@Entity
@Table(name="iocl_bc_audit_bayoperations")
@NamedQuery(name="IoclBcAuditBayoperation.findAll", query="SELECT i FROM IoclBcAuditBayoperation i")
public class IoclBcAuditBayoperation implements Serializable {
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

	@Column(name="FanPin")
	private String fanPin;

	@Column(name="OperationalStatus")
	private String operationalStatus;

	public IoclBcAuditBayoperation() {
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

	public String getFanPin() {
		return this.fanPin;
	}

	public void setFanPin(String fanPin) {
		this.fanPin = fanPin;
	}

	public String getOperationalStatus() {
		return this.operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}