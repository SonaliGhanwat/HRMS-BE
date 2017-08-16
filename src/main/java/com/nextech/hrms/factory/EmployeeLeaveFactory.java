package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.model.Employeeleave;

public class EmployeeLeaveFactory {
	public static Employeeleave setEmployeeleave(EmployeeLeaveDto employeeLeaveDto)throws Exception{
		Employeeleave employeeleave = new Employeeleave();
		employeeleave.setId(employeeLeaveDto.getId());
		employeeleave.setEmployee(employeeLeaveDto.getEmployee());
		employeeleave.setSubject(employeeLeaveDto.getSubject());
		employeeleave.setLeavedate(employeeLeaveDto.getLeavedate());
		employeeleave.setAfterleavejoiningdate(employeeLeaveDto.getAfterleavejoiningdate());
		employeeleave.setCreatedDate(employeeLeaveDto.getCreatedDate());
		employeeleave.setUpdatedDate(employeeLeaveDto.getUpdatedDate());
		employeeleave.setIsActive(true);
		return employeeleave;
		
	}
	public static  EmployeeLeaveDto setEmployeeLeaveList(Employeeleave employeeleave)throws Exception{
		EmployeeLeaveDto employeeLeaveDto = new EmployeeLeaveDto();
		employeeLeaveDto.setId(employeeleave.getId());
		employeeLeaveDto.setEmployee(employeeleave.getEmployee());
		employeeLeaveDto.setSubject(employeeleave.getSubject());
		employeeLeaveDto.setLeavedate(employeeleave.getLeavedate());
		employeeLeaveDto.setAfterleavejoiningdate(employeeleave.getAfterleavejoiningdate());
		employeeLeaveDto.setCreatedDate(employeeleave.getCreatedDate());
		employeeLeaveDto.setUpdatedDate(employeeleave.getUpdatedDate());
		employeeLeaveDto.setIsActive(true);
		return employeeLeaveDto;
		
	}
	public static Employeeleave setEmployeeLeaveUpdate(EmployeeLeaveDto employeeLeaveDto)throws Exception{
		Employeeleave employeeleave = new Employeeleave();
		employeeleave.setId(employeeLeaveDto.getId());
		employeeleave.setEmployee(employeeLeaveDto.getEmployee());
		employeeleave.setSubject(employeeLeaveDto.getSubject());
		employeeleave.setLeavedate(employeeLeaveDto.getLeavedate());
		employeeleave.setAfterleavejoiningdate(employeeLeaveDto.getAfterleavejoiningdate());
		employeeleave.setCreatedDate(employeeLeaveDto.getCreatedDate());
		employeeleave.setUpdatedDate(employeeLeaveDto.getUpdatedDate());
		employeeleave.setIsActive(true);
		return employeeleave;
	}

}
