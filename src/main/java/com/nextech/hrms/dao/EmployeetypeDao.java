package com.nextech.hrms.dao;


import com.nextech.hrms.model.Employeetype;

public interface EmployeetypeDao extends SuperDao<Employeetype> {
	 public Employeetype getEmployeetypeByUserid(long empId) throws Exception;	
		

}
