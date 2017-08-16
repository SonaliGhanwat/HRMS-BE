package com.nextech.hrms.services;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.model.Employeedailytask;

public interface EmployeeDailyTaskServices extends CRUDService<Employeedailytask> {
	
	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(long empId,Date date)throws Exception;

	public EmployeeDailyTaskDto getEmployeeDailyTaskDto(long id) throws Exception;
	 
	public List<EmployeeDailyTaskDto> getEmployeeDailyTaskDtoList(List<EmployeeDailyTaskDto> employeeDailyTaskDtos)throws Exception;

	public EmployeeDailyTaskDto getEmployeeDailyTaskDtoByid(long id)throws Exception;
}

