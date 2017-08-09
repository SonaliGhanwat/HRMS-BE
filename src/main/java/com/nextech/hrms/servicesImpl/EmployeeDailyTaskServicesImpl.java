package com.nextech.hrms.servicesImpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.services.EmployeeDailyTaskServices;

public class EmployeeDailyTaskServicesImpl extends CRUDServiceImpl<Employeedailytask> implements EmployeeDailyTaskServices {

	@Autowired
	EmployeeDailyTaskDao employeeDailyTaskDao;

	@Override
	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(
			long empId, Date date) throws Exception {
		
		return employeeDailyTaskDao.getEmployeeDailytaskByEmployeeIdandCurrentDate(empId, date);
	}
	
	
	

}

