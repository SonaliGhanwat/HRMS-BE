package com.nextech.hrms.controller;

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

import com.nextech.hrms.model.Department;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.DepartmentService;

@RestController
@RequestMapping("/department")
public class DeprtmentController {

	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addDepartment(
			@Valid @RequestBody Department department,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError().getDefaultMessage());
			}
			department.setActive(true);
			departmentService.addEntity(department);
			return new Status(0, "Department added Successfully !");
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
	public @ResponseBody Department getDepartment(@PathVariable("id") long id) {
		Department department = null;
		try {
			department = departmentService.getEntityById(Department.class,
					id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateDepartment(
			@RequestBody Department department, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			department.setActive(true);
			departmentService.updateEntity(department);
			return new Status(0, "department update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Department> getDepartment() {

		List<Department> departments = null;
		try {
			departments = departmentService.getEntityList(Department.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return departments;
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteDepartment(@PathVariable("id") long id) {

		try {
			Department department = departmentService.getEntityById(
					Department.class, id);
			department.setActive(false);
			departmentService.updateEntity(department);
			return new Status(0, "Designation deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

}
