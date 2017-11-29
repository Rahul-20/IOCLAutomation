package com.rainiersoft.iocl.dao;

import java.util.List;

import com.rainiersoft.iocl.entity.IoclComments;

public interface IOCLCommentsDAO extends GenericDAO<IoclComments, Long>
{
	public List<IoclComments> findCommentsByType(String commentType);
}
