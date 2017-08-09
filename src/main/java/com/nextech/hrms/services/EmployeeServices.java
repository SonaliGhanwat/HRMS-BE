package com.nextech.hrms.services;
import java.util.List;

import com.nextech.hrms.model.Employee;

public interface EmployeeServices extends CRUDService<Employee> {
	
	
	public Employee  getEmployeeByUserId(String userId) throws Exception;
	
	public Employee getEmployeeByphoneNumber(String phoneNumber)throws Exception;
	
	public Employee getEmpolyeeByEmailid(String emailId)throws Exception;
	
}
