package com.nextech.hrms.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	private MessageSource messageSource;

	static final Logger logger = Logger.getLogger(EmployeeController.class);
	@RequestMapping(value = "/create", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public @ResponseBody Status addEmployee(
			@Valid @RequestBody EmployeeDto employeeDto/*BindingResult bindingResult*/)
			throws Exception {
		if (employeeServices.getEmployeeByUserId(employeeDto.getUserid()) == null) {

		} else {
			return new Status(1, messageSource.getMessage(
					MessageConstant.USER_ID_EXIT, null, null));
		}
		if (employeeServices.getEmpolyeeByEmailid(employeeDto.getEmailid()) == null) {
		} else {
			return new Status(1, messageSource.getMessage(
					MessageConstant.EMAIL_ALREADY_EXIT, null, null));
		}
		if (employeeServices.getEmployeeByphoneNumber(employeeDto.getPhoneNumber()) == null) {
		} else {
			return new Status(1, messageSource.getMessage(
					MessageConstant.CONTACT_NUMBER_EXIT, null, null));
		}
			employeeDto.setIsActive(true);
			employeeServices.addEntity(EmployeeFactory.setEmployee(employeeDto));
			return new Status(0, messageSource.getMessage
					(MessageConstant.Employee_Added_Successfully,null,null));
	}

	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestParam("employeeExcelFile") MultipartFile employeeExcelFile)
			throws Exception {
		try {
			List<EmployeeDto> employeeDtos =  EmployeeFactory.setEmployeeExcel(employeeExcelFile);
			employeeServices.addEmployeeExcel(employeeDtos);
			return new Status(1, messageSource.getMessage
					(MessageConstant.Employee_Added_Successfully,null,null));
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody EmployeeDto getEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;
		try {

			employeeDto = employeeServices.getEmployeeDto(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return  employeeDto;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<EmployeeDto> getEmployee() {

		List<EmployeeDto> employeeDtoList = null;
		try {
			employeeDtoList = employeeServices.getEmployeeAttendanceList(employeeDtoList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeDtoList;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public @ResponseBody Status getEmployee(@RequestBody Employee emplyee) throws Exception {
 
		Employee employeeDB = employeeServices.getEmployeeByUserId(emplyee.getUserid());
		try {
			if(employeeDB ==null){
				return  new Status(1,"Please Enetr Valid UserId");
			}else if(!employeeDB.getPassword().equals(emplyee.getPassword())){
				return new Status(1,"Please Enter Valid Password");
			}
			return new Status(0,"Login Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE ,headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;

		try {
			employeeDto = employeeServices.getEmployeeDtoByid(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1, messageSource.getMessage(
				MessageConstant.Employee_Deleted_Successfully,null,null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT,headers = "Accept=application/json")
	public @ResponseBody Status updateEntity(@Valid @RequestBody EmployeeDto employeeDto,BindingResult bindingResult) {
		try {
			employeeDto.setIsActive(true);
			employeeServices.updateEntity(EmployeeFactory.setEmployeeUpdate(employeeDto));
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1,messageSource.getMessage(
				MessageConstant.Employee_Update_Successfully,null,null));
	}
}
