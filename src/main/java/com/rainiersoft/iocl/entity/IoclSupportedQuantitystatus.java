package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_quantitystatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_quantitystatus")
@NamedQueries({
	@NamedQuery(name="IoclSupportedQuantitystatus.findAll", query="SELECT i FROM IoclSupportedQuantitystatus i"),
	@NamedQuery(name="findStatusIdByQuantityStatus", query="SELECT i FROM IoclSupportedQuantitystatus i where i.quantityStatus=:quantityStatus")})

public class IoclSupportedQuantitystatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int statusId;

	private String quantityStatus;

	//bi-directional many-to-one association to IoclQuantitiesDetail
	@OneToMany(mappedBy="ioclSupportedQuantitystatus")
	private List<IoclQuantitiesDetail> ioclQuantitiesDetails;

	public IoclSupportedQuantitystatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getQuantityStatus() {
		return this.quantityStatus;
	}

	public void setQuantityStatus(String quantityStatus) {
		this.quantityStatus = quantityStatus;
	}

	public List<IoclQuantitiesDetail> getIoclQuantitiesDetails() {
		return this.ioclQuantitiesDetails;
	}

	public void setIoclQuantitiesDetails(List<IoclQuantitiesDetail> ioclQuantitiesDetails) {
		this.ioclQuantitiesDetails = ioclQuantitiesDetails;
	}
}