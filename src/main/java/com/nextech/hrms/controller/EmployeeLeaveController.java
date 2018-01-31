package com.nextech.hrms.controller;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.nextech.hrms.util.YearUtil;

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.dto.EmployeeLeaveDto;

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeLeaveDto;

import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.EmployeeLeaveFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.model.Leavetype;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.HolidayServices;
import com.nextech.hrms.services.LeaveTypeServices;
import com.nextech.hrms.util.DateUtil;


@Controller
@RequestMapping("/employeeleave")
public class EmployeeLeaveController {
	public long totaltime;

	@Autowired
	EmployeeLeaveServices employeeLeaveServices;
	
	@Autowired
	HolidayServices holidayServices;
	
	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices ;
	
	@Autowired
	EmployeeServices  employeeServices;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	LeaveTypeServices leaveTypeServices;

	static final Logger logger = Logger.getLogger(EmployeeLeaveController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeave(@RequestBody EmployeeLeaveDto employeeLeaveDto) {
		List<Employeeleave> employeeleaves =  null;
		EmployeeDto employeeDto = null;
		try {
			Holiday holiday = holidayServices.getHolidayBYDate(employeeLeaveDto.getFromDate());
			if(holiday!=null){
				return new Status(1,"Please dont apply holiday leave for leave");
			}else{
			Employeeleave employeeleave1 = employeeLeaveServices.getEmpolyeeleaveByIdandDate(employeeLeaveDto.getEmployee().getId(), employeeLeaveDto.getFromDate());
			if(employeeleave1==null){
				employeeLeaveDto.setIsActive(true);				
				EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
				employeeAttendanceDto.setEmployee(employeeLeaveDto.getEmployee());
				employeeAttendanceDto.setDate(employeeLeaveDto.getFromDate());
	            String time = ("00:00:00");
	            Time intime = Time.valueOf(time);
	            employeeAttendanceDto.setIntime(intime);
	            employeeAttendanceDto.setOuttime(intime);
				employeeAttendanceDto.setStatus("Leave");
				 employeeDto = employeeServices.getEmployeeDtoByidforLeave(employeeLeaveDto.getEmployee().getId());
				 Employeetype employeetype = employeeDto.getEmployeetype();
				 int seekLeave = employeetype.getSeekLeave();
				 int paidLeave = employeetype.getSeekLeave();
				 int totalSeekLeave =0;
				 int totalPaidleave =0;
				 int totalseekCount=0;		
				 int totalpaidCount=0;
				 employeeleaves = employeeLeaveServices.getEmployeeLeaveByUserid(employeeLeaveDto.getEmployee().getId());
				 Leavetype leavetype =  leaveTypeServices.getEntityById(Leavetype.class, employeeLeaveDto.getLeavetype().getId());
				 for (Employeeleave employeeleave : employeeleaves) {
				if(employeeleave.getLeavetype().getName().equals("seek leave")){
					 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
					  int day = Integer.valueOf(dayFormat.format(employeeleave.getFromDate()));
					  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
					  int day1 = Integer.valueOf(dayFormat1.format(employeeleave.getToDate()));
					 totalseekCount=totalseekCount+day1-day;
					totalSeekLeave = totalSeekLeave+totalseekCount;
				}
				if(employeeleave.getLeavetype().getName().equals("paid leave")){
					SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
					  int day = Integer.valueOf(dayFormat.format(employeeleave.getFromDate()));
					  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
					  int day1 = Integer.valueOf(dayFormat1.format(employeeleave.getToDate()));
					 totalpaidCount=totalpaidCount+day1-day;
					totalPaidleave = totalPaidleave+totalpaidCount;
				}
				}
				 
				 SimpleDateFormat dayFormat = new SimpleDateFormat("MM");
				  int month = Integer.valueOf(dayFormat.format(employeeDto.getDateOfJoining()));
				  if(month<=3){
					 // int leaveAllocate = seekLeave/3;
					  if(leavetype.getName().equals("seek leave")){
					  if(totalSeekLeave<=seekLeave){
					  }else{
						  return new Status(1,"your seek leave is over");
					  }
					  }
					  if(leavetype.getName().equals("paid leave")){
					  if(totalPaidleave<=paidLeave){
					  }else{
						  return new Status(1,"your paid leave is over ");
					  }
					  }
				  }if(month<=6){
					  if(leavetype.getName().equals("seek leave")){
						  if(totalSeekLeave<=seekLeave){
						  }else{
							  return new Status(1,"your seek leave is over");
						  }
					  }
					  if(leavetype.getName().equals("paid leave")){
						  if(totalPaidleave<=paidLeave){
					  }else{
						  return new Status(1,"your paid leave is over");
					  }
				  }
				  }
				  employeeLeaveServices.addEntity(EmployeeLeaveFactory.setEmployeeleave(employeeLeaveDto));
				employeeAttendanceServices.addEntity(EmployeeAttendanceFactory.setEmployeeAttendance(employeeAttendanceDto));
		}else{
			return new Status(1, "You have allready added leave");
		}
			return new Status(0, "Employee Leave added Successfully !");
		} 
		}catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}
	}


	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeave(@RequestParam("employeeLeaveExcelFile") MultipartFile employeeLeaveExcelFile) {
		try {
			    List<EmployeeLeaveDto> employeeLeaveDtos = EmployeeLeaveFactory.setEmployeeLeaveExcel(employeeLeaveExcelFile) ;
				employeeLeaveServices.addEmployeeLeaveExcel(employeeLeaveDtos);
		
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Added_Successfully, null,null));
		} catch (Exception e) {
			 e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EmployeeLeaveDto getEmployeeLeave(@PathVariable("id") long id) {
		EmployeeLeaveDto employeeLeaveDto = null;
		try {
			employeeLeaveDto = employeeLeaveServices.getEmployeeLeaveDto(id);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return employeeLeaveDto;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<EmployeeLeaveDto> getEmployee() {

		List<EmployeeLeaveDto> employeeLeaveDtolist = null;
		try {
			employeeLeaveDtolist = employeeLeaveServices.getEmployeeLeaveDtoList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeLeaveDtolist;
	}

	@RequestMapping(value = "/getLeaveByUserid/{EmpId}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody List<EmployeeLeaveDto> getEmployeeLeaveByUserId( @PathVariable("EmpId") long empId) {
		 List<EmployeeLeaveDto> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDto>();
		 List<Employeeleave> employeeleaves =  null;
		try {
			employeeleaves = employeeLeaveServices.getEmployeeLeaveByUserid(empId);
			int totalCount=0;
			int totalLeave=12;
			/*int count = 0;*/
			for (Employeeleave employeeleave : employeeleaves) {
				
				EmployeeLeaveDto employeeLeaveDTO= new EmployeeLeaveDto();
				 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				  int day = Integer.valueOf(dayFormat.format(employeeleave.getFromDate()));
				  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
				  int day1 = Integer.valueOf(dayFormat1.format(employeeleave.getToDate()));
				 totalCount=totalCount+day1-day;
				 totalLeave = totalLeave-totalCount;
				 employeeLeaveDTO.setToDate(employeeleave.getToDate());
				 employeeLeaveDTO.setFromDate(employeeleave.getFromDate());
				 employeeLeaveDTO.setSubject(employeeleave.getSubject());
				 employeeLeaveDTO.setEmployee(employeeleave.getEmployee());
				 employeeLeaveDTO.setTotalCount(totalCount);
				 employeeLeaveDTO.setPendingLeave(totalLeave);
				 employeeLeaveDTOs.add(employeeLeaveDTO);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return  employeeLeaveDTOs; 
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			 employeeLeaveServices.getEmployeeLeaveDtoByid(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Delete_Successfully, null,null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeLeaveDto employeeLeaveDto) {

		try {
			employeeLeaveServices.updateEntity(EmployeeLeaveFactory.setEmployeeLeaveUpdate(employeeLeaveDto));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Update_Successfully, null,null));
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
	public @ResponseBody List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(@PathVariable("id") long empId,@PathVariable("yearMonth") String date) {
		List<Employeeleave> employeeleaves = null;
		try {
			
			 employeeleaves = employeeLeaveServices.getMonthlyEmployeeLeaveByEmployeeId(empId,YearUtil.convertToDate(date));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeleaves;
	}
	@RequestMapping(value = "/getEmployeeLeave/{Date}", method = RequestMethod.GET)
	public @ResponseBody List<Employeeleave> getEmployeeAttendanceByIdandMonth( @PathVariable("Date") String date) {
		List<Employeeleave> employeeleaveList = null;
		try {
			employeeleaveList = employeeLeaveServices
					.getEmployeeLeaveByCurrentDate(DateUtil.convertToDate(date));

		} catch (Exception e) {
			e.printStackTrace();
			//return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return  employeeleaveList;
	}
	
}


