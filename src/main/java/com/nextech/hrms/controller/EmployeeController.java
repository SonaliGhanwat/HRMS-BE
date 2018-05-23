package com.nextech.hrms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
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

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeAttendancePart;
import com.nextech.hrms.dto.EmployeeDailyTaskDto;
import com.nextech.hrms.dto.EmployeePart;
import com.nextech.hrms.dto.PageDTO;
import com.nextech.hrms.dto.UserTypePageAssoDTO;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.UserTypeServices;
import com.nextech.hrms.services.UsertypepageassociationService;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	EmployeeServices employeeServices;

	@Autowired
	UserTypeServices userTypeServices;

	@Autowired
	UsertypepageassociationService usertypepageassociationService;

	@Autowired
	private MessageSource messageSource;
	
	
	static  Logger logger = Logger.getLogger(EmployeeController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addEmployee(
			@Valid @RequestBody EmployeeDto employeeDto,
			HttpServletRequest request, HttpServletResponse response)
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
		if (employeeServices.getEmployeeByphoneNumber(employeeDto
				.getPhoneNumber()) == null) {
		} else {
			return new Status(1, messageSource.getMessage(
					MessageConstant.CONTACT_NUMBER_EXIT, null, null));
		}
		employeeDto.setIsActive(true);
		employeeServices.addEntity(EmployeeFactory
				.getEmployeeModel(employeeDto));
		return new Status(0, messageSource.getMessage(
				MessageConstant.Employee_Added_Successfully, null, null));
	}

	@Transactional
	@RequestMapping(value = "/createExcel", headers = "Content-Type=*/*", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(
			@RequestParam("employeeExcelFile") MultipartFile employeeExcelFile)
			throws Exception {
		try {
			List<EmployeeDto> employeeDtos = EmployeeFactory
					.setEmployeeExcel(employeeExcelFile);
			employeeServices.addEmployeeExcel(employeeDtos);
			return new Status(1, messageSource.getMessage(
					MessageConstant.Employee_Added_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody EmployeeDto getEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;
		try {
			employeeDto = employeeServices.getEmployeeDto(id);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return employeeDto;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<EmployeeDto> getEmployee(
			HttpServletRequest request, HttpServletResponse response) {
		List<EmployeeDto> employeeDtoList = null;
		try {
			employeeDtoList = employeeServices
					.getEmployeeAttendanceList(employeeDtoList);
		} catch (Exception e) {
			logger.error(e);
		}
		return employeeDtoList;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status getEmployee(@RequestBody Employee employee,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Employee employeeResult = employeeServices
					.getEmployeeByUserId(employee.getUserid());

			if (employeeResult != null
					&& authenticate(employee, employeeResult)) {
				Usertype usertype = userTypeServices.getEntityById(
						Usertype.class, employeeResult.getUsertype().getId());
				List<UserTypePageAssoDTO> userTypePageAssociations = usertypepageassociationService
						.getPagesByUsertype(usertype.getId());
				HashMap<String, Object> result = new HashMap<String, Object>();
				List<PageDTO> pages = new ArrayList<PageDTO>();
				if (!userTypePageAssociations.isEmpty()) {
					for (UserTypePageAssoDTO usertypepageassociation : userTypePageAssociations) {
						pages.add(usertypepageassociation.getPage());
					}
				}
				
				result.put("pages", pages);
				result.put("user", EmployeeFactory.getEmployeeDTO(employeeResult));
				String success = employee.getUserid()
						+ " logged in Successfully";
				return new Status(0, success, result, employeeResult);
			}
			if (employeeResult == null) {
				return new Status(1, messageSource.getMessage(
						MessageConstant.Valid_UserId, null, null));
			} else if (!employeeResult.getPassword().equals(
					employee.getPassword())) {
				return new Status(2, messageSource.getMessage(
						MessageConstant.Valid_Password, null, null));
			}

		} catch (Exception e) {
			logger.error(e);
		}
		return new Status(0, messageSource.getMessage(
				MessageConstant.Login_Successfully, null, null));

	}

	private boolean authenticate(Employee formEmployee, Employee dbEmployee) {
		if (formEmployee.getUserid().equals(dbEmployee.getUserid())
				&& formEmployee.getPassword().equals(dbEmployee.getPassword())) {
			logger.error("Employee Authentication Successful");
			return true;
		} else {
			logger.error("Employee Authentication Failed");
			return false;
		}

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;

		try {
			employeeDto = employeeServices.getEmployeeDtoByid(id);
			employeeDto.setIsActive(false);
			return new Status(1, messageSource.getMessage(
					MessageConstant.Employee_Deleted_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null, null));
		}
		
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateEntity(
			@Valid @RequestBody EmployeeDto employeeDto,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			employeeDto.setIsActive(true);
			employeeServices.updateEntity(EmployeeFactory
					.setEmployeeUpdate(employeeDto));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null, null));
		}
		return new Status(1, messageSource.getMessage(
				MessageConstant.Employee_Update_Successfully, null, null));
	}

	
	@RequestMapping(value = "/getEmployeeByUserId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getEmployeeByUserId(@PathVariable("userId") String userId) {
		List<Employee> employees = null;
		try {
			 Employee employee = employeeServices.getEmployeeByUserId(userId);
			 if(employee.getUsertype().getId()!=1){
		        employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
		        if(employees==null){
		        	return new Status (0,"Employee does not exits");
		        }
			 }else{
				 employees = employeeServices.getEntityList(Employee.class);
			 }
		} catch (Exception e) {
			logger.error(e);
		}
		return new Status (1,"Employee List",employees);
	}
	
	@RequestMapping(value = "/getEmployeeByUserIdinList/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getEmployeeByUserIdInList(@PathVariable("userId") String userId) {
		
		List<Employee>employeesData = new ArrayList<Employee>();
		try {
			Employee employee = employeeServices.getEmployeeByUserId(userId);
			  List<Employee> employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
			  if(employees!=null){
				  for (Employee employee2 : employees) {
						employeesData.add(employee2);
					}
			  }
		
		   employeesData.add(employee);
			
			 
		} catch (Exception e) {
			logger.error(e);
		}
		return new Status (1,"Employee List",employeesData);
	}
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status resetPassword(@RequestBody Employee employee,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Employee employeeDB = employeeServices.getEmployeeByUserId(employee
				.getUserid());

		try {
			if (employeeDB == null) {
				return new Status(1, messageSource.getMessage(
						MessageConstant.Valid_UserId, null, null));
			}

			if(employeeDB.getPassword().equals(employee.getPassword())){
				return new Status(1, messageSource.getMessage(
						MessageConstant.Old_Password, null, null));
							
			}else{
				employeeDB.setPassword(employee.getPassword());
				employeeServices.updateEntity(employeeDB);
			}
			
			return new Status(0, messageSource.getMessage(
					MessageConstant.Reset_Password, null, null));
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}
	@RequestMapping(value = "/createMultiple", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Status addMultipleEmployee(
			@Valid @RequestBody EmployeeDto employeeDto) {
		try {			
			List<EmployeePart> employeeParts =	employeeDto.getEmployeeParts();
			if(!employeeParts.isEmpty()){
				
			for (EmployeePart employeePart : employeeParts) {	
				Employee employee2 = employeeServices.getEmployeeDataByUserIdAndPhoneNumber(employeePart.getUserid(),employeePart.getEmailid(),employeePart.getPhoneNumber());
				if(employee2!=null){
					return new Status(2,"Employee data allready in database");
				}
				Employee employee =new Employee();
				employee.setId(employeePart.getId());
				employee.setUserid(employeePart.getUserid());
				employee.setPassword(employeePart.getPassword());
				employee.setFirstName(employeePart.getFirstName());
				employee.setLastName(employeePart.getLastName());
				employee.setPhoneNumber(employeePart.getPhoneNumber());
				employee.setEmailid(employeePart.getEmailid());
				employee.setDateOfJoining(employeePart.getDateOfJoining());
				employee.setDateOfBirth(employeePart.getDateOfBirth());
				employee.setAddress(employeePart.getAddress());
				employee.setSalary(employeePart.getSalary());
				employee.setUsertype(employeePart.getUsertype());
				employee.setEmployeetype(employeePart.getEmployeetype());
				employee.setDesignation(employeePart.getDesignation());
				employee.setReportTo(employeePart.getReportTo());
				employee.setDepartment(employeePart.getDepartment());
				employeeServices.addEntity(employee);
				
			
			}
			}else{
				return new Status(2,messageSource.getMessage(
						MessageConstant.Empty_Filed_ExcelFile,
						null, null));
			}
			return new Status(1,messageSource.getMessage(
					MessageConstant.Excel_Upload_Successfully,
					null, null));
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	
}
