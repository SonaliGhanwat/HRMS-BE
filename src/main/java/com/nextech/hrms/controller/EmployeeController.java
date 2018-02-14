package com.nextech.hrms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.filter.TokenFactory;
import com.nextech.hrms.model.Authorization;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	private MessageSource messageSource;

	static final Logger logger = Logger.getLogger(EmployeeController.class);
	@RequestMapping(value = "/create", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public @ResponseBody Status addEmployee(
			@Valid @RequestBody EmployeeDto employeeDto,HttpServletRequest request,HttpServletResponse response)
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
	public  @ResponseBody List<EmployeeDto> getEmployee(HttpServletRequest request,HttpServletResponse response) {
		List<EmployeeDto> employeeDtoList = null;
	    Employee employees = null;
		try {
			//eraseCookie(request, response); 			
			/*Cookie[] cookie = request.getCookies();
			for(int i=0;i<cookie.length;i++){
				
					System.out.println("userid:"+cookie[i].getName() + " : " + cookie[i].getValue());	
			}*/
			  
			    /*HttpSession session=request.getSession();  
		        String user=(String)session.getAttribute("name"); 
		        Employee employee = employeeServices.getEmployeeByUserId(user);
		        System.out.println("user:"+user);		  */     
		        employeeDtoList = employeeServices.getEmployeeAttendanceList(employeeDtoList);
		       
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeDtoList;
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public @ResponseBody Status getEmployee(@RequestBody Employee emplyee,HttpServletRequest request,HttpServletResponse response ) throws Exception {

		Employee employeeDB = employeeServices.getEmployeeByUserId(emplyee.getUserid());
		
		try {		
			  eraseCookie(request, response);   
			
	            Cookie cookie = new Cookie("cookie",emplyee.getUserid());
	            //cookie.setMaxAge(60*60); //1 hour
	    		response.addCookie(cookie);	 
	    		String userid = cookie.getValue();
	    		request.setAttribute("cookie", true);
	    		
	    		/*Cookie[] cookies = request.getCookies();
	    	    if (cookies != null)
	    	        for (Cookie cookie1 : cookies) {
	    	        	cookie1.setMaxAge(60*60);
	    	        	String userid = cookie.getValue();
	    	        	cookie1.setValue(userid);
	    	            response.addCookie(cookie1);
	    	        }
	    	    HttpSession session=request.getSession();  
	    		session.setAttribute("name",emplyee.getUserid());*/
			if(employeeDB ==null){
				return  new Status(1,"Please Enetr Valid UserId");
			}else if(!employeeDB.getPassword().equals(emplyee.getPassword())){
				return new Status(1,"Please Enter Valid Password");
			}
			return new Status(0,"Login Successfully",cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	/*@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public Status getEmployee(@RequestBody Employee employee, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Employee employeeDB = employeeServices.getEmployeeByUserId(employee.getUserid());
		try {
			
			if (employeeDB != null && authenticate(employee, employeeDB)) {
				Authorization authorization = new Authorization();
				authorization.setUserid(employee.getUserid());
				authorization.setPassword(employee.getPassword());
				authorization.setUpdatedDate(new Date());
				String token = TokenFactory.createAccessJwtToken(employeeDB);
				authorization.setToken(token);
				response.addHeader("auth_token", token);
			}
			
			
		}catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			
		}
		if(employeeDB ==null){
			return  new Status(1,"Please Enetr Valid UserId");
		}else if(!employeeDB.getPassword().equals(employee.getPassword())){
			return new Status(1,"Please Enter Valid Password");
		}
		return new Status(0,"Login Successfully");

	}
	private boolean authenticate(Employee formUser, Employee dbUser) {
		if (formUser.getUserid().equals(dbUser.getUserid())
				&& formUser.getPassword().equals(dbUser.getPassword())) {
			dbUser.getFirstName();
			dbUser.getLastName();
			return true;
		} else {
			return false;
		}

	}*/


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE ,headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {
		EmployeeDto employeeDto = null;

		try {
			
			employeeDto = employeeServices.getEmployeeDtoByid(id);
			employeeDto.setIsActive(false);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1, messageSource.getMessage(
				MessageConstant.Employee_Deleted_Successfully,null,null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT,headers = "Accept=application/json")
	public @ResponseBody Status updateEntity(@Valid @RequestBody EmployeeDto employeeDto,BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response,@CookieValue("cookie") String fooCookie) {
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
	
	/*public static Cookie addCookie(Employee emplyee,HttpServletRequest request,HttpServletResponse response) {
		  Cookie cookie = new Cookie("cookie",emplyee.getUserid());
          //cookie.setMaxAge(60*60); //1 hour
  		response.addCookie(cookie);	 
  		request.setAttribute("cookie", true);
	    return cookie;
	}*/
	private  Cookie[] eraseCookie(HttpServletRequest request, HttpServletResponse response) {
		
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null)
	        for (Cookie cookie1 : cookies) {
	            cookie1.setValue("");
	            cookie1.setPath("/");
	            cookie1.setMaxAge(0);
	            response.addCookie(cookie1);
	        }
	    return cookies;
	}
	
}
