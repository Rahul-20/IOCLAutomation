package com.rainiersoft.iocl.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the iocl_userrole_mapping database table.
 * 
 */
@Entity
@Table(name="iocl_userrole_mapping")
/*@NamedQuery(name="findRolesByUserID", query="SELECT irole FROM IoclUserroleMapping irole INNER JOIN on IoclUserDetail iuser")*/
public class IoclUserroleMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RecId")
	private int recId;

	@Column(name="UserType")
	private String userType;

	//bi-directional many-to-one association to IoclUserDetail
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="UserId")
	private IoclUserDetail ioclUserDetail;

	public IoclUserroleMapping() {
	}

	public int getRecId() {
		return this.recId;
	}

	public void setRecId(int recId) {
		this.recId = recId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public IoclUserDetail getIoclUserDetail() {
		return this.ioclUserDetail;
	}

	public void setIoclUserDetail(IoclUserDetail ioclUserDetail) {
		this.ioclUserDetail = ioclUserDetail;
	}

}