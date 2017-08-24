package com.nextech.hrms.controller;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.util.DateUtil;
import com.nextech.hrms.util.YearUtil;

@RestController
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController {
	
	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;
	
	@Autowired
	private MessageSource messageSource;
	
	@Transactional @RequestMapping(value = "/create", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

	public @ResponseBody Status addEmployeeAttendance(@RequestParam("employeeAttendanceExcelFile") MultipartFile employeeAttendanceExcelFile) {
		try {
			List<EmployeeAttendanceDto> employeeAttendanceDtos = EmployeeAttendanceFactory.setEmployeeAttendanceExcel(employeeAttendanceExcelFile);
			
			employeeAttendanceServices.addEmployeeAttendanceExcel(employeeAttendanceDtos);
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeAttendance_Added_Successfully, null,null));
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendance(@PathVariable("id") long id) {
		EmployeeAttendanceDto employeeAttendanceDto = null;
		try {
			employeeAttendanceDto = employeeAttendanceServices.getEmployeeAttendanceDto(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1, "Employee Attendance List", employeeAttendanceDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeAttendanceDto> getEmployee() {

		List<EmployeeAttendanceDto> employeeAttendanceDtoList = null;
		try {
			employeeAttendanceDtoList = employeeAttendanceServices.getEmployeeAttendanceList(employeeAttendanceDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeAttendanceDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {

		try {

			EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceServices.getEmployeeAttendanceDtoByid(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeAttendance_Delete_Successfully,null,null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(
			@RequestBody EmployeeAttendanceDto employeeAttendanceDto) {

		try {
			employeeAttendanceDto.setTotaltime(calculateTotalTime(employeeAttendanceDto));
			employeeAttendanceDto.setStatus(getEmployeeAttendanceStatus(employeeAttendanceDto));
			employeeAttendanceDto.setIsActive(true);
			employeeAttendanceServices.updateEntity(EmployeeAttendanceFactory.setEmployeeAttendanceUpdate(employeeAttendanceDto));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeAttendance_Update_Successfully, null,null));
	}

	@RequestMapping(value = "/getAttendanceByDate/{Date}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByDate( @PathVariable("Date") String date) {
		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices
					.getEmployeeattendanceByCurrentDate(DateUtil.convertToDate(date));
			if(employeeattendanceList!=null){
				// TODO create constants for success status code and error status code and user everywhere
				return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));// TODO Use proper message to indicate correct reason user
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new Status(1, "Employee Attendance By Date!", employeeattendanceList); // TODO Use proper message to indicate correct reason user
	}
	
	@RequestMapping(value = "/getAttendance/{id}/{yearMonth}", method = RequestMethod.GET)
	public @ResponseBody Status calculateEmployeeAttendanceByIdandMonth(
			@PathVariable("id") long empId, @PathVariable("yearMonth") String yearMonthString) {
		List<Employeeattendance> employeeattendanceList = null;
		String count="";
		try {
			employeeattendanceList = employeeAttendanceServices
					.calculateEmployeeAttendanceByEmployeeIdandDate(empId,YearUtil.convertToDate(yearMonthString));

			  count = String.valueOf(employeeattendanceList.size());
			 System.out.println("Employee Attedance"+count);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return new Status(1, "Employee Attendance!",employeeattendanceList);

	}

	
	public String getEmployeeAttendanceStatus(EmployeeAttendanceDto employeeAttendanceDto){
		try {
			if (employeeAttendanceDto.getTotaltime() >= 1
					&& employeeAttendanceDto.getTotaltime() <= 4) {
				return "HalfDay";
			} else if (employeeAttendanceDto.getTotaltime() >= 4) {
				return "Fullday";
			} else {
				return "Absent";
			}
		} catch (Exception e) {
			System.err.println("Got an exception!");
			e.printStackTrace();
		}
		return null;
	}
	
	public long calculateTotalTime(EmployeeAttendanceDto employeeAttendanceDto) throws ClassNotFoundException, SQLException{
		Time intime = employeeAttendanceDto.getIntime();
		Time outtime = employeeAttendanceDto.getOuttime();
		long totalTime = outtime.getTime() - intime.getTime();
		return (totalTime / (60 * 60 * 1000) % 24);
	}
}
