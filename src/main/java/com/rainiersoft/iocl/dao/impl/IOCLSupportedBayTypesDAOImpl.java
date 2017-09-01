package com.rainiersoft.iocl.dao.impl;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLSupportedBayTypesDAO;
import com.rainiersoft.iocl.entity.IoclSupportedBaytype;

@Repository
@Singleton
public class IOCLSupportedBayTypesDAOImpl extends GenericDAOImpl<IoclSupportedBaytype, Long> implements IOCLSupportedBayTypesDAO
{
  public IOCLSupportedBayTypesDAOImpl() {}
  
  public List<IoclSupportedBaytype> findAllSupportedBayTypes()
  {
    List<IoclSupportedBaytype> listOfTypes = findAll(IoclSupportedBaytype.class);
    return listOfTypes;
  }
}
