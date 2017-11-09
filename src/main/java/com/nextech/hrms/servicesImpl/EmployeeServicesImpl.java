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
import com.nextech.hrms.model.Status;
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
	public Employee getEmployeeByphoneNumber(long phoneNumber)
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

	@Override
	public void addEmployeeExcel(List<EmployeeDto> employeeDtos) throws Exception {
		// TODO Auto-generated method stub
		for (EmployeeDto employeeDto : employeeDtos) {
			employeeDto.setUserid(employeeDto.getUserid());
			employeeDto.setPassword(employeeDto.getPassword());
			employeeDto.setFirstName(employeeDto.getFirstName());
			employeeDto.setLastName(employeeDto.getLastName());
			employeeDto.setPhoneNumber(employeeDto.getPhoneNumber());
			employeeDto.setEmailid(employeeDto.getEmailid());
			employeeDto.setDateOfJoining(employeeDto.getDateOfJoining());
			employeeDto.setDateOfBirth(employeeDto.getDateOfBirth());
			employeeDto.setAddress(employeeDto.getAddress());
			employeeDto.setDepartment(employeeDto.getDepartment());
			employeeDto.setSalary(employeeDto.getSalary());
			employeeDto.setUsertype(employeeDto.getUsertype());
			Employee employee1 = employeeDao.getEmployeeByUserId(employeeDto.getUserid());
			if (employee1 == null) {
				employeeDao.add(EmployeeFactory.setEmployee(employeeDto));
			}
		
		}
		
	}

	@Override
	public List<Employee> getDesignationById(long id) throws Exception {
		// TODO Auto-generated method stub
		return employeeDao.getDesignationById(id);
	}
}
