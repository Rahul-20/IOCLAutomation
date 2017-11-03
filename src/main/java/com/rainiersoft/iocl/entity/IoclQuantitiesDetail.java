package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the iocl_quantities_details database table.
 * 
 */
@Entity
@Table(name="iocl_quantities_details")
@NamedQueries({
	@NamedQuery(name="IoclQuantitiesDetail.findAll", query="SELECT i FROM IoclQuantitiesDetail i"),
	@NamedQuery(name="findQuantityByQuantityName", query="SELECT i FROM IoclQuantitiesDetail i where i.quantityName=:quantityName"),
	@NamedQuery(name="findQunatityByQunatity", query="SELECT i FROM IoclQuantitiesDetail i where i.quantity=:quantity"),
	@NamedQuery(name="findQuantityByQuantityId", query="SELECT i FROM IoclQuantitiesDetail i where i.quantityId=:quantityId"),
})
public class IoclQuantitiesDetail implements Serializable {
	public int getQuantityCreatedBy() {
		return quantityCreatedBy;
	}

	public void setQuantityCreatedBy(int quantityCreatedBy) {
		this.quantityCreatedBy = quantityCreatedBy;
	}

	public Date getQuantityCreatedOn() {
		return quantityCreatedOn;
	}

	public void setQuantityCreatedOn(Date quantityCreatedOn) {
		this.quantityCreatedOn = quantityCreatedOn;
	}

	public int getQuantityUpdatedBy() {
		return quantityUpdatedBy;
	}

	public void setQuantityUpdatedBy(int quantityUpdatedBy) {
		this.quantityUpdatedBy = quantityUpdatedBy;
	}

	public Date getQuantityUpdatedOn() {
		return quantityUpdatedOn;
	}

	public void setQuantityUpdatedOn(Date quantityUpdatedOn) {
		this.quantityUpdatedOn = quantityUpdatedOn;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QuantityId")
	private int quantityId;

	@Column(name="QuantityName")
	private String quantityName;

	@Column(name="Quantity")
	private String quantity;

	@Column(name="QuantityUnits")
	private String quantityUnits;

	@Column(name="QuantityCreatedBy")
	private int quantityCreatedBy;

	@Column(name="QuantityCreatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quantityCreatedOn;

	@Column(name="QuantityUpdatedBy")
	private int quantityUpdatedBy;

	@Column(name="QuantityUpdatedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date quantityUpdatedOn;

	//bi-directional many-to-one association to IoclSupportedQuantitystatus
	@ManyToOne
	@JoinColumn(name="QuantityStatusId")
	private IoclSupportedQuantitystatus ioclSupportedQuantitystatus;

	public IoclQuantitiesDetail() {
	}

	public IoclSupportedQuantitystatus getIoclSupportedQuantitystatus() {
		return this.ioclSupportedQuantitystatus;
	}

	public void setIoclSupportedQuantitystatus(IoclSupportedQuantitystatus ioclSupportedQuantitystatus) {
		this.ioclSupportedQuantitystatus = ioclSupportedQuantitystatus;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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