package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employeeattendance;

public interface DesignationService extends CRUDService<Designation> {
	
	 public List<Designation> getDesignationByUserTypeid(long usertypeid) throws Exception;

}
