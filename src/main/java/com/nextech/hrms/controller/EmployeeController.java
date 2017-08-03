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

import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	static final Logger logger = Logger.getLogger(EmployeeController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployee(@RequestBody Employee employee) {
		try {
			
			Employee employee1 = employeeServices.getEmployeeByUserId(employee.getUserid());
			Employee employee2 = employeeServices.getEmployeeByphoneNumber(employee.getPhoneNumber());
			Employee employee3 = employeeServices.getEmpolyeeByEmailid(employee.getEmailid());
			if(employee1==null){
			if(employee2==null){
			if(employee3==null){
			employee.setIsActive(true);
			employeeServices.addEntity(employee);
			
			}else{
				return new Status(1, "EmailId Already Exit");
			}
			}else{
				return new Status(1, "Phone Number Already Exit");
			}
			}else{
				return new Status(1, "UserId Already Exit");
			}
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
		     e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		Employee employee = null;
		try {
			employee = employeeServices.getEntityById(id);
			if(employee==null){
				return new Status(1,"please Enter valid id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, "Employee List",employee);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<Employee> getEmployee() {

		List<Employee> employeeList = null;
		try {
			employeeList = employeeServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {
		Employee employee = null;

		try {
			employee =employeeServices.getEntityById(id);
            if(employee==null){
				return new Status(1,"please Enter valid id");
			}
			employee.setIsActive(false);
			employeeServices.updateEntity(employee);
			//employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@PathVariable("id") long id) {
		Employee employee = null;
		try {
			employeeServices.updateEntity(employee);
			return new Status(1, "Employee update Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	
}
