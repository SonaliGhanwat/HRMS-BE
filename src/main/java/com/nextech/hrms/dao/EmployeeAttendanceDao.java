package com.nextech.hrms.dao;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employeeattendance;

public interface EmployeeAttendanceDao extends SuperDao<Employeeattendance> {

	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception;
	
	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)throws Exception;
	
    public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date) throws Exception;	
    
    public List<Employeeattendance> getEmployeeattendanceByUserid(long empId) throws Exception;	
    
	public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)throws Exception;
	
	public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandStatus(long empId,String status)throws Exception;
	
	public List<Employeeattendance> getEmployeeattendanceByUseridandHasRead(long empId) throws Exception;
	
	 public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandStatusandHasRead(long empId,String status)throws Exception;
	
}


