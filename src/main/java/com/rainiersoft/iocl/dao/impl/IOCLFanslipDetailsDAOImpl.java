package com.rainiersoft.iocl.dao.impl;

import com.rainiersoft.iocl.dao.IOCLFanslipDetailsDAO;
import com.rainiersoft.iocl.entity.IoclFanslipDetail;
import com.rainiersoft.iocl.entity.IoclLocationDetail;
import com.rainiersoft.iocl.entity.IoclSupportedPinstatus;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Singleton
public class IOCLFanslipDetailsDAOImpl extends GenericDAOImpl<IoclFanslipDetail, Long> implements IOCLFanslipDetailsDAO
{
	private static final Logger LOG = LoggerFactory.getLogger(IOCLFanslipDetailsDAOImpl.class);

	public IOCLFanslipDetailsDAOImpl() {}

	public void insertFanSlipDetails(int bayNo, String fanPin, IoclSupportedPinstatus fanPinStatusId, int truckID, Date createdOn, String quantity, String vehicleWgt, String destination, IoclLocationDetail locationId) 
	{
		IoclFanslipDetail ioclFanslipDetail = new IoclFanslipDetail();
		ioclFanslipDetail.setBayNo(bayNo);
		ioclFanslipDetail.setFanPin(fanPin);
		ioclFanslipDetail.setTruckId(truckID);

		LOG.info("CreatecOn::::::::" + createdOn);
		ioclFanslipDetail.setFanCreationOn(createdOn);
		ioclFanslipDetail.setQuantity(quantity);
		ioclFanslipDetail.setVehicleWgt(vehicleWgt);
		ioclFanslipDetail.setDestination(destination);
		ioclFanslipDetail.setIoclLocationDetail(locationId);
		ioclFanslipDetail.setIoclSupportedPinstatus(fanPinStatusId);
		saveOrUpdate(ioclFanslipDetail);
	}


	public List<IoclFanslipDetail> findAnyBayIsAssignedInPast(int bayNo, Date currDate, Date pastDate)
	{
		LOG.info("Bay Nums::::" + bayNo);
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findAnyBayIsAssignedInPast");
		LOG.info("query:" + query);
		query.setParameter("bayNo", Integer.valueOf(bayNo));
		query.setParameter("currDate", currDate);
		query.setParameter("pastDate", pastDate);
		List<IoclFanslipDetail> ioclFanslipDetail = findObjectCollection(query);
		return ioclFanslipDetail;
	}


	public IoclFanslipDetail findFanPinStatusByFanPin(String fanPin)
	{
		LOG.info("fanPin::::" + fanPin);
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findFanPinStatusByFanPin");
		LOG.info("query:" + query);
		query.setParameter("fanPin", fanPin);
		IoclFanslipDetail ioclFanslipDetail = (IoclFanslipDetail)findObject(query);
		return ioclFanslipDetail;
	}
}
