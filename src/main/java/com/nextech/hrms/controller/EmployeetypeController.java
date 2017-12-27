package com.nextech.hrms.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.nextech.hrms.Dto.EmployeeDto;
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
			return new Status(0, "Employeetype added Successfully !");
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
	public @ResponseBody Employeetype getEmployeetype(@PathVariable("id") long id) {
		Employeetype employeetype = null;
		try {
			employeetype = employeetypeService.getEntityById(Employeetype.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeetype;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateUserType(
			@RequestBody Employeetype employeetype,HttpServletRequest request,HttpServletResponse response) {
		try {
			employeetype.setIsactive(true);
			employeetypeService.updateEntity(employeetype);
			return new Status(0, "Employeetype update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Employeetype> getEmployeetype() {

		List<Employeetype> employeetypees = null;
		try {
			employeetypees = employeetypeService.getEntityList(Employeetype.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeetypees;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployeetype(@PathVariable("id") long id) {

		try {
			Employeetype employeetype = employeetypeService.getEntityById(Employeetype.class,id);
			employeetype.setIsactive(false);
			employeetypeService.updateEntity(employeetype);
			return new Status(0, "Employeetype deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	
}

