package com.nextech.hrms.controller;

import java.sql.Time;
import java.util.List;

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

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeDailyTaskServices;
import com.nextech.hrms.util.DateUtil;

@Controller
@RequestMapping("/employeedailytask")
public class EmployeeDailyTaskController {
	public long totaltime;

	@Autowired
	EmployeeDailyTaskServices employeeDailyTaskServices;
	
	@Autowired
	private MessageSource messageSource;

	static final Logger logger = Logger.getLogger(EmployeeDailyTaskController.class);

	@Transactional @RequestMapping(value = "/create", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestParam("employeeDailyTaskExcelFile") MultipartFile employeeDailyTaskExcelFile) {
		try {
			List<EmployeeDailyTaskDto> employeeDailyTaskDtos = EmployeeDailyTaskFactory.setEmployeeDailyTaskExcel(employeeDailyTaskExcelFile);
			employeeDailyTaskServices.addEmployeeDailyTaskExcel(employeeDailyTaskDtos);
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Added_Successfully, null,null));
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		EmployeeDailyTaskDto employeeDailyTaskDto = null;
		try {
			employeeDailyTaskDto = employeeDailyTaskServices.getEmployeeDailyTaskDto(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, "Employee Daily Task By Id !",employeeDailyTaskDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<EmployeeDailyTaskDto> getEmployee() {

		List<EmployeeDailyTaskDto> employeeDailyTaskDtoList = null;
		try {
			employeeDailyTaskDtoList = employeeDailyTaskServices.getEmployeeDailyTaskDtoList(employeeDailyTaskDtoList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeDailyTaskDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			EmployeeDailyTaskDto employeeDailyTaskDto = employeeDailyTaskServices.getEmployeeDailyTaskDtoByid(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Delete_Successfully, null,null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeDailyTaskDto employeeDailyTaskDto) {

		try {
			calculateTotalTime(employeeDailyTaskDto);
			employeeDailyTaskServices.updateEntity(EmployeeDailyTaskFactory.setEmployeeDailyTaskUpdate(employeeDailyTaskDto));
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeDailyTask_Update_Successfully, null,null));
		} catch (Exception e) {
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
			e.printStackTrace();

		}
		return new Status(1, "Employee Daily Task By Id and Date!", employeedailytaskList);

	}
	public long calculateTotalTime(EmployeeDailyTaskDto employeeDailyTaskDto){
		Time starttime = employeeDailyTaskDto.getStarttime();
		Time endtime = employeeDailyTaskDto.getEndtime();
		long totalTime = endtime.getTime()-starttime.getTime();
		return (totalTime / (60 * 60 * 1000) % 24);
	}
}
