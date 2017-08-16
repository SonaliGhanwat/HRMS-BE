package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.model.Employeedailytask;

public class EmployeeDailyTaskFactory {
	public static Employeedailytask setEmployeeDailyTask(EmployeeDailyTaskDto employeeDailyTaskDto)throws Exception{
		Employeedailytask employeedailytask = new Employeedailytask();
		employeedailytask.setId(employeeDailyTaskDto.getId());
		employeedailytask.setEmployee(employeeDailyTaskDto.getEmployee());
		employeedailytask.setDate(employeeDailyTaskDto.getDate());
		employeedailytask.setTaskName(employeeDailyTaskDto.getTaskName());
		employeedailytask.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
		employeedailytask.setStarttime(employeeDailyTaskDto.getStarttime());
		employeedailytask.setEndtime(employeeDailyTaskDto.getEndtime());
		employeedailytask.setTakenTime(employeeDailyTaskDto.getTakenTime());
		employeedailytask.setCreatedDate(employeeDailyTaskDto.getCreatedDate());
		employeedailytask.setUpdatedDate(employeeDailyTaskDto.getUpdatedDate());
		employeedailytask.setIsActive(true);
		return employeedailytask;
	}
	public static EmployeeDailyTaskDto setEmployeeDailyTaskList(Employeedailytask employeedailytask)throws Exception{
		EmployeeDailyTaskDto employeeDailyTaskDto = new EmployeeDailyTaskDto();
		employeeDailyTaskDto.setId(employeedailytask.getId());
		employeeDailyTaskDto.setEmployee(employeedailytask.getEmployee());
		employeeDailyTaskDto.setDate(employeedailytask.getDate());
		employeeDailyTaskDto.setTaskName(employeedailytask.getTaskName());
		employeeDailyTaskDto.setEstimationTime(employeedailytask.getEstimationTime());
		employeeDailyTaskDto.setStarttime(employeedailytask.getStarttime());
		employeeDailyTaskDto.setEndtime(employeedailytask.getEndtime());
		employeeDailyTaskDto.setTakenTime(employeedailytask.getTakenTime());
		employeeDailyTaskDto.setCreatedDate(employeedailytask.getCreatedDate());
		employeeDailyTaskDto.setUpdatedDate(employeedailytask.getUpdatedDate());
		employeeDailyTaskDto.setIsActive(true);
		return employeeDailyTaskDto;
		
	}
	public  static Employeedailytask setEmployeeDailyTaskUpdate(EmployeeDailyTaskDto employeeDailyTaskDto)throws Exception {
		Employeedailytask employeedailytask = new Employeedailytask();
		employeedailytask.setId(employeeDailyTaskDto.getId());
		employeedailytask.setEmployee(employeeDailyTaskDto.getEmployee());
		employeedailytask.setDate(employeeDailyTaskDto.getDate());
		employeedailytask.setTaskName(employeeDailyTaskDto.getTaskName());
		employeedailytask.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
		employeedailytask.setStarttime(employeeDailyTaskDto.getStarttime());
		employeedailytask.setEndtime(employeeDailyTaskDto.getEndtime());
		employeedailytask.setTakenTime(employeeDailyTaskDto.getTakenTime());
		employeedailytask.setCreatedDate(employeeDailyTaskDto.getCreatedDate());
		employeedailytask.setUpdatedDate(employeeDailyTaskDto.getUpdatedDate());
		employeedailytask.setIsActive(true);
		return employeedailytask;
	}


}
