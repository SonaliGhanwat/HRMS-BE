package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	public static final String USER_DOES_NOT_EXISTS = "We are sorry. This user does not exist.";

	@Autowired
	EmployeeServices employeeServices;

	static final Logger logger = Logger.getLogger(EmployeeController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(
			@Valid @RequestBody Employee employee, BindingResult bindingResult)
			throws Exception {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}

			Employee employee1 = employeeServices.getEmployeeByUserId(employee
					.getUserid());
			Employee employee2 = employeeServices
					.getEmployeeByphoneNumber(employee.getPhoneNumber());
			Employee employee3 = employeeServices.getEmpolyeeByEmailid(employee
					.getEmailid());
			if (employee1 == null) {
			} else {
				return new Status(1, "UserId Already Exit");
			}
			if (employee2 == null) {
			} else {
				return new Status(1, "Phone Number Already Exit");
			}
			if (employee3 == null) {
			} else {
				return new Status(1, "EmailId Already Exit");
			}
			employee.setIsActive(true);
			employeeServices.addEntity(employee);
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
		Employee employee = null;
		try {

			employee = employeeServices.getEntityById(Employee.class, id);
			if (employee == null) {
				return new Status(1, USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee List", employee);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployee() {

		List<Employee> employeeList = null;
		try {
			employeeList = employeeServices.getEntityList(Employee.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		Employee employee = null;

		try {
			employee = employeeServices.getEntityById(Employee.class, id);
			if (employee == null) {
				return new Status(1, USER_DOES_NOT_EXISTS);
			}
			employee.setIsActive(false);
			employeeServices.updateEntity(employee);
			// employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@Valid @RequestBody Employee employee,BindingResult bindingResult) {
		try {
			employee.setIsActive(true);
			employeeServices.updateEntity(employee);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee Update Successfully !");
	}

}
