package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_bay_details database table.
 * 
 */
@Entity
@Table(name="iocl_bay_details")
@NamedQuery(name="IoclBayDetail.findAll", query="SELECT i FROM IoclBayDetail i")
public class IoclBayDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int bayId;

	private String bayName;

	private String bayNum;

	private String bayType;

	private String bcControllerId;

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

	public String getBayNum() {
		return this.bayNum;
	}

	public void setBayNum(String bayNum) {
		this.bayNum = bayNum;
	}

	public String getBayType() {
		return this.bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}

	public String getBcControllerId() {
		return this.bcControllerId;
	}

	public void setBcControllerId(String bcControllerId) {
		this.bcControllerId = bcControllerId;
	}

}