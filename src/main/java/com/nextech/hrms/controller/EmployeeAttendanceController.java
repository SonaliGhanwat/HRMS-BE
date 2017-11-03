package com.nextech.hrms.controller;

import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.util.DateUtil;
import com.nextech.hrms.util.YearUtil;

@RestController
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController {
	
	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;
	
	@Autowired
	EmployeeLeaveServices employeeLeaveServices;
	
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST,headers = "Accept=application/json")
	public @ResponseBody Status addEmployeeAttendance(@RequestBody EmployeeAttendanceDto employeeAttendanceDto) {
		try {
			List<Employeeleave> employeeleaves = employeeLeaveServices.getEntityList(Employeeleave.class);
			SimpleDateFormat dateFormatter = new SimpleDateFormat ("yyyy/MM/dd");
			for (Employeeleave employeeleave:employeeleaves) {
				 String leaveDate = dateFormatter.format(employeeleave.getLeavedate());
				    String attendanceDate = dateFormatter.format(employeeAttendanceDto.getDate());
				if(leaveDate.equals(attendanceDate)&&employeeAttendanceDto.getEmployee().getId()==employeeleave.getEmployee().getId()){
					return new Status(1,"Sorry You have allready applied leave for this day,So you cant fill Attendance");
				}
			}
			
			Employeeattendance employeeattendance1 = employeeAttendanceServices.getEmpolyeeAttendanceByIdandDate(employeeAttendanceDto.getEmployee().getId(), employeeAttendanceDto.getDate());
			if (employeeattendance1 == null) {
				employeeAttendanceDto.setTotaltime(calculateTotalTime(employeeAttendanceDto));
				employeeAttendanceDto.setStatus(getEmployeeAttendanceStatus(employeeAttendanceDto));
				employeeAttendanceServices.addEntity(EmployeeAttendanceFactory.setEmployeeAttendance(employeeAttendanceDto));
			} else {
				return new Status(1, "EmployeeId and Date Already Exist.");
			}
			return new Status(0, "Employee Attendance added Successfully !");
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}
	
	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

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
	public @ResponseBody EmployeeAttendanceDto getEmployeeAttendance(@PathVariable("id") long id) {
		EmployeeAttendanceDto employeeAttendanceDto = null;
		try {
			employeeAttendanceDto = employeeAttendanceServices.getEmployeeAttendanceDto(id);
		} catch (Exception e) {
			e.printStackTrace();
			//return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return  employeeAttendanceDto;
	}

	@RequestMapping(value = "/getAttendanceByUserid/{Userid}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Employeeattendance> getEmployeeAttendanceByUserId( @PathVariable("Userid") long empId) {
		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices
					.getEmployeeattendanceByUserid(empId);
			/*if(employeeattendanceList!=null){
				// TODO create constants for success status code and error status code and user everywhere
				return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));// TODO Use proper message to indicate correct reason user
			}*/

		} catch (Exception e) {
			e.printStackTrace();

		}
		return  employeeattendanceList; // TODO Use proper message to indicate correct reason user
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<EmployeeAttendanceDto> getEmployee() {

		List<EmployeeAttendanceDto> employeeAttendanceDtoList = null;
		try {
			employeeAttendanceDtoList = employeeAttendanceServices.getEmployeeAttendanceList(employeeAttendanceDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeAttendanceDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE,headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {

		try {

			EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceServices.getEmployeeAttendanceDtoByid(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeAttendance_Delete_Successfully,null,null));
	}

	
	@RequestMapping(value = "/update", method = RequestMethod.PUT,headers="Accept=application/json")
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

	@RequestMapping(value = "/getAttendanceByDate/{Date}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Employeeattendance> getEmployeeAttendanceByDate( @PathVariable("Date") String date) {
		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices
					.getEmployeeattendanceByCurrentDate(DateUtil.convertToDate(date));
			/*if(employeeattendanceList!=null){
				// TODO create constants for success status code and error status code and user everywhere
				return new Status(0,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));// TODO Use proper message to indicate correct reason user
			}*/

		} catch (Exception e) {
			e.printStackTrace();

		}
		return  employeeattendanceList; // TODO Use proper message to indicate correct reason user
	}
	
	@RequestMapping(value = "/getAttendance/{id}/{yearMonth}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<Employeeattendance> calculateEmployeeAttendanceByIdandMonth(
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

		return employeeattendanceList;

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
		long totalTime = outtime.getHours() - intime.getTime();
		return (totalTime / (60 * 60 * 1000) % 24);
	}
}
