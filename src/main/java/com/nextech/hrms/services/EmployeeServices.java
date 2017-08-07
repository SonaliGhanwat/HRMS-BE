package com.nextech.hrms.services;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employee;

public interface EmployeeServices {
	
	public boolean addEntity(Employee employee) throws Exception;
	
	public Employee getEntityById(long id) throws Exception;
	
	public List<Employee> getEntityList() throws Exception;
	
	public boolean deleteEntity(long id) throws Exception;
	
	public boolean updateEntity(Employee employee) throws Exception;
	
	public Employee  getEmployeeByUserId(String userId) throws Exception;
	
	public Employee getEmployeeByphoneNumber(String phoneNumber)throws Exception;
	
	public Employee getEmpolyeeByEmailid(String emailId)throws Exception;
	
}
