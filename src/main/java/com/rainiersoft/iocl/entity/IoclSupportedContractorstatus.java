package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_contractorstatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_contractorstatus")
@NamedQueries
({
	@NamedQuery(name="IoclSupportedContractorstatus.findAll", query="SELECT i FROM IoclSupportedContractorstatus i"),
	@NamedQuery(name="findStatusIdByContractorStatus",query="SELECT i FROM IoclSupportedContractorstatus i where i.contractorStatus=:contractorStatus")
})
public class IoclSupportedContractorstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int statusId;

	private String contractorStatus;

	//bi-directional many-to-one association to IoclContractorDetail
	@OneToMany(mappedBy="ioclSupportedContractorstatus")
	private List<IoclContractorDetail> ioclContractorDetails;

	public IoclSupportedContractorstatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getContractorStatus() {
		return this.contractorStatus;
	}

	public void setContractorStatus(String contractorStatus) {
		this.contractorStatus = contractorStatus;
	}

	public List<IoclContractorDetail> getIoclContractorDetails() {
		return this.ioclContractorDetails;
	}

	public void setIoclContractorDetails(List<IoclContractorDetail> ioclContractorDetails) {
		this.ioclContractorDetails = ioclContractorDetails;
	}
}