package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_contractor_details database table.
 * 
 */
@Entity
@Table(name="iocl_contractor_details")
@NamedQuery(name="IoclContractorDetail.findAll", query="SELECT i FROM IoclContractorDetail i")
public class IoclContractorDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecID")
	private int recID;

	@Column(name="ContractorId")
	private int contractorId;

	@Column(name="ContractorLocation")
	private String contractorLocation;

	@Column(name="ContractorName")
	private String contractorName;

	@Column(name="ContractorType")
	private String contractorType;

	@Column(name="OperationalStatus")
	private String operationalStatus;

	public IoclContractorDetail() {
	}

	public int getRecID() {
		return this.recID;
	}

	public void setRecID(int recID) {
		this.recID = recID;
	}

	public int getContractorId() {
		return this.contractorId;
	}

	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorLocation() {
		return this.contractorLocation;
	}

	public void setContractorLocation(String contractorLocation) {
		this.contractorLocation = contractorLocation;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getContractorType() {
		return this.contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}

	public String getOperationalStatus() {
		return this.operationalStatus;
	}

	public void setOperationalStatus(String operationalStatus) {
		this.operationalStatus = operationalStatus;
	}
}