package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_bay_types database table.
 * 
 */
@Entity
@Table(name="iocl_bay_types")
@NamedQuery(name="IoclBayType.findAll", query="SELECT i FROM IoclBayType i")
public class IoclBayType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	@Column(name="BayType")
	private String bayType;

	//bi-directional many-to-one association to IoclBayDetail
	@ManyToOne
	@JoinColumn(name="bayNum")
	private IoclBayDetail ioclBayDetail;

	public IoclBayType() {
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getBayType() {
		return this.bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}

	public IoclBayDetail getIoclBayDetail() {
		return this.ioclBayDetail;
	}

	public void setIoclBayDetail(IoclBayDetail ioclBayDetail) {
		this.ioclBayDetail = ioclBayDetail;
	}
}