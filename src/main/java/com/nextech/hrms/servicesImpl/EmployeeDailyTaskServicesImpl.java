package com.nextech.hrms.servicesImpl;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.services.EmployeeDailyTaskServices;

public class EmployeeDailyTaskServicesImpl implements EmployeeDailyTaskServices {

	@Autowired
	EmployeeDailyTaskDao employeeDailyTaskDao;
	
	@Override
	public boolean addEntity(Employeedailytask employeedailytask) throws Exception {
		return employeeDailyTaskDao.addEntity(employeedailytask);
	}

	@Override
	public Employeedailytask getEntityById(long id) throws Exception {
		return employeeDailyTaskDao.getEntityById(id);
	}

	@Override
	public List<Employeedailytask> getEntityList() throws Exception {
		return employeeDailyTaskDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return employeeDailyTaskDao.deleteEntity(id);
	}
	@Override
	public boolean updateEntity(Employeedailytask employeedailytask) throws Exception {
		return employeeDailyTaskDao.updateEntity(employeedailytask);
	}

	

}

