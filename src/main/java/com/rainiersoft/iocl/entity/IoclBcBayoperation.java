package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_bc_bayoperations database table.
 * 
 */
@Entity
@Table(name="iocl_bc_bayoperations")
@NamedQuery(name="IoclBcBayoperation.findAll", query="SELECT i FROM IoclBcBayoperation i")
public class IoclBcBayoperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int bayId;

	private String bayName;

	private String operationalStatus;

	public IoclBcBayoperation() {
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

	public String getOperationalStatus() {
		return this.operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

}