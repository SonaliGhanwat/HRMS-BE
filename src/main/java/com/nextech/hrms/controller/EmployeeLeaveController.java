package com.nextech.hrms.controller;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.nextech.hrms.util.YearUtil;
import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.factory.EmployeeLeaveFactory;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.util.DateUtil;


@Controller
@RequestMapping("/employeeleave")
public class EmployeeLeaveController {
	public long totaltime;
	public static final String Employee_DOES_NOT_EXISTS = "We are sorry. This Employee does not exist.";

	@Autowired
	EmployeeLeaveServices employeeLeaveServices;

	static final Logger logger = Logger.getLogger(EmployeeLeaveController.class);

	@Transactional @RequestMapping(value = "/create", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeave(@RequestParam("employeeLeaveExcelFile") MultipartFile employeeLeaveExcelFile) {
		try {
			    List<EmployeeLeaveDto> employeeLeaveDtos = EmployeeLeaveFactory.setEmployeeLeaveExcel(employeeLeaveExcelFile) ;
				employeeLeaveServices.addEmployeeLeaveExcel(employeeLeaveDtos);
		
			return new Status(1, "Employee Leave added Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployeeLeave(@PathVariable("id") long id) {
		EmployeeLeaveDto employeeLeaveDto = null;
		try {
			employeeLeaveDto = employeeLeaveServices.getEmployeeLeaveDto(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,Employee_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Leave List",employeeLeaveDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<EmployeeLeaveDto> getEmployee() {

		List<EmployeeLeaveDto> employeeLeaveDtolist = null;
		try {
			employeeLeaveDtolist = employeeLeaveServices.getEmployeeLeaveDtoList(employeeLeaveDtolist);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeLeaveDtolist;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			 employeeLeaveServices.getEmployeeLeaveDtoByid(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,Employee_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Leave deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeLeaveDto employeeLeaveDto) {

		try {
			employeeLeaveDto.setIsActive(true);
			employeeLeaveServices.updateEntity(EmployeeLeaveFactory.setEmployeeLeaveUpdate(employeeLeaveDto));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1, "Employee Leave update Successfully !");
	}
	
	@RequestMapping(value = "/leaveYear/{id}", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(@PathVariable("id") long empId) {
		List<EmployeeLeaveDTO> employeeleaves = null;
		try {
			 employeeleaves = employeeLeaveServices.getYearlyEmployeeLeaveByEmployeeId(empId);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeleaves;
	}
	
	@RequestMapping(value = "/leaveMonth/{id}/{yearMonth}", method = RequestMethod.GET)
	public @ResponseBody Status getMonthlyEmployeeLeaveByEmployeeId(@PathVariable("id") long empId,@PathVariable("yearMonth") String date) {
		
		try {
			List<Employeeleave> employeeleaves = null;
			 employeeleaves = employeeLeaveServices.getMonthlyEmployeeLeaveByEmployeeId(empId,YearUtil.convertToDate(date));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1,"Employee Leave");
	}
	
	@RequestMapping(value = "/getEmployeeLeave/{Date}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeAttendanceByIdandMonth( @PathVariable("Date") String date) {
		List<Employeeleave> employeeleaveList = null;
		try {
			employeeleaveList = employeeLeaveServices
					.getEmployeeLeaveByCurrentDate(DateUtil.convertToDate(date));

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,Employee_DOES_NOT_EXISTS);
		}
		return new Status(1, "Employee Leave!", employeeleaveList);
	}
}


