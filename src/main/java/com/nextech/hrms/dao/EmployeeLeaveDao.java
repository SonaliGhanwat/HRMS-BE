package com.nextech.hrms.dao;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;

public interface EmployeeLeaveDao extends SuperDao<Employeeleave>{

	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception;
	
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId) throws Exception;
	
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(long empId,Date date) throws Exception;
	
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date) throws Exception;	
	
	public List <Employeeleave> getEmployeeLeaveListByDate(Date date) throws Exception;
	
	public List<Employeeleave> getEmployeeLeaveByUserid(long empId) throws Exception;
	
	public List<Employeeleave> getEmployeeLeaveByLeaveTypeid(long leaveid) throws Exception;
}
