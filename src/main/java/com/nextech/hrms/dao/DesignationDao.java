package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Designation;

public interface DesignationDao extends SuperDao<Designation> {
	
	 public List<Designation> getDesignationByUserTypeid(long usertypeid) throws Exception;

}
