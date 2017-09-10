package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_states_details database table.
 * 
 */
@Entity
@Table(name="iocl_states_details")
@NamedQueries({
	@NamedQuery(name="IoclStatesDetail.findAll", query="SELECT i FROM IoclStatesDetail i"),
	@NamedQuery(name="findStateIdByStateName", query="SELECT i FROM IoclStatesDetail i where i.stateName=:stateName")
})
public class IoclStatesDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stateId;

	private String stateName;

	//bi-directional many-to-one association to IoclContractorDetail
	@OneToMany(mappedBy="ioclStatesDetail")
	private List<IoclContractorDetail> ioclContractorDetails;

	public IoclStatesDetail() {
	}

	public int getStateId() {
		return this.stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<IoclContractorDetail> getIoclContractorDetails() {
		return this.ioclContractorDetails;
	}

	public void setIoclContractorDetails(List<IoclContractorDetail> ioclContractorDetails) {
		this.ioclContractorDetails = ioclContractorDetails;
	}
}