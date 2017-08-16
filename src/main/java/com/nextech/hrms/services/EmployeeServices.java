package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.Dto.EmployeeDto;
import com.nextech.hrms.model.Employee;

public interface EmployeeServices extends CRUDService<Employee> {
	
	
	public Employee  getEmployeeByUserId(String userId) throws Exception;
	
	public Employee getEmployeeByphoneNumber(String phoneNumber)throws Exception;
	
	public Employee getEmpolyeeByEmailid(String emailId)throws Exception;
	
    public EmployeeDto getEmployeeDto(long id) throws Exception;
    
    public List<EmployeeDto> getEmployeeAttendanceList(List<EmployeeDto> employeeDtos)throws Exception;
    
    public EmployeeDto getEmployeeDtoByid(long id)throws Exception;



}
