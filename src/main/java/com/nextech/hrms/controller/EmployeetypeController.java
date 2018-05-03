package com.nextech.hrms.controller;

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
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.EmployeetypeService;

@RestController
@RequestMapping("/employeetype")
public class EmployeetypeController {
	@Autowired
	EmployeetypeService employeetypeService;
	
	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	private MessageSource messageSource;
	
	static  Logger logger = Logger.getLogger(EmployeetypeController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addEmployeetype(@Valid @RequestBody Employeetype employeetype,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			employeetype.setIsactive(true);
			employeetypeService.addEntity(employeetype);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Employee_Type_Added_Successfully, null, null));
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
	public @ResponseBody Employeetype getEmployeetype(@PathVariable("id") long id) {
		Employeetype employeetype = null;
		try {
			employeetype = employeetypeService.getEntityById(Employeetype.class, id);
		} catch (Exception e) {
			logger.error(e);
		}
		return employeetype;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateUserType(
			@RequestBody Employeetype employeetype,HttpServletRequest request,HttpServletResponse response) {
		try {
			employeetype.setIsactive(true);
			employeetypeService.updateEntity(employeetype);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Employee_Type_Update_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Employeetype> getEmployeetype() {

		List<Employeetype> employeetypees = null;
		try {
			employeetypees = employeetypeService.getEntityList(Employeetype.class);

		} catch (Exception e) {
			logger.error(e);
		}

		return employeetypees;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployeetype(@PathVariable("id") long id) {

		try {
			Employeetype employeetype = employeetypeService.getEntityById(Employeetype.class,id);
			employeetype.setIsactive(false);
			employeetypeService.updateEntity(employeetype);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Employee_Type_Delete_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
	
}

