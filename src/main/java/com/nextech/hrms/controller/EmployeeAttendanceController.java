package com.nextech.hrms.controller;

import java.sql.SQLException;
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
import com.nextech.hrms.model.Status;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.util.DateUtil;
import com.nextech.hrms.util.YearUtil;

@Controller
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController {
	public static final String USER_DOES_NOT_EXISTS = "We are sorry. This user does not exist.";
	public long totaltime;

	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;

	static final Logger logger = Logger
			.getLogger(EmployeeAttendanceController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeAttendance(@RequestBody Employeeattendance employeeattendance) {
		try {
			Employeeattendance employeeattendance1 = employeeAttendanceServices.getEmpolyeeAttendanceByIdandDate(employeeattendance.getEmployee().getId(), employeeattendance.getDate());
			if (employeeattendance1 == null) {
				employeeattendance.setIsActive(true);
				totaltime(employeeattendance);
				employeeAttendanceServices.addEntity(employeeattendance);
				
			} else {
				return new Status(1, "EmployeeId and Date Already Exit");
			}
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendance(
			@PathVariable("id") long id) {
		Employeeattendance employeeattendance = null;
		try {
			employeeattendance = employeeAttendanceServices.getEntityById(Employeeattendance.class, id);
			if (employeeattendance == null) {
				return new Status(1, USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee List", employeeattendance);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Employeeattendance> getEmployee() {

		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices.getEntityList(Employeeattendance.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeattendanceList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {

		try {
			Employeeattendance employeeattendance = employeeAttendanceServices.getEntityById(Employeeattendance.class, id);
			if (employeeattendance == null) {
				return new Status(1, USER_DOES_NOT_EXISTS);
			}
			employeeattendance.setIsActive(false);
			employeeAttendanceServices.updateEntity(employeeattendance);
			// employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(
			@RequestBody Employeeattendance employeeattendance) {

		try {
			
			 totaltime(employeeattendance);
			 employeeattendance.setIsActive(true);
			employeeAttendanceServices.updateEntity(employeeattendance);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, "Employee update Successfully !");
	}

	@RequestMapping(value = "/getemployeeattendance/{currentdate}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByIdandMonth( @PathVariable("currentdate") String date) {
		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices
					.getEmployeeattendanceByCurrentDate(DateUtil.convertToDate(date));
			if(employeeattendanceList==null){
				
				return new Status(1,USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, "Employee Attendance!", employeeattendanceList);

	}
	
	@RequestMapping(value = "/attendance/{id}/{Year-Month}", method = RequestMethod.GET)
	public @ResponseBody Status calculateEmployeeAttendanceByIdandMonth(
			@PathVariable("id") long empId, @PathVariable("Year-Month") String date) {
		List<Employeeattendance> employeeattendanceList = null;
		String count="";
		try {
			employeeattendanceList = employeeAttendanceServices
					.calculateEmployeeAttendanceByEmployeeIdandDate(empId,YearUtil.convertToDate(date));

			  count = String.valueOf(employeeattendanceList.size());
			 System.out.println("Employee Attedance"+count);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new Status(1, "Employee Attendance!",count);

	}

	public void getEmployeeAttendanceStatus(
			Employeeattendance employeeAttendance)
			throws ClassNotFoundException, SQLException {
		try {
			if (employeeAttendance.getTotaltime() >= 1
					&& employeeAttendance.getTotaltime() <= 4) {
				employeeAttendance.setStatus("HalfDay");
			} else if (employeeAttendance.getTotaltime() >= 4) {
				employeeAttendance.setStatus("Fullday");
			} else {
				employeeAttendance.setStatus("Absent");
			}
		} catch (Exception e) {
			System.err.println("Got an exception!");
			e.printStackTrace();
		}
	}
	
	public void totaltime(Employeeattendance employeeattendance) throws ClassNotFoundException, SQLException{
		Time intime = employeeattendance.getIntime();
		Time outtime = employeeattendance.getOuttime();
		totaltime = outtime.getTime() - intime.getTime();
		long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n" + diffHours);
		employeeattendance.setTotaltime(diffHours);
		getEmployeeAttendanceStatus(employeeattendance);
		
	}

}
