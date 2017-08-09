package com.nextech.hrms.services;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employeeattendance;

public interface EmployeeAttendanceServices extends CRUDService<Employeeattendance> {
	
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception;
	
	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)throws Exception;
	
    public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date) throws Exception;	

    
}
