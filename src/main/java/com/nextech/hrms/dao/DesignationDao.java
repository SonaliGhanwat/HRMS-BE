package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Designation;

public interface DesignationDao extends SuperDao<Designation> {
	
	 public List<Designation> getDesignationByDepartmentid(long departmentid) throws Exception;

}
