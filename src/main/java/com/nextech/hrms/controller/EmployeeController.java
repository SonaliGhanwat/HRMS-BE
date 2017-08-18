package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.EmployeeDto;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	public static final String EMPLOYEE_DOES_NOT_EXISTS = "We are sorry. This Employee does not exist.";

	@Autowired
	EmployeeServices employeeServices;

	static final Logger logger = Logger.getLogger(EmployeeController.class);

	@Transactional @RequestMapping(value = "/create", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestParam("employeeExcelFile") MultipartFile employeeExcelFile)
			throws Exception {
		try {
			List<EmployeeDto> employeeDtos =  EmployeeFactory.setEmployeeExcel(employeeExcelFile);
			employeeServices.addEmployeeExcel(employeeDtos);
			return new Status(1, "Employee added Successfully !");
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;
		try {

			employeeDto = employeeServices.getEmployeeDto(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1, EMPLOYEE_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee List", employeeDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDto> getEmployee() {

		List<EmployeeDto> employeeDtoList = null;
		try {
			employeeDtoList = employeeServices.getEmployeeAttendanceList(employeeDtoList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;

		try {
			employeeDto = employeeServices.getEmployeeDtoByid(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, EMPLOYEE_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee deleted Successfully !");
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@Valid @RequestBody EmployeeDto employeeDto,BindingResult bindingResult) {
		try {
			employeeDto.setIsActive(true);
			employeeServices.updateEntity(EmployeeFactory.setEmployeeUpdate(employeeDto));
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, EMPLOYEE_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Update Successfully !");
	}
}
