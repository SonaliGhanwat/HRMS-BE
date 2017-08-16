package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.Dto.EmployeeDto;
import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.services.EmployeeServices;

@Service
public class EmployeeServicesImpl extends CRUDServiceImpl<Employee> implements EmployeeServices {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Employee getEmployeeByUserId(String userId) throws Exception {
		return employeeDao.getEmployeeByUserId(userId);
	}

	@Override
	public Employee getEmployeeByphoneNumber(String phoneNumber)
			throws Exception {
		return employeeDao.getEmployeeByphoneNumber(phoneNumber);

	}

	@Override
	public Employee getEmpolyeeByEmailid(String emailId) throws Exception {
		return employeeDao.getEmpolyeeByEmailid(emailId);
	}

	@Override
	public EmployeeDto getEmployeeDto(long id) throws Exception {
		Employee  employee =  employeeDao.getById(Employee.class, id);
		EmployeeDto employeeDto = EmployeeFactory.setEmployeeList(employee);
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getEmployeeAttendanceList(
			List<EmployeeDto> employeeDtos) throws Exception {
		employeeDtos = new ArrayList<EmployeeDto>();
		List<Employee> employeeList = null;
		employeeList = employeeDao.getList(Employee.class);
		for (Employee employee : employeeList) {
			EmployeeDto employeeDto = EmployeeFactory.setEmployeeList(employee);
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployeeDtoByid(long id) throws Exception {
		Employee employee =  employeeDao.getById(Employee.class, id);
		EmployeeDto employeeDto = EmployeeFactory.setEmployeeList(employee);
		employee.setIsActive(false);
		employeeDao.update(employee);
		return employeeDto;
		
	}
}
