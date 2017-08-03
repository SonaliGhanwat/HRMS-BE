package com.nextech.hrms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Employee;

public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	EmployeeDao employeeDao;
	
	@Override
	public boolean addEntity(Employee employee) throws Exception {
		return employeeDao.addEntity(employee);
	}

	@Override
	public Employee getEntityById(long id) throws Exception {
		return employeeDao.getEntityById(id);
	}

	@Override
	public List<Employee> getEntityList() throws Exception {
		return employeeDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return employeeDao.deleteEntity(id);
	}
	@Override
	public boolean updateEntity(Employee employee) throws Exception {
		return employeeDao.updateEntity(employee);
	}

	@Override
	public Employee getEmployeeByUserId(String userId) throws Exception {
		return employeeDao.getEmployeeByUserId(userId);
	}

	@Override
	public Employee getEmployeeByphoneNumber(String phoneNumber) throws Exception {
		return employeeDao.getEmployeeByphoneNumber(phoneNumber);
		
	}

	@Override
	public Employee getEmpolyeeByEmailid(String emailId) throws Exception {
		return employeeDao.getEmpolyeeByEmailid(emailId);
	}

}
