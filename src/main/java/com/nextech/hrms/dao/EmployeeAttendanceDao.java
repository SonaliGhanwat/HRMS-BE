package com.nextech.hrms.dao;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employeeattendance;

public interface EmployeeAttendanceDao {

	public boolean addEntity(Employeeattendance employeeattendance) throws Exception;
	
	public Employeeattendance getEntityById(long id) throws Exception;
	
	public List<Employeeattendance> getEntityList() throws Exception;
	
	public boolean deleteEntity(long id) throws Exception;
	
	public boolean updateEntity(Employeeattendance employeeattendance) throws Exception;
	
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception;
	
	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)throws Exception;
	
    public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date) throws Exception;	
	
	
}


