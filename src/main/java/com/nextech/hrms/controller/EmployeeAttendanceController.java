package com.nextech.hrms.controller;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
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

import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeAttendanceServices;

@Controller
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController {
	public long totaltime;
 

	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;

	static final Logger logger = Logger.getLogger(EmployeeAttendanceController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployeeAttendance(@RequestBody Employeeattendance employeeattendance) {
		try {
			
			Employeeattendance employeeattendance1 = employeeAttendanceServices.getEmpolyeeAttendanceByIdandDate(employeeattendance.getEmployee().getId(), employeeattendance.getDate());
			if(employeeattendance1==null){
			employeeattendance.setIsActive(true);
			Time intime = employeeattendance.getIntime();
			Time outtime = employeeattendance.getOuttime();
			totaltime=outtime.getTime()-intime.getTime();
			long diffHours = totaltime / (60 * 60 * 1000) % 24;
			System.out.print("Totaltime\n"  +diffHours);
			employeeattendance.setTotaltime(diffHours);
			getEmployeeAttendanceStatus(employeeattendance);
			employeeAttendanceServices.addEntity(employeeattendance);
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
		Employeeattendance employeeattendance = null;
		try {
			employeeattendance = employeeAttendanceServices.getEntityById(id);
			if(employeeattendance==null){
				return new Status(1,"please enter valid Id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee List",employeeattendance);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<Employeeattendance> getEmployee() {

		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices.getEntityList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeattendanceList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			Employeeattendance employeeattendance =employeeAttendanceServices.getEntityById(id);
			if(employeeattendance==null){
				return new Status(1,"please enter valid Id");
			}
			employeeattendance.setIsActive(false);
			employeeAttendanceServices.updateEntity(employeeattendance);
			//employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody Employeeattendance employeeattendance) {

		try {
			
			Time intime = employeeattendance.getIntime();
			Time outtime = employeeattendance.getOuttime();
			totaltime=outtime.getTime()-intime.getTime();
			long diffHours = totaltime / (60 * 60 * 1000) % 24;
			System.out.print("Totaltime\n"  +diffHours);
			employeeattendance.setTotaltime(diffHours);
			getEmployeeAttendanceStatus(employeeattendance);
			employeeAttendanceServices.updateEntity(employeeattendance);
			return new Status(1, "Employee update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}

	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/UPDATE-EMPLOYEEATTENDANCE/{EMP-ID}/{date}", method = RequestMethod.PUT)
	public @ResponseBody 
	Status updateEmplyeeAttendanceByEmpId(@RequestBody Employeeattendance employeeattendance,@PathVariable("EMP-ID") long empID,@PathVariable("date") Date date) {
		 
		try {
		Employeeattendance 	employeeattendanceOld =employeeAttendanceServices.getEmployeeAttendanceByEmployeeId(empID,date);
		employeeattendanceOld.setIntime(employeeattendance.getIntime());
		employeeattendanceOld.setOuttime(employeeattendance.getOuttime());
		Time intime = employeeattendanceOld.getIntime();
		Time outtime = employeeattendanceOld.getOuttime();
		totaltime=outtime.getTime()-intime.getTime();
		long diffHours = totaltime / (60 * 60 * 1000) % 24;
		System.out.print("Totaltime\n"  +diffHours);
		employeeattendanceOld.setTotaltime(diffHours);
		getEmployeeAttendanceStatus(employeeattendanceOld);
		employeeAttendanceServices.updateEntity(employeeattendanceOld);
		if(employeeattendanceOld==null){
				return new Status(1, "please enter valid Id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee update Successfully !");
	}
	
	 public void getEmployeeAttendanceStatus(Employeeattendance employeeAttendance) throws ClassNotFoundException, SQLException{
		 try {
		        if(employeeAttendance.getTotaltime()>=1 && employeeAttendance.getTotaltime()<=4){
			        employeeAttendance.setStatus("HalfDay");
		        }else if(employeeAttendance.getTotaltime()>=4){
			        employeeAttendance.setStatus("Fullday");
		        }else{
			        employeeAttendance.setStatus("Absent");
		        }
	    	}
	        catch (Exception e)
	        {
	          System.err.println("Got an exception!");
	          e.printStackTrace();
	        }
	        
		}
}


