package com.nextech.hrms.servicesImpl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.services.EmployeeLeaveServices;

public class EmployeeLeaveServicesImpl extends CRUDServiceImpl<Employeeleave> implements EmployeeLeaveServices {

	@Autowired
	EmployeeLeaveDao employeeLeaveDao;

	@Override
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception {
		return employeeLeaveDao.getEmpolyeeleaveByIdandDate(empId, date);
	}

	@Override
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId)
			throws Exception {
		return employeeLeaveDao.getYearlyEmployeeLeaveByEmployeeId(empId);
	}

	@Override
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(
			long empId, Date date) throws Exception {
		return employeeLeaveDao.getMonthlyEmployeeLeaveByEmployeeId(empId, date);
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date)
			throws Exception {
		return employeeLeaveDao.getEmployeeLeaveByCurrentDate(date);
	}

}
