package com.rainiersoft.iocl.dao.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLQuantityDetailsDAO;
import com.rainiersoft.iocl.entity.IoclQuantitiesDetail;
import com.rainiersoft.iocl.entity.IoclSupportedQuantitystatus;

@Repository
@Singleton
public class IOCLQuantityDetailsDAOImpl extends GenericDAOImpl<IoclQuantitiesDetail, Long> implements IOCLQuantityDetailsDAO 
{
	@Override
	public List<IoclQuantitiesDetail> findAllQuantites() 
	{
		List<IoclQuantitiesDetail> listOfIoclQuantitiesDetail = findAll(IoclQuantitiesDetail.class);
		return listOfIoclQuantitiesDetail;
	}

	@Override
	public IoclQuantitiesDetail findQuantityByQuantityName(String quantityName) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findQuantityByQuantityName");
		query.setParameter("quantityName",quantityName);
		IoclQuantitiesDetail ioclQuantitiesDetail = (IoclQuantitiesDetail)findObject(query);
		return ioclQuantitiesDetail;
	}

	@Override
	public IoclQuantitiesDetail findQuantityByQunatity(String quantity) {
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findQunatityByQunatity");
		query.setParameter("quantity",quantity);
		IoclQuantitiesDetail ioclQuantitiesDetail = (IoclQuantitiesDetail)findObject(query);
		return ioclQuantitiesDetail;
	}

	@Override
	public Long insertQuantitiesDetails(String quantityName, String quantity, IoclSupportedQuantitystatus quantityStatus,int userID,Date createdOn) 
	{
		Session session=getCurrentSession();
		IoclQuantitiesDetail ioclQuantitiesDetail=new IoclQuantitiesDetail();
		ioclQuantitiesDetail.setIoclSupportedQuantitystatus(quantityStatus);
		ioclQuantitiesDetail.setQuantity(quantity);
		ioclQuantitiesDetail.setQuantityName(quantityName);
		ioclQuantitiesDetail.setQuantityCreatedBy(userID);
		ioclQuantitiesDetail.setQuantityCreatedOn(createdOn);
		Integer quantityId=(Integer) session.save(ioclQuantitiesDetail);
		return quantityId.longValue();
	}

	@Override
	public IoclQuantitiesDetail findQuantityByQuantityId(int quantityId) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findQuantityByQuantityId");
		query.setParameter("quantityId",quantityId);
		IoclQuantitiesDetail ioclQuantitiesDetail = (IoclQuantitiesDetail)findObject(query);
		return ioclQuantitiesDetail;
	}

	@Override
	public void updateQuantitiesDetails(String quantityName, String quantity,
			IoclSupportedQuantitystatus quantityStatus, IoclQuantitiesDetail ioclQuantitiesDetail,int userID,Date updatedOn) {
		Session session=getCurrentSession();
		ioclQuantitiesDetail.setIoclSupportedQuantitystatus(quantityStatus);
		ioclQuantitiesDetail.setQuantity(quantity);
		ioclQuantitiesDetail.setQuantityName(quantityName);
		ioclQuantitiesDetail.setQuantityUpdatedBy(userID);
		ioclQuantitiesDetail.setQuantityUpdatedOn(updatedOn);
		session.update(ioclQuantitiesDetail);
	}

	@Override
	public boolean deleteQunatity(int quantityId) {
		return deleteById(IoclQuantitiesDetail.class,quantityId);
	}
}
