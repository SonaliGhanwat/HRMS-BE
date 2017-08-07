package com.nextech.hrms.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.services.EmployeeLeaveServices;

public class EmployeeLeaveServicesImpl implements EmployeeLeaveServices {

	@Autowired
	EmployeeLeaveDao employeeLeaveDao;
	
	@Override
	public boolean addEntity(Employeeleave employeeleave) throws Exception {
		return employeeLeaveDao.addEntity(employeeleave);
	}

	@Override
	public Employeeleave getEntityById(long id) throws Exception {
		return employeeLeaveDao.getEntityById(id);
	}

	@Override
	public List<Employeeleave> getEntityList() throws Exception {
		return employeeLeaveDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return employeeLeaveDao.deleteEntity(id);
	}
	@Override
	public boolean updateEntity(Employeeleave employeeleave) throws Exception {
		return employeeLeaveDao.updateEntity(employeeleave);
	}

	@Override
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception {
		return employeeLeaveDao.getEmpolyeeleaveByIdandDate(empId, date);
	}

	@Override
	public boolean updateEntity(long id) throws Exception {
		return employeeLeaveDao.updateEntity(id);
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
