package com.nextech.hrms.controller;

import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeDailyTaskServices;
import com.nextech.hrms.util.DateUtil;

@Controller
@RequestMapping("/employeedailytask")
public class EmployeeDailyTaskController {
	public long totaltime;
	public static final String EMPLOYEE_DOES_NOT_EXISTS = "We are sorry. This Employee does not exist.";

	@Autowired
	EmployeeDailyTaskServices employeeDailyTaskServices;

	static final Logger logger = Logger.getLogger(EmployeeDailyTaskController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployee(@RequestBody EmployeeDailyTaskDto employeeDailyTaskDto) {
		try {
			employeeDailyTaskDto.setIsActive(true);
			totalTime(employeeDailyTaskDto);
			employeeDailyTaskServices.addEntity(EmployeeDailyTaskFactory.setEmployeeDailyTask(employeeDailyTaskDto));
			return new Status(1, "Employee Daily Task added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		EmployeeDailyTaskDto employeeDailyTaskDto = null;
		try {
			employeeDailyTaskDto = employeeDailyTaskServices.getEmployeeDailyTaskDto(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,EMPLOYEE_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Daily Task List !",employeeDailyTaskDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<EmployeeDailyTaskDto> getEmployee() {

		List<EmployeeDailyTaskDto> employeeDailyTaskDtoList = null;
		try {
			employeeDailyTaskDtoList = employeeDailyTaskServices.getEmployeeDailyTaskDtoList(employeeDailyTaskDtoList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeDailyTaskDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			EmployeeDailyTaskDto employeeDailyTaskDto = employeeDailyTaskServices.getEmployeeDailyTaskDtoByid(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,EMPLOYEE_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Daily Task deleted Successfully!");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeDailyTaskDto employeeDailyTaskDto) {

		try {
			totalTime(employeeDailyTaskDto);
			employeeDailyTaskServices.updateEntity(EmployeeDailyTaskFactory.setEmployeeDailyTaskUpdate(employeeDailyTaskDto));
			return new Status(1, "Employee Daily Task update Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	
	@RequestMapping(value = "/getemployeedailytask/{id}/{currentdate}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByIdandMonth( @PathVariable("id") long empId,@PathVariable("currentdate") String date) {
		List<Employeedailytask> employeedailytaskList = null;
		try {
			employeedailytaskList = employeeDailyTaskServices.getEmployeeDailytaskByEmployeeIdandCurrentDate(empId,DateUtil.convertToDate(date));
					
			if(employeedailytaskList==null){
				
				return new Status(1,EMPLOYEE_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, "Employee Daily Task!", employeedailytaskList);

	}
	public void totalTime(EmployeeDailyTaskDto employeeDailyTaskDto){
		Time starttime = employeeDailyTaskDto.getStarttime();
		Time endtime = employeeDailyTaskDto.getEndtime();
		totaltime = endtime.getTime()-starttime.getTime();
		long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeeDailyTaskDto.setTakenTime(diffHours);
	}
}
