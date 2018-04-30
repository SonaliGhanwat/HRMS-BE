package com.nextech.hrms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeLeaveDto;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Regularization;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.RegularizationServices;

@RestController
@RequestMapping("/regularization")
public class RegularizationController {

	
	@Autowired
	RegularizationServices regularizationServices;
	
	@Autowired
	EmployeeServices  employeeServices;
	
	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addRegularization(
			@Valid @RequestBody Regularization regularization) {
		try {
			
			Regularization regularizationResult = regularizationServices.getRegularizationByUseridandDate(regularization.getEmployee().getId(),regularization.getDate());
			if(regularizationResult==null){
			regularization.setActive(true);
			String status = "New Request For Regularization";
			regularization.setStatus(status);
			regularizationServices.addEntity(regularization);
			}else{
				return new Status(1, "You have allready added Regularization.");
			}
			return new Status(0, "Regularization added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Regularization getRegularization(@PathVariable("id") long id) {
		Regularization regularization = null;
		try {
			regularization = regularizationServices.getEntityById(Regularization.class,
					id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regularization;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateRegularization(
			@RequestBody Regularization regularization, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			regularization.setActive(true);
			regularizationServices.updateEntity(regularization);
			return new Status(0, "Regularization update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Regularization> getRegularization() {

		List<Regularization> regularizations = null;
		try {
			regularizations = regularizationServices.getEntityList(Regularization.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return regularizations;
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteRegularization(@PathVariable("id") long id) {

		try {
			Regularization regularization = regularizationServices.getEntityById(
					Regularization.class, id);
			regularization.setActive(false);
			regularizationServices.updateEntity(regularization);
			return new Status(0, "Regularization deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
	@RequestMapping(value = "/getRegularizationByStatus/{userId}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeLeaveByAppliedLeave(@PathVariable("userId") String userId,HttpServletRequest request ) {
		List<RegularizationDto> regularizationDtos = new ArrayList<RegularizationDto>();
		List<Regularization> regularizations =null;
		try {
		
	        Employee employee = employeeServices.getEmployeeByUserId(userId);
	        List<Employee> employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
	        for (Employee employee2 : employees) {
	        	regularizations =  regularizationServices.getRegularizationByEmployeeId(employee2.getId());
	        	for (Regularization regularization : regularizations) {
	        		
	        		if(regularization.getStatus().equals("New Request For Regularization")){
	        			RegularizationDto regularizationDto= new RegularizationDto();
	   				
	        			regularizationDto.setId(regularization.getId());
	        			regularizationDto.setDate(regularization.getDate());
	   				 regularizationDto.setTotalNumberofHoursworked(regularization.getTotalnumberofHoursworked());
	   				regularizationDto.setRegularizedHours(regularization.getRegularizedHours());
	   				regularizationDto.setStatus(regularization.getStatus());
	   				regularizationDto.setEmployee(regularization.getEmployee());
	   				regularizationDtos.add(regularizationDto);
		        		
		        	}
				}if(regularizations.size()==0){
	        	 return new Status(1,"Employee does not exits") ;
				}
	        }       
	      
		} catch (Exception e) {
			e.printStackTrace();	
			 
		}
		return new Status(0,"",regularizationDtos) ;
	}
	
	@RequestMapping(value = "/regularizationStatusUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeaveStatus(@RequestBody  RegularizationDto regularizationDto, HttpServletRequest request ) {
		try {
		
			
				Regularization regularization = regularizationServices.getEntityById(Regularization.class, regularizationDto.getId());
				regularization.setStatus(regularizationDto.getStatus());
				regularizationServices.updateEntity(regularization);
				Employeeattendance employeeattendance = employeeAttendanceServices.getEmpolyeeAttendanceByIdandDate(regularization.getEmployee().getId(), regularization.getDate());
				long totalTime = employeeattendance.getTotaltime() + regularization.getRegularizedHours();
				EmployeeAttendanceDto attendanceDto = new EmployeeAttendanceDto();
				attendanceDto.setTotaltime(totalTime);
				employeeattendance.setTotaltime(attendanceDto.getTotaltime());
				employeeAttendanceServices.updateEntity(employeeattendance);
				
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, "Status Update Successfully");
	}
}
