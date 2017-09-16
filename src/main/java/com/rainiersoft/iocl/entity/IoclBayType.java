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

	@Override
	public String toString() {
		return "IoclBayType [recId=" + recId + ", bayTypeId=" + bayTypeId + ", ioclBayDetail=]";
	}

	private static final long serialVersionUID = 100000000001L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	@Column(name="BayTypeId")
	private int bayTypeId;

	//bi-directional many-to-one association to IoclBayDetail
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="bayId",referencedColumnName = "bayId")
	private IoclBayDetail ioclBayDetail;

	public IoclBayType() {
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public int getBayTypeId() {
		return this.bayTypeId;
	}

	public void setBayTypeId(int bayTypeId) {
		this.bayTypeId = bayTypeId;
	}

	public IoclBayDetail getIoclBayDetail() {
		return this.ioclBayDetail;
	}

	public void setIoclBayDetail(IoclBayDetail ioclBayDetail) {
		this.ioclBayDetail = ioclBayDetail;
	}
}