package com.nextech.hrms.dao;
import java.util.Date;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;

public interface EmployeeLeaveDao {

	public boolean addEntity(Employeeleave employeeleave) throws Exception;
	
	public Employeeleave getEntityById(long id) throws Exception;
	
	public List<Employeeleave> getEntityList() throws Exception;
	
	public boolean deleteEntity(long id) throws Exception;
	
	public boolean updateEntity(Employeeleave employeeleave) throws Exception;
	
	public boolean updateEntity(long id) throws Exception;
	
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception;
	
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId) throws Exception;
	
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(long empId,Date date) throws Exception;
	
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date) throws Exception;	
	
}
