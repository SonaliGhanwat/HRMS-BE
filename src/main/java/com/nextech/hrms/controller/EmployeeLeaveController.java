package com.nextech.hrms.controller;
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
import com.nextech.hrms.util.YearUtil;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.util.DateUtil;
import com.nextech.hrms.util.YearUtil;


@Controller
@RequestMapping("/employeeleave")
public class EmployeeLeaveController {
	public long totaltime;
	public static final String USER_DOES_NOT_EXISTS = "We are sorry. This user does not exist.";
 

	@Autowired
	EmployeeLeaveServices employeeLeaveServices;

	static final Logger logger = Logger.getLogger(EmployeeLeaveController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployeeAttendance(@RequestBody Employeeleave employeeleave) {
		try {
			
			Employeeleave employeeleave1 = employeeLeaveServices.getEmpolyeeleaveByIdandDate(employeeleave.getEmployee().getId(), employeeleave.getLeavedate());
			if(employeeleave1==null){
			    employeeleave.setIsActive(true);
				employeeLeaveServices.addEntity(employeeleave);
		}else{
			return new Status(1, "EmployeeId and Date Already Exit");
		}
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployeeAttendance(@PathVariable("id") long id) {
		Employeeleave employeeleave = null;
		try {
			employeeleave = employeeLeaveServices.getEntityById(id);
			if(employeeleave==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee List",employeeleave);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<Employeeleave> getEmployee() {

		List<Employeeleave> employeeleaveList = null;
		try {
			employeeleaveList = employeeLeaveServices.getEntityList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeleaveList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			Employeeleave employeeleave =employeeLeaveServices.getEntityById(id);
			if(employeeleave==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}
			employeeleave.setIsActive(false);
			employeeLeaveServices.updateEntity(employeeleave);
			//employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody Employeeleave employeeleave) {

		try {
			employeeLeaveServices.updateEntity(employeeleave);
			return new Status(1, "Employee update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,USER_DOES_NOT_EXISTS);
		}

	}
	
	@RequestMapping(value = "/leaveyear/{id}", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(@PathVariable("id") long empId) {
		List<EmployeeLeaveDTO> employeeleaves = null;
		try {
			 employeeleaves = employeeLeaveServices.getYearlyEmployeeLeaveByEmployeeId(empId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeleaves;
	}
	
	@RequestMapping(value = "/leavemonth/{id}/{Year-Month}", method = RequestMethod.GET)
	public @ResponseBody Status getMonthlyEmployeeLeaveByEmployeeId(@PathVariable("id") long empId,@PathVariable("Year-Month") String date) {
		
		try {
			List<Employeeleave> employeeleaves = null;
			 employeeleaves = employeeLeaveServices.getMonthlyEmployeeLeaveByEmployeeId(empId,YearUtil.convertToDate(date));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1,"Employee Leave");
	}
	
	@RequestMapping(value = "/getemployeeleave/{currentdate}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByIdandMonth( @PathVariable("currentdate") String date) {
		List<Employeeleave> employeeleaveList = null;
		try {
			employeeleaveList = employeeLeaveServices
					.getEmployeeLeaveByCurrentDate(DateUtil.convertToDate(date));

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,USER_DOES_NOT_EXISTS);

		}

		return new Status(1, "Employee Leave!", employeeleaveList);

	}
}


