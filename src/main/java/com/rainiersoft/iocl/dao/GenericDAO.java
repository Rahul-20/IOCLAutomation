package com.rainiersoft.iocl.dao;

import java.io.Serializable;

/**
 * This is a Base Interface for generic methods used across
 * all other DAO Implemention classes.
 * @author Rahul Kumar Pamidi
 * 
 */

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

public interface GenericDAO<T,ID> 
{
	public void saveOrUpdate(T entity);
	public void merge(T entity);
	public void delete(T entity);
	public List<T> findObjectCollection(Query query);
	public T findObject(Query query);
	public List<T> findObjectCollection(String hqlQuery);
	public T findObject(String hqlQuery);
	public List<T> findAll(Class<T> entity) ;
	public List<T> search(T entity,Map<String, Object> parameterMap) ;
	public void clearCacheForEntity(T entity);
	public boolean deleteById(Class<?> type, Serializable id);
}
