package com.rainiersoft.iocl.dao.impl;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import com.rainiersoft.iocl.dao.IOCLBayDetailsDAO;
import com.rainiersoft.iocl.dao.IOCLBayTypeDAO;
import com.rainiersoft.iocl.entity.IoclBayDetail;
import com.rainiersoft.iocl.entity.IoclBayType;

@Repository
@Singleton
public class IOCLBayTypeDAOImpl extends GenericDAOImpl<IoclBayType, Long> implements IOCLBayTypeDAO
{

}
