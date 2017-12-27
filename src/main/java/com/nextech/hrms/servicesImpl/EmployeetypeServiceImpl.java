package com.nextech.hrms.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.dao.EmployeetypeDao;
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.services.EmployeetypeService;

@Service
public class EmployeetypeServiceImpl extends CRUDServiceImpl<Employeetype> implements EmployeetypeService{

	
	@Autowired
	EmployeetypeDao employeetypeDao;
	
	@Override
	public Employeetype getEmployeetypeByUserid(long empId)
			throws Exception {
		// TODO Auto-generated method stub
		return employeetypeDao.getEmployeetypeByUserid(empId);
	}

}
