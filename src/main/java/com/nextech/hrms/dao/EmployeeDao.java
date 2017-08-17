package com.nextech.hrms.dao;

import com.nextech.hrms.model.Employee;

public interface EmployeeDao extends SuperDao<Employee>{
	
	public Employee  getEmployeeByUserId(String userId) throws Exception;
	
	public Employee getEmployeeByphoneNumber(long phoneNumber)throws Exception;
	
	public Employee getEmpolyeeByEmailid(String emailId)throws Exception;
}
