package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.model.Employeeattendance;

public class EmployeeAttendanceFactory {
	
	public static Employeeattendance setEmployeeAttendance(EmployeeAttendanceDto employeeAttendanceDto)throws Exception{
		Employeeattendance employeeattendance = new Employeeattendance();
		employeeattendance.setId(employeeAttendanceDto.getId());
		employeeattendance.setEmployee(employeeAttendanceDto.getEmployee());
		employeeattendance.setIntime(employeeAttendanceDto.getIntime());
		employeeattendance.setOuttime(employeeAttendanceDto.getOuttime());
		employeeattendance.setTotaltime(employeeAttendanceDto.getTotaltime());
		employeeattendance.setDate(employeeAttendanceDto.getDate());
		employeeattendance.setStatus(employeeAttendanceDto.getStatus());
		employeeattendance.setCreatedDate(employeeAttendanceDto.getCreatedDate());
		employeeattendance.setUpdatedDate(employeeAttendanceDto.getUpdatedDate());
		employeeattendance.setIsActive(true);
		return employeeattendance;
	}
	
	public  static Employeeattendance setEmployeeAttendanceUpdate(EmployeeAttendanceDto employeeAttendanceDto)throws Exception {
		Employeeattendance employeeattendance = new Employeeattendance();
		employeeattendance.setId(employeeAttendanceDto.getId());
		employeeattendance.setEmployee(employeeAttendanceDto.getEmployee());
		employeeattendance.setIntime(employeeAttendanceDto.getIntime());
		employeeattendance.setOuttime(employeeAttendanceDto.getOuttime());
		employeeattendance.setTotaltime(employeeAttendanceDto.getTotaltime());
		employeeattendance.setDate(employeeAttendanceDto.getDate());
		employeeattendance.setStatus(employeeAttendanceDto.getStatus());
		employeeattendance.setCreatedDate(employeeAttendanceDto.getCreatedDate());
		employeeattendance.setUpdatedDate(employeeAttendanceDto.getUpdatedDate());
		employeeattendance.setIsActive(true);
		return employeeattendance;
	}

	
	public static EmployeeAttendanceDto setEmployeeAttendanceList(Employeeattendance employeeattendance)throws Exception{
		EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
		employeeAttendanceDto.setId(employeeattendance.getId());
		employeeAttendanceDto.setEmployee(employeeattendance.getEmployee());
		employeeAttendanceDto.setIntime(employeeattendance.getIntime());
		employeeAttendanceDto.setOuttime(employeeattendance.getOuttime());
		employeeAttendanceDto.setTotaltime(employeeattendance.getTotaltime());
		employeeAttendanceDto.setDate(employeeattendance.getDate());
		employeeAttendanceDto.setStatus(employeeattendance.getStatus());
		employeeAttendanceDto.setCreatedDate(employeeattendance.getCreatedDate());
		employeeAttendanceDto.setUpdatedDate(employeeattendance.getUpdatedDate());
		employeeAttendanceDto.setIsActive(true);
		return employeeAttendanceDto;
	}

}
