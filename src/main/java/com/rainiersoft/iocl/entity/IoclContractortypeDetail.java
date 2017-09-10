package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_contractortype_details database table.
 * 
 */
@Entity
@Table(name="iocl_contractortype_details")
@NamedQueries({
	@NamedQuery(name="IoclContractortypeDetail.findAll", query="SELECT i FROM IoclContractortypeDetail i"),
	@NamedQuery(name="findContractorIdByContractorType", query="SELECT i FROM IoclContractortypeDetail i where i.contractorType=:contractorType")
})
public class IoclContractortypeDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int contractorTypeId;

	private String contractorType;

	//bi-directional many-to-one association to IoclContractorDetail
	@OneToMany(mappedBy="ioclContractortypeDetail")
	private List<IoclContractorDetail> ioclContractorDetails;

	public IoclContractortypeDetail() {
	}

	public int getContractorTypeId() {
		return this.contractorTypeId;
	}

	public void setContractorTypeId(int contractorTypeId) {
		this.contractorTypeId = contractorTypeId;
	}

	public String getContractorType() {
		return this.contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	public List<IoclContractorDetail> getIoclContractorDetails() {
		return this.ioclContractorDetails;
	}

	public void setIoclContractorDetails(List<IoclContractorDetail> ioclContractorDetails) {
		this.ioclContractorDetails = ioclContractorDetails;
	}
}