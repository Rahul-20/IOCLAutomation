package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_baystatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_baystatus")
@NamedQueries({
@NamedQuery(name="IoclSupportedBaystatus.findAll", query="SELECT i FROM IoclSupportedBaystatus i"),
@NamedQuery(name="findStatusIdByStatus", query="SELECT i FROM IoclSupportedBaystatus i where i.bayFunctionalStatus=:bayStatus")})
public class IoclSupportedBaystatus implements Serializable {
	@Override
	public String toString() {
		return "IoclSupportedBaystatus [statusId=" + statusId + ", bayFunctionalStatus=" + bayFunctionalStatus
				+ "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="StatusId")
	private int statusId;

	@Column(name="BayFunctionalStatus")
	private String bayFunctionalStatus;

	//bi-directional many-to-one association to IoclBayDetail
	@OneToMany(mappedBy="ioclSupportedBaystatus")
	private List<IoclBayDetail> ioclBayDetails;

	public IoclSupportedBaystatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getBayFunctionalStatus() {
		return this.bayFunctionalStatus;
	}

	public void setBayFunctionalStatus(String bayFunctionalStatus) {
		this.bayFunctionalStatus = bayFunctionalStatus;
	}

	public List<IoclBayDetail> getIoclBayDetails() {
		return this.ioclBayDetails;
	}

	public void setIoclBayDetails(List<IoclBayDetail> ioclBayDetails) {
		this.ioclBayDetails = ioclBayDetails;
	}
}