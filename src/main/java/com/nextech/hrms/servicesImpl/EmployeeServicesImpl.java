package com.nextech.hrms.servicesImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.services.EmployeeServices;

public class EmployeeServicesImpl extends CRUDServiceImpl<Employee> implements EmployeeServices {

	@Autowired
	EmployeeDao employeeDao;

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
