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
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeDailyTaskServices;
import com.nextech.hrms.util.DateUtil;

@Controller
@RequestMapping("/employeedailytask")
public class EmployeeDailyTaskController {
	public long totaltime;
	public static final String USER_DOES_NOT_EXISTS = "We are sorry. This user does not exist.";

	@Autowired
	EmployeeDailyTaskServices employeeDailyTaskServices;

	static final Logger logger = Logger.getLogger(EmployeeDailyTaskController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployee(@RequestBody Employeedailytask employeedailytask) {
		try {
			employeedailytask.setIsActive(true);
			totalTime(employeedailytask);
			employeeDailyTaskServices.addEntity(employeedailytask);
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		Employeedailytask employeedailytask = null;
		try {
			employeedailytask = employeeDailyTaskServices.getEntityById(Employeedailytask.class, id);
			if(employeedailytask==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee List !",employeedailytask);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<Employeedailytask> getEmployee() {

		List<Employeedailytask> employeedailytaskList = null;
		try {
			employeedailytaskList = employeeDailyTaskServices.getEntityList(Employeedailytask.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeedailytaskList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			Employeedailytask employeedailytask = employeeDailyTaskServices.getEntityById(Employeedailytask.class, id);
			if(employeedailytask==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}
			employeedailytask.setIsActive(false);
			employeeDailyTaskServices.updateEntity(employeedailytask);
			//employeeServices.deleteEntity(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully!");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody Employeedailytask employeedailytask) {

		try {
			totalTime(employeedailytask);
			employeedailytask.setIsActive(true);
			employeeDailyTaskServices.updateEntity(employeedailytask);
			return new Status(1, "Employee update Successfully !");
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
				
				return new Status(1,USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, "Employee Daily Task!", employeedailytaskList);

	}
	public void totalTime(Employeedailytask employeedailytask){
		Time starttime = employeedailytask.getStarttime();
		Time endtime = employeedailytask.getEndtime();
		totaltime = endtime.getTime()-starttime.getTime();
		long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeedailytask.setTakenTime(diffHours);
	}
}
