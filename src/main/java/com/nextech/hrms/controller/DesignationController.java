package com.nextech.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.DesignationService;
import com.nextech.hrms.services.EmployeeServices;

@RestController
@RequestMapping("/designation")
public class DesignationController {

	@Autowired
	DesignationService designationService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	EmployeeServices employeeServices;
	
	static  Logger logger = Logger.getLogger(DesignationController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addDesignation(
			@Valid @RequestBody Designation designation,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError().getDefaultMessage());
			}
			designation.setIsactive(true);
			designationService.addEntity(designation);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Designation_Added_Successfully, null, null));
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Designation getDesignation(@PathVariable("id") long id) {
		Designation designation = null;
		try {
			designation = designationService.getEntityById(Designation.class,
					id);
		} catch (Exception e) {
			logger.error(e);
			
		}
		return designation;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateUserType(
			@RequestBody Designation designation, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			designation.setIsactive(true);
			designationService.updateEntity(designation);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Designation_Update_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Designation> getDesignation() {

		List<Designation> designations = null;
		try {
			designations = designationService.getEntityList(Designation.class);

		} catch (Exception e) {
			logger.error(e);
		}

		return designations;
	}

	@RequestMapping(value = "designationList/{departmentid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Designation> getDesignationListByUserTypeId(@PathVariable("departmentid") long departmentid) {
		List<Designation> designations = null;
		try {
			designations = designationService.getDesignationByDepartmentid(departmentid);

		} catch (Exception e) {
			logger.error(e);
		}

		return designations;
		
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteDesignation(@PathVariable("id") long id) {

		try {
			Designation designation = designationService.getEntityById(
					Designation.class, id);
			designation.setIsactive(false);
			designationService.updateEntity(designation);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Designation_Delete_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "reportTo/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getDesignationById(@PathVariable("id") long id) {
		try {
			List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();
			Designation designation = designationService.getEntityById(Designation.class, id);
			List<Designation> designations = designationService.getEntityList(Designation.class);
			for (Designation designation2 : designations) {
				if (designation2.getBand() < designation.getBand() || (designation2.getBand() <= designation.getBand() && designation2.getLevel() > designation.getLevel())) {
			        List<Employee> employees = employeeServices.getDesignationById(designation2.getId());
			        if(employees != null){
			        	for (Employee employee : employees) {
				        	EmployeeDto employeeDto = new EmployeeDto();
				        	employeeDto.setId(employee.getId());
				        	employeeDto.setFirstName(employee.getFirstName());
				        	employeeDto.setLastName(employee.getLastName());
				        	employeeDto.setUserid(employee.getUserid());
							employeeDtos.add(employeeDto);
						}	 
			        }
				}
			}
			return new Status(employeeDtos);
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
}
