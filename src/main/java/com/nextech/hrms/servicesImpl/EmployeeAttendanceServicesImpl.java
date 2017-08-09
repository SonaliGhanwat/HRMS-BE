package com.nextech.hrms.servicesImpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;

public class EmployeeAttendanceServicesImpl extends CRUDServiceImpl<Employeeattendance> implements EmployeeAttendanceServices {

	@Autowired
	EmployeeAttendanceDao employeeAttendanceDao;
	
	@Override
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception {
		return employeeAttendanceDao.getEmpolyeeAttendanceByIdandDate(empId, date);
	}

	@Override
	public List<Employeeattendance>  calculateEmployeeAttendanceByEmployeeIdandDate(
			long empId, Date date) throws Exception {
		return employeeAttendanceDao.calculateEmployeeAttendanceByEmployeeIdandDate(empId, date);
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date)
			throws Exception {
		return employeeAttendanceDao.getEmployeeattendanceByCurrentDate(date);
	}



}
