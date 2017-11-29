package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLCommentsDAO;
import com.rainiersoft.iocl.entity.IoclComments;

@Repository
public class IOCLCommentsDAOImpl  extends GenericDAOImpl<IoclComments, Long> implements IOCLCommentsDAO
{
	@Override
	public List<IoclComments> findCommentsByType(String commentType) 
	{
		Session session = getCurrentSession();
		Query query = session.getNamedQuery("findCommentsByType");
		query.setParameter("commentType",commentType);
		List<IoclComments> ioclComments=findObjectCollection(query);
		return ioclComments;
	}
}
