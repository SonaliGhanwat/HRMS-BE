package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Employee;

public interface EmployeeDao extends SuperDao<Employee>{
	
	public Employee  getEmployeeByUserId(String userId) throws Exception;
	
	public Employee getEmployeeByphoneNumber(long phoneNumber)throws Exception;
	
	public Employee getEmpolyeeByEmailid(String emailId)throws Exception;
	
	public List<Employee> getDesignationById(long id) throws Exception;	
	
	public Employee  getEmployeeByUserIdforLeave(long userId) throws Exception;
	
	public List<Employee> getEmployeeByReportTo(int id) throws Exception;
}
