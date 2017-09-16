package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_supported_bayoperationalstatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_bayoperationalstatus")
@NamedQuery(name="IoclSupportedBayoperationalstatus.findAll", query="SELECT i FROM IoclSupportedBayoperationalstatus i")
public class IoclSupportedBayoperationalstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int statusId;

	private String operationalStatus;

	public IoclSupportedBayoperationalstatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getOperationalStatus() {
		return this.operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}

}