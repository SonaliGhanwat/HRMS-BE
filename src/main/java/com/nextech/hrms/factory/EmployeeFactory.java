package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.Dto.EmployeeDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;

public class EmployeeFactory {
	
	public static Employee setEmployee(EmployeeDto employeeDto)throws Exception{
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setUserid(employeeDto.getUserid());
		employee.setPassword(employeeDto.getPassword());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmailid(employeeDto.getEmailid());
		employee.setDateOfJoining(employeeDto.getDateOfJoining());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setAddress(employeeDto.getAddress());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setSalary(employeeDto.getSalary());
		employee.setUsertype(employeeDto.getUsertype());
		employee.setCreatedDate(employeeDto.getCreatedDate());
		employee.setUpdatedDate(employeeDto.getUpdatedDate());
		employee.setIsActive(true);
		return employee;
		
	}
	public static EmployeeDto setEmployeeList(Employee employee)throws Exception{
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setUserid(employee.getUserid());
		employeeDto.setPassword(employee.getPassword());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setEmailid(employee.getEmailid());
		employeeDto.setDateOfJoining(employee.getDateOfJoining());
		employeeDto.setDateOfBirth(employee.getDateOfBirth());
		employeeDto.setAddress(employee.getAddress());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setUsertype(employee.getUsertype());
		employeeDto.setCreatedDate(employee.getCreatedDate());
		employeeDto.setUpdatedDate(employee.getUpdatedDate());
		employeeDto.setIsActive(true);
		return employeeDto;
		
	}
	public  static Employee setEmployeeUpdate(EmployeeDto employeeDto)throws Exception {
		
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setUserid(employeeDto.getUserid());
		employee.setPassword(employeeDto.getPassword());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmailid(employeeDto.getEmailid());
		employee.setDateOfJoining(employeeDto.getDateOfJoining());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setAddress(employeeDto.getAddress());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setSalary(employeeDto.getSalary());
		employee.setUsertype(employeeDto.getUsertype());
		employee.setCreatedDate(employeeDto.getCreatedDate());
		employee.setUpdatedDate(employeeDto.getUpdatedDate());
		employee.setIsActive(true);
		return employee;

	}
}
