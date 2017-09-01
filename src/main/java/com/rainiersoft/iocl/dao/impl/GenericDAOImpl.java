package com.rainiersoft.iocl.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.GenericDAO;

/**
 * This is a Implementation class for generic methods used across
 * all other DAO Implemention classes.
 * @author Rahul Kumar Pamidi
 * 
 */

@SuppressWarnings("unchecked")
@Repository
@Singleton
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T,ID> {

	private static final Logger LOG = LoggerFactory.getLogger(GenericDAOImpl.class.getName());


	protected SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		LOG.info("getSessionFactory::::::::"+sessionFactory);
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		LOG.info("setSessionFactory::::::::"+sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	public GenericDAOImpl(){

	}


	protected Session getCurrentSession() {

		Session session=null;
		try
		{
			session=getSessionFactory().getCurrentSession();
		}
		catch(Exception exp)
		{
			LOG.info("EXP::::::::::"+exp);
		}
		return session;
	}

	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}


	public void merge(T entity) {
		getCurrentSession().merge(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public List<T> findObjectCollection(Query query) {
		List<T> t;
		t = (List<T>) query.list();
		return t;
	}

	public T findObject(Query query) {
		T t;
		t = (T) query.uniqueResult();
		return t;
	}


	public List<T> findObjectCollection(String hqlQuery) {
		List<T> t;
		Query query = getCurrentSession().createQuery(hqlQuery);
		t = (List<T>)query.list();
		return t;
	}

	public T findObject(String hqlQuery) {
		T t;
		t = (T) getCurrentSession().createQuery(hqlQuery).uniqueResult();
		return t;
	}

	public List<T> findAll(Class<T> entity) {
		List<T> t= null;
		Query query = getCurrentSession().createQuery("from " + entity.getName());
		t = (List<T>)query.list();
		return t;
	}

	public List<T> search(T entity,Map<String, Object> parameterMap) {
		Criteria criteria = getCurrentSession().createCriteria(entity.getClass());
		Set<String> fieldName = parameterMap.keySet();
		for (String field : fieldName) {
			criteria.add(Restrictions.ilike(field, parameterMap.get(field)));
		}
		return (List<T>)criteria.list();
	}

	public void clearCacheForEntity(T entity) {
		getCurrentSession().evict(entity);
	}

	public boolean deleteById(Class<?> type, Serializable id) {
		Object persistentInstance = getCurrentSession().load(type, id);
		if (persistentInstance != null) {
			getCurrentSession().delete(persistentInstance);
			return true;
		}
		return false;
	}
}
