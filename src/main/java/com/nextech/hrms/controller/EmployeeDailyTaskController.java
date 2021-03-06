package com.nextech.hrms.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.EmployeeDailyTaskDto;
import com.nextech.hrms.dto.EmployeeLeaveDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeDailyTaskServices;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.util.DateUtil;

@Controller
@RequestMapping("/employeedailytask")
public class EmployeeDailyTaskController {
	public long totaltime;

	@Autowired
	EmployeeDailyTaskServices employeeDailyTaskServices;
	
	@Autowired
	EmployeeLeaveServices employeeLeaveServices;
	
	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	private MessageSource messageSource;

	static  Logger logger = Logger.getLogger(EmployeeDailyTaskController.class);

	@RequestMapping(value = "/create/{userid}", method = RequestMethod.POST,headers = "Accept=application/json")
	public @ResponseBody
	Status addEmployee(@PathVariable("userid") String userid ,@RequestBody EmployeeDailyTaskDto employeeDailyTaskDto) {
		try {
			Employeeleave employeeleaves = employeeLeaveServices.getEmpolyeeleaveByIdandDate(employeeDailyTaskDto.getEmployee().getId(),employeeDailyTaskDto.getDate());
			if(employeeleaves!=null){
				return new Status(1,"Sorry You have allready applied leave for this day,So you cant fill Daily Task");
			}else{
			employeeDailyTaskDto.setIsActive(true);
			employeeDailyTaskDto.setHasRead(true);
			employeeDailyTaskDto.setTakenTime(calculateTotalTime(employeeDailyTaskDto));
			 Employee employee = employeeServices.getEmployeeByUserId(userid);
			 if(employee.getId()!=employeeDailyTaskDto.getEmployee().getId()){
				 Employeedailytask employeedailytask = new Employeedailytask();
				
				 employeedailytask.setEmployee(employeeDailyTaskDto.getEmployee());
				 employeedailytask.setDate(employeeDailyTaskDto.getDate());
				 employeedailytask.setTaskName(employeeDailyTaskDto.getTaskName());
				 employeedailytask.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
				 employeedailytask.setStarttime(employeeDailyTaskDto.getStarttime());
				 employeedailytask.setEndtime(employeeDailyTaskDto.getEndtime());
				 employeedailytask.setTakenTime(employeeDailyTaskDto.getTakenTime());
				 employeedailytask.setStatus(employeeDailyTaskDto.getStatus());
				 employeedailytask.setDescription(employeeDailyTaskDto.getDescription());
				 employeedailytask.setAssignBy(employee.getId());
				 employeedailytask.setIsActive(true);
				 employeedailytask.setHasRead(true);
				employeeDailyTaskServices.addEntity(employeedailytask);
				return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Added_Successfully, null,null));
			 }else {
				
				 employeeDailyTaskServices.addEntity(EmployeeDailyTaskFactory.setEmployeeDailyTask(employeeDailyTaskDto));
				 return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Added_Successfully, null,null));
			}			
			
		}
		}catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestParam("employeeDailyTaskExcelFile") MultipartFile employeeDailyTaskExcelFile) {
		try {
			List<EmployeeDailyTaskDto> employeeDailyTaskDtos = EmployeeDailyTaskFactory.setEmployeeDailyTaskExcel(employeeDailyTaskExcelFile);
			employeeDailyTaskServices.addEmployeeDailyTaskExcel(employeeDailyTaskDtos);
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Added_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EmployeeDailyTaskDto getEmployee(@PathVariable("id") long id) {
		EmployeeDailyTaskDto employeeDailyTaskDto = null;
		try {
			employeeDailyTaskDto = employeeDailyTaskServices.getEmployeeDailyTaskDto(id);
			
		} catch (Exception e) {
			logger.error(e);
			
		}
		return employeeDailyTaskDto;
	}

	@RequestMapping(value = "/getDailyTaskByUserid/{Userid}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Employeedailytask> getEmployeeDailyTaskByUserId( @PathVariable("Userid") long empId) {
		List<Employeedailytask> employeedailytasks = null;
		try {
			employeedailytasks = employeeDailyTaskServices
					.getEmployeeDailyTaskByUserid(empId);		

		} catch (Exception e) {
			logger.error(e);
			logger.error("Employee daily task list");

		}
		return  employeedailytasks; // TODO Use proper message to indicate correct reason user
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<EmployeeDailyTaskDto> getEmployee() {

		List<EmployeeDailyTaskDto> employeeDailyTaskDtoList = null;
		try {
			employeeDailyTaskDtoList = employeeDailyTaskServices.getEmployeeDailyTaskDtoList(employeeDailyTaskDtoList);

		} catch (Exception e) {
			logger.error(e);
		}

		return employeeDailyTaskDtoList;
	}

	@RequestMapping(value = "/getMYTaskByUserid/{userid}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Employeedailytask> getMyDailyTaskByUserId( @PathVariable("userid") String userid) {
		List<Employeedailytask> employeedailytasks = null;
		try {
			Employee employee = employeeServices.getEmployeeByUserId(userid);
			employeedailytasks = employeeDailyTaskServices
					.getEmployeeDailyTaskByUserid(employee.getId());		

		} catch (Exception e) {
			logger.error(e);

		}
		return  employeedailytasks; // TODO Use proper message to indicate correct reason user
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE,headers = "Accept=application/json")
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			employeeDailyTaskServices.getEmployeeDailyTaskDtoByid(id); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Delete_Successfully, null,null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeDailyTaskDto employeeDailyTaskDto) {

		try {
			employeeDailyTaskDto.setTakenTime(calculateTotalTime(employeeDailyTaskDto));
			employeeDailyTaskServices.updateEntity(EmployeeDailyTaskFactory.setEmployeeDailyTaskUpdate(employeeDailyTaskDto));
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Update_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
	
	@RequestMapping(value = "/getEmployeeDailyTask/{id}/{Date}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByIdandMonth( @PathVariable("id") long empId,@PathVariable("Date") String date) {
		List<Employeedailytask> employeedailytaskList = null;
		try {
			employeedailytaskList = employeeDailyTaskServices.getEmployeeDailytaskByEmployeeIdandCurrentDate(empId,DateUtil.convertToDate(date));
					
			if(employeedailytaskList==null){
				
				return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
			}

		} catch (Exception e) {
			logger.error(e);

		}
		return new Status(1, "Employee Daily Task By Id and Date!", employeedailytaskList);
	}
	public long calculateTotalTime(EmployeeDailyTaskDto employeeDailyTaskDto){
		Time starttime = employeeDailyTaskDto.getStarttime();
		Time endtime = employeeDailyTaskDto.getEndtime();
		long totalTime = endtime.getTime()-starttime.getTime();
		return (totalTime / (60 * 60 * 1000) % 24);
	}
	
	@RequestMapping(value = "/getDailyTaskByReportTo/{userId}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeLeaveByAppliedLeave(@PathVariable("userId") String userId,HttpServletRequest request ) {
		List<EmployeeDailyTaskDto> dailyTaskDtos = new ArrayList<EmployeeDailyTaskDto>();
		List<Employeedailytask> employeedailytasks =null;
		try {
		
	        Employee employee = employeeServices.getEmployeeByUserId(userId);
	        List<Employee> employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
	        if(employees==null){
	        	logger.error("Employee does not exits");
	        	 return new Status(1,"Employee does not exits") ;
				}  
	        for (Employee employee2 : employees) {
	        	employeedailytasks =  employeeDailyTaskServices.getEmployeeTaskByEmployeeId(employee2.getId());
	        	for (Employeedailytask employeedailytask : employeedailytasks) {
        			EmployeeDailyTaskDto employeeDailyTaskDto= new EmployeeDailyTaskDto();
     				employeeDailyTaskDto.setId(employeedailytask.getId());
	   				employeeDailyTaskDto.setEmployee(employeedailytask.getEmployee());
	   				employeeDailyTaskDto.setTaskName(employeedailytask.getTaskName());
	   				employeeDailyTaskDto.setStarttime(employeedailytask.getStarttime());
	   				employeeDailyTaskDto.setEndtime(employeedailytask.getEndtime());
	   				employeeDailyTaskDto.setEstimationTime(employeedailytask.getEstimationTime());
	   				employeeDailyTaskDto.setStatus(employeedailytask.getStatus());
	   				employeeDailyTaskDto.setDate(employeedailytask.getDate());
	   				employeeDailyTaskDto.setTakenTime(employeedailytask.getTakenTime());
	   				dailyTaskDtos.add(employeeDailyTaskDto);		        	
				}
	        } 
	    	
	      
		} catch (Exception e) {
			logger.error(e);
			
			return new Status(0, e.getCause().getMessage());
			 
		}
		return new Status(0,"",dailyTaskDtos) ;
	}
	
	@RequestMapping(value = "/getTaskByUserid/{userid}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody Status getDailyTaskByUserId( @PathVariable("userid") String userid) {
		List<Employeedailytask> employeedailytasks = null;
		List<EmployeeDailyTaskDto> dailyTaskDtos = new ArrayList<EmployeeDailyTaskDto>();
		try {
			Employee employee = employeeServices.getEmployeeByUserId(userid);
			employeedailytasks = employeeDailyTaskServices
					.getEmployeeDailyTaskByUseridandHasRead(employee.getId());	
			for (Employeedailytask employeedailytask : employeedailytasks) {
				if(employeedailytask.getEmployee().getId()!=employeedailytask.getAssignBy()){
					EmployeeDailyTaskDto employeeDailyTaskDto= new EmployeeDailyTaskDto();
     				employeeDailyTaskDto.setId(employeedailytask.getId());
	   				employeeDailyTaskDto.setEmployee(employeedailytask.getEmployee());
	   				employeeDailyTaskDto.setTaskName(employeedailytask.getTaskName());
	   				employeeDailyTaskDto.setStarttime(employeedailytask.getStarttime());
	   				employeeDailyTaskDto.setEndtime(employeedailytask.getEndtime());
	   				employeeDailyTaskDto.setEstimationTime(employeedailytask.getEstimationTime());
	   				employeeDailyTaskDto.setStatus(employeedailytask.getStatus());
	   				employeeDailyTaskDto.setDate(employeedailytask.getDate());
	   				employeeDailyTaskDto.setTakenTime(employeedailytask.getTakenTime());
	   				employeeDailyTaskDto.setAssignBy(employeedailytask.getAssignBy());
	   				dailyTaskDtos.add(employeeDailyTaskDto);		        	
				}
			}

		} catch (Exception e) {
			logger.error(e);

		}
		return new Status(1,"daily task list",dailyTaskDtos)  ; 
	}
	
	@RequestMapping(value = "changeHasReadDailyTaskStatus/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteDepartment(@PathVariable("id") long id) {

		try {
			Employeedailytask employeedailytask = employeeDailyTaskServices.getEntityById(
					Employeedailytask.class, id);
			employeedailytask.setHasRead(false);
			employeeDailyTaskServices.updateEntity(employeedailytask);
			return new Status(0, "Has read cheack Successfully");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
