package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the iocl_supported_pinstatus database table.
 * 
 */
@Entity
@Table(name="iocl_supported_pinstatus")
@NamedQueries({
@NamedQuery(name="IoclSupportedPinstatus.findAll", query="SELECT i FROM IoclSupportedPinstatus i"),
@NamedQuery(name="findStatusIdByPinStatus", query="SELECT i FROM IoclSupportedPinstatus i where i.fanPinStatus=:fanPinStatus")
})
public class IoclSupportedPinstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="StatusId")
	private int statusId;

	@Column(name="FanPinStatus")
	private String fanPinStatus;

	//bi-directional many-to-one association to IoclFanslipDetail
	@OneToMany(mappedBy="ioclSupportedPinstatus")
	private List<IoclFanslipDetail> ioclFanslipDetails;

	public IoclSupportedPinstatus() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getFanPinStatus() {
		return this.fanPinStatus;
	}

	public void setFanPinStatus(String fanPinStatus) {
		this.fanPinStatus = fanPinStatus;
	}

	public List<IoclFanslipDetail> getIoclFanslipDetails() {
		return this.ioclFanslipDetails;
	}

	public void setIoclFanslipDetails(List<IoclFanslipDetail> ioclFanslipDetails) {
		this.ioclFanslipDetails = ioclFanslipDetails;
	}

	public IoclFanslipDetail addIoclFanslipDetail(IoclFanslipDetail ioclFanslipDetail) {
		getIoclFanslipDetails().add(ioclFanslipDetail);
		ioclFanslipDetail.setIoclSupportedPinstatus(this);
		return ioclFanslipDetail;
	}

	public IoclFanslipDetail removeIoclFanslipDetail(IoclFanslipDetail ioclFanslipDetail) {
		getIoclFanslipDetails().remove(ioclFanslipDetail);
		ioclFanslipDetail.setIoclSupportedPinstatus(null);
		return ioclFanslipDetail;
	}
}