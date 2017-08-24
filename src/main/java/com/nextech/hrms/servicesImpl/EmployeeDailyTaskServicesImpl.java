package com.nextech.hrms.servicesImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.controller.EmployeeDailyTaskController;
import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.services.EmployeeDailyTaskServices;
@Service
public class EmployeeDailyTaskServicesImpl extends CRUDServiceImpl<Employeedailytask> implements EmployeeDailyTaskServices {

	@Autowired
	EmployeeDailyTaskDao employeeDailyTaskDao;

	@Override
	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(
			long empId, Date date) throws Exception {
		
		return employeeDailyTaskDao.getEmployeeDailytaskByEmployeeIdandCurrentDate(empId, date);
	}

	@Override
	public EmployeeDailyTaskDto getEmployeeDailyTaskDto(long id)
			throws Exception {
		Employeedailytask  employeedailytask =  employeeDailyTaskDao.getById(Employeedailytask.class, id);
		EmployeeDailyTaskDto employeeDailyTaskDto = EmployeeDailyTaskFactory.setEmployeeDailyTaskList(employeedailytask);
		return employeeDailyTaskDto;
	}

	@Override
	public List<EmployeeDailyTaskDto> getEmployeeDailyTaskDtoList(
			List<EmployeeDailyTaskDto> employeeDailyTaskDtos) throws Exception {
		employeeDailyTaskDtos = new ArrayList<EmployeeDailyTaskDto>();
		List<Employeedailytask> employeedailytaskList = null;
		employeedailytaskList = employeeDailyTaskDao.getList(Employeedailytask.class);
		for (Employeedailytask employeedailytask : employeedailytaskList) {
			EmployeeDailyTaskDto employeeDailyTaskDto = EmployeeDailyTaskFactory.setEmployeeDailyTaskList(employeedailytask);
			employeeDailyTaskDtos.add(employeeDailyTaskDto);
		}
		return employeeDailyTaskDtos;
	}

	@Override
	public EmployeeDailyTaskDto getEmployeeDailyTaskDtoByid(long id)
			throws Exception {
		Employeedailytask employeedailytask =  employeeDailyTaskDao.getById(Employeedailytask.class, id);
		EmployeeDailyTaskDto employeeDailyTaskDto = EmployeeDailyTaskFactory.setEmployeeDailyTaskList(employeedailytask);
		employeedailytask.setIsActive(false);
		employeeDailyTaskDao.update(employeedailytask);
		return employeeDailyTaskDto;
	}

	@Override
	public void addEmployeeDailyTaskExcel(List<EmployeeDailyTaskDto> employeeDailyTaskDtos) throws Exception {
		
		EmployeeDailyTaskController employeeDailyTaskController = new EmployeeDailyTaskController();
		
		for(EmployeeDailyTaskDto employeeDailyTaskDto :employeeDailyTaskDtos){
			employeeDailyTaskDto.setEmployee(employeeDailyTaskDto.getEmployee());
			employeeDailyTaskDto.setDate(employeeDailyTaskDto.getDate());
			employeeDailyTaskDto.setTaskName(employeeDailyTaskDto.getTaskName());
			employeeDailyTaskDto.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
			employeeDailyTaskDto.setStarttime(employeeDailyTaskDto.getStarttime());
			employeeDailyTaskDto.setEndtime(employeeDailyTaskDto.getEndtime());
			employeeDailyTaskDto.setTakenTime(employeeDailyTaskController.calculateTotalTime(employeeDailyTaskDto));
			employeeDailyTaskDao.add(EmployeeDailyTaskFactory.setEmployeeDailyTask(employeeDailyTaskDto));
     	}
		
	}
}

