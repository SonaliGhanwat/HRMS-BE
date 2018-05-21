package com.nextech.hrms.dao;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;

public interface EmployeeDailyTaskDao extends SuperDao<Employeedailytask> {

	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(long empId,Date date)throws Exception;
	
	public List<Employeedailytask> getEmployeeDailyTaskByUserid(long empId) throws Exception;

	public List<Employeedailytask> getEmployeeTaskByEmployeeId(long empId)  throws Exception;
}
