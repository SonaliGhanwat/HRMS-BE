package com.nextech.hrms.services;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.dto.EmployeeDailyTaskDto;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;

public interface EmployeeDailyTaskServices extends CRUDService<Employeedailytask> {
	
	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(long empId,Date date)throws Exception;

	public EmployeeDailyTaskDto getEmployeeDailyTaskDto(long id) throws Exception;
	 
	public List<EmployeeDailyTaskDto> getEmployeeDailyTaskDtoList(List<EmployeeDailyTaskDto> employeeDailyTaskDtos)throws Exception;

	public EmployeeDailyTaskDto getEmployeeDailyTaskDtoByid(long id)throws Exception;
	
	public void addEmployeeDailyTaskExcel(List<EmployeeDailyTaskDto> employeeDailyTaskDtos)throws Exception;
	
	public List<Employeedailytask> getEmployeeDailyTaskByUserid(long empId) throws Exception;
	
	public List<Employeedailytask> getEmployeeTaskByEmployeeId(long empId)  throws Exception;
	
	public List<Employeedailytask> getEmployeeDailyTaskByUseridandHasRead(long empId) throws Exception;
}

