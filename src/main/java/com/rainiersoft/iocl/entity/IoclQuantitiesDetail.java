package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the iocl_quantities_details database table.
 * 
 */
@Entity
@Table(name="iocl_quantities_details")
@NamedQuery(name="IoclQuantitiesDetail.findAll", query="SELECT i FROM IoclQuantitiesDetail i")
public class IoclQuantitiesDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int quantityId;

	private String quantityName;

	private String quantityUnits;

	public IoclQuantitiesDetail() {
	}

	public int getQuantityId() {
		return this.quantityId;
	}

	public void setQuantityId(int quantityId) {
		this.quantityId = quantityId;
	}

	public String getQuantityName() {
		return this.quantityName;
	}

	public void setQuantityName(String quantityName) {
		this.quantityName = quantityName;
	}

	public String getQuantityUnits() {
		return this.quantityUnits;
	}

	public void setQuantityUnits(String quantityUnits) {
		this.quantityUnits = quantityUnits;
	}

}