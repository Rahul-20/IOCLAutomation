package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the iocl_contractor_details database table.
 * 
 */
@Entity
@Table(name="iocl_contractor_details")
@NamedQueries({
	@NamedQuery(name="IoclContractorDetail.findAll", query="SELECT i FROM IoclContractorDetail i"),
	@NamedQuery(name="findContractorByContractorName", query="SELECT i FROM IoclContractorDetail i where i.contractorName=:contractorName"),
	@NamedQuery(name="findContractorByContractorId", query="SELECT i FROM IoclContractorDetail i where i.contractorId=:contractorId"),
	@NamedQuery(name="findAllContractorTypes", query="SELECT i FROM IoclContractorDetail i"),
	@NamedQuery(name="findAllContractorStates", query="SELECT i FROM IoclContractorDetail i")
})
public class IoclContractorDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ContractorId")
	private int contractorId;

	@Column(name="ContractorAddress")
	private String contractorAddress;

	@Column(name="ContractorCity")
	private String contractorCity;

	/*@Column(name="ContractorState")
	private String contractorState;*/

	@Column(name="ZipCode")
	private String zipCode;

	@Column(name="ContractorName")
	private String contractorName;

	/*@Column(name="ContractorType")
	private String contractorType;
	 */

	//bi-directional many-to-one association to IoclSupportedContractorstatus
	@ManyToOne
	@JoinColumn(name="ContractorStatusId")
	private IoclSupportedContractorstatus ioclSupportedContractorstatus;

	//bi-directional many-to-one association to IoclContractortypeDetail
	@ManyToOne
	@JoinColumn(name="ContractorTypeId")
	private IoclContractortypeDetail ioclContractortypeDetail;

	//bi-directional many-to-one association to IoclStatesDetail
	@ManyToOne
	@JoinColumn(name="ContractorStateId")
	private IoclStatesDetail ioclStatesDetail;


	public IoclContractorDetail() {
	}

	public String getContractorAddress() {
		return contractorAddress;
	}

	public void setContractorAddress(String contractorAddress) {
		this.contractorAddress = contractorAddress;
	}

	public String getContractorCity() {
		return contractorCity;
	}

	public void setContractorCity(String contractorCity) {
		this.contractorCity = contractorCity;
	}

	/*	public String getContractorState() {
		return contractorState;
	}

	public void setContractorState(String contractorState) {
		this.contractorState = contractorState;
	}
	 */
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getContractorId() {
		return this.contractorId;
	}

	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}

	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	/*public String getContractorType() {
		return this.contractorType;
	}

	public void setContractorType(String contractorType) {
		this.contractorType = contractorType;
	}
	 */
	public IoclSupportedContractorstatus getIoclSupportedContractorstatus() {
		return this.ioclSupportedContractorstatus;
	}

	public void setIoclSupportedContractorstatus(IoclSupportedContractorstatus ioclSupportedContractorstatus) {
		this.ioclSupportedContractorstatus = ioclSupportedContractorstatus;
	}

	public IoclContractortypeDetail getIoclContractortypeDetail() {
		return this.ioclContractortypeDetail;
	}

	public void setIoclContractortypeDetail(IoclContractortypeDetail ioclContractortypeDetail) {
		this.ioclContractortypeDetail = ioclContractortypeDetail;
	}

	public IoclStatesDetail getIoclStatesDetail() {
		return this.ioclStatesDetail;
	}

	public void setIoclStatesDetail(IoclStatesDetail ioclStatesDetail) {
		this.ioclStatesDetail = ioclStatesDetail;
	}
}