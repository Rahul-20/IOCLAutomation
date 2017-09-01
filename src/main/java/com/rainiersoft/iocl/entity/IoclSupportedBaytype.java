package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_supported_baytypes database table.
 * 
 */
@Entity
@Table(name="iocl_supported_baytypes")
@NamedQuery(name="IoclSupportedBaytype.findAll", query="SELECT i FROM IoclSupportedBaytype i")
public class IoclSupportedBaytype implements Serializable {
	private static final long serialVersionUID = 99910389120L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	@Column(name="TypeId")
	private int typeId;

	@Column(name="BayType")
	private String bayType;


	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public IoclSupportedBaytype() {
	}

	public String getBayType() {
		return this.bayType;
	}

	public void setBayType(String bayType) {
		this.bayType = bayType;
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}
}