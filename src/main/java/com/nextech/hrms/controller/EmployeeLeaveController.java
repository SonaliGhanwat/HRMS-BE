package com.nextech.hrms.controller;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.services.MailService;
import com.nextech.hrms.dto.Mail;
import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.services.NotificationService;
import com.nextech.hrms.services.NotificationUserAssociationService;
import com.nextech.hrms.util.YearUtil;
import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeLeaveDto;
import com.nextech.hrms.dto.EmplyeeLeavePart;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.EmployeeLeaveFactory;
import com.nextech.hrms.factory.MailResponseRequestFactory;
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

@EnableScheduling
@Configuration
@RestController
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
	NotificationUserAssociationService notificationUserAssService;

	@Autowired
	NotificationService notificationService;
	
	@Autowired
	LeaveTypeServices leaveTypeServices;
	
	@Autowired
	MailService mailService;

	static  Logger logger = Logger.getLogger(EmployeeLeaveController.class);
	
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
				  if(month<=4){
					 // int leaveAllocate = seekLeave/3;
					  if(leavetype.getName().equals("seek leave")){
					  if(totalSeekLeave<=seekLeave){
					  }else{
						  return new Status(1,messageSource.getMessage(MessageConstant.SeekLeave_Over, null,null));
					  }
					  }
					  if(leavetype.getName().equals("paid leave")){
					  if(totalPaidleave<=paidLeave){
					  }else{
						  return new Status(1,messageSource.getMessage(MessageConstant.PaidLeave_Over, null,null));
					  }
					  }
				  }if(month<=8){
					  if(leavetype.getName().equals("seek leave")){
						  if(totalSeekLeave<=seekLeave){
						  }else{
							  return new Status(1,messageSource.getMessage(MessageConstant.SeekLeave_Over, null,null));
						  }
					  }
					  if(leavetype.getName().equals("paid leave")){
						  if(totalPaidleave<=paidLeave){
					  }else{
						  return new Status(1,messageSource.getMessage(MessageConstant.PaidLeave_Over, null,null));
					  }
				  }
				  }if(month<=12){
					  if(leavetype.getName().equals("seek leave")){
						  if(totalSeekLeave<=seekLeave){
						  }else{
							  return new Status(1,messageSource.getMessage(MessageConstant.SeekLeave_Over, null,null));
						  }
					  }
					  if(leavetype.getName().equals("paid leave")){
						  if(totalPaidleave<=paidLeave){
					  }else{
						  return new Status(1,messageSource.getMessage(MessageConstant.PaidLeave_Over, null,null));
					  }
				  }
				  }
				employeeLeaveServices.addEntity(EmployeeLeaveFactory.setEmployeeleave(employeeLeaveDto));
				employeeAttendanceServices.addEntity(EmployeeAttendanceFactory.setEmployeeAttendance(employeeAttendanceDto));
				
				NotificationDTO notificationDTO = notificationService.getNotificationByCode("Apply Leave");
				emailNotificationUser(employeeLeaveDto, notificationDTO);
				
			}else{
			return new Status(1, messageSource.getMessage(MessageConstant.Alreay_Add_Leave, null,null));
		}
			return new Status(0, messageSource.getMessage(MessageConstant.EmployeeLeave_Added_Successfully, null,null));
		} 
		}catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
	private void emailNotificationUser(EmployeeLeaveDto employeeLeaveDto,
			NotificationDTO notificationDTO) throws Exception {
		Employee employee = employeeServices.getEntityById(Employee.class, employeeLeaveDto.getEmployee().getId());
		Employee employee2 = employeeServices.getEntityById(Employee.class, employee.getReportTo());
		Mail mail = mailService.setMailCCBCCAndTO(notificationDTO);
		String mailSubject  = mailService.getSubject(notificationDTO);
		//String userEmailTo = mail.getMailTo() + "," + employee2.getEmailid();
		mail.setMailSubject(mailSubject);
		mail.setMailTo(employee2.getEmailid());
		mail.setModel(MailResponseRequestFactory.setMailDetailsUser(employee,employeeLeaveDto, employee2,notificationDTO));
		mailService.sendEmailWithoutPdF(mail, notificationDTO);
		
	}
	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeave(@RequestParam("employeeLeaveExcelFile") MultipartFile employeeLeaveExcelFile) {
		try {
			    List<EmployeeLeaveDto> employeeLeaveDtos = EmployeeLeaveFactory.setEmployeeLeaveExcel(employeeLeaveExcelFile) ;
				employeeLeaveServices.addEmployeeLeaveExcel(employeeLeaveDtos);
		
			return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Added_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
			
		}
		return employeeLeaveDto;
	}

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeLeaveDto> getEmployee() {

		List<EmployeeLeaveDto> employeeLeaveDtolist = null;
		try {
			employeeLeaveDtolist = employeeLeaveServices.getEmployeeLeaveDtoList();
		} catch (Exception e) {
			logger.error(e);
		}

		return employeeLeaveDtolist;
	}

	@RequestMapping(value = "/leaveBalanceReport", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody Status getEmployeeLeaveByUserId( ) {
		 List<EmployeeLeaveDto> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDto>();
		 List<Employeeleave> employeeleaves =  null;
		try {
			employeeleaves = employeeLeaveServices.getEntityList(Employeeleave.class);
			HashMap<Long,List<EmplyeeLeavePart>> employeeLeavehash =  new HashMap<Long, List<EmplyeeLeavePart>>();
	
			for (Employeeleave employeeleave : employeeleaves) {
				int totalCount=0;
				int totalLeave=0;
				int totalSeekleave=0;
				int totalPaidLeave=0;
				int pendingLeave=0;
				int remaningSeekLeave=0;
				int remaningPaidLeave=0;
			
				List<Employeeleave> employeeleaves2  = employeeLeaveServices.getEmployeeLeaveByUserid(employeeleave.getId());
				for (Employeeleave employeeleave2 : employeeleaves2) {	
					totalLeave = employeeleave2.getEmployee().getEmployeetype().getTotalLeave();
				int seekLeave = employeeleave2.getEmployee().getEmployeetype().getSeekLeave();
				int paidLeave = employeeleave2.getEmployee().getEmployeetype().getPaidLeave();
					
					if(employeeleave2.getLeavetype().getId()==1){
				 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				  int day = Integer.valueOf(dayFormat.format(employeeleave2.getFromDate()));
				  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
				  int day1 = Integer.valueOf(dayFormat1.format(employeeleave2.getToDate()));
				  totalSeekleave=totalSeekleave+day1-day;
				  remaningSeekLeave = seekLeave-totalSeekleave;
				  remaningPaidLeave = paidLeave-totalPaidLeave;
					}else{
						 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
						  int day = Integer.valueOf(dayFormat.format(employeeleave2.getFromDate()));
						  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
						  int day1 = Integer.valueOf(dayFormat1.format(employeeleave2.getToDate()));
						  totalPaidLeave=totalPaidLeave+day1-day;
						  remaningPaidLeave = paidLeave-totalPaidLeave;
					}

					totalCount= totalSeekleave+totalPaidLeave;
					pendingLeave = totalLeave-totalCount;
					
				List<EmplyeeLeavePart> emplyeeLeaveParts = null;
				if(employeeLeavehash.get(employeeleave2.getEmployee()) == null){
					emplyeeLeaveParts = new ArrayList<EmplyeeLeavePart>();
				}else{
					emplyeeLeaveParts = employeeLeavehash.get(employeeleave2.getEmployee());
				}
				EmplyeeLeavePart emplyeeLeavePart = new EmplyeeLeavePart();
				emplyeeLeavePart.setTotalCount(totalCount);
				emplyeeLeavePart.setPaidLeave(totalPaidLeave);
				emplyeeLeavePart.setSeekLeave(totalSeekleave);
				emplyeeLeavePart.setPendingLeave(pendingLeave);
				emplyeeLeavePart.setRemaningPaidLeave(remaningPaidLeave);
				emplyeeLeavePart.setRemaningSeekLeave(remaningSeekLeave);
				emplyeeLeaveParts.add(emplyeeLeavePart);
				employeeLeavehash.put(employeeleave2.getEmployee().getId(), emplyeeLeaveParts);
				
				}
			}
			Set<Entry<Long, List<EmplyeeLeavePart>>> setEmployeePart =  employeeLeavehash.entrySet();
			for(Entry<Long, List<EmplyeeLeavePart>> setEmployeeEntry : setEmployeePart){
				EmployeeLeaveDto employeeLeaveDto = new EmployeeLeaveDto();
				Employee employee = employeeServices.getEntityById(Employee.class, setEmployeeEntry.getKey());
				employeeLeaveDto.setEmployee(employee);
				employeeLeaveDto.setEmplyeeLeaveParts(setEmployeeEntry.getValue());
				employeeLeaveDTOs.add(employeeLeaveDto);
			}
			
		} catch (Exception e) {
			logger.error(e);

		}
		return new Status(1,"",employeeLeaveDTOs)  ; 
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			 employeeLeaveServices.getEmployeeLeaveDtoByid(id);
			
		} catch (Exception e) {
			logger.error(e);
			return new Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Delete_Successfully, null,null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody EmployeeLeaveDto employeeLeaveDto) {

		try {
			employeeLeaveServices.updateEntity(EmployeeLeaveFactory.setEmployeeLeaveUpdate(employeeLeaveDto));			
		} catch (Exception e) {
			logger.error(e);	
		}
		return new Status(1, messageSource.getMessage(MessageConstant.EmployeeLeave_Update_Successfully, null,null));
	}
	
	@RequestMapping(value = "/getlistByUserId/{userId}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployee(@PathVariable("userId") String userId) {
		  Employee employee = null;
		List<Employeeleave> employeeleaves = null;
		try {
			employee = employeeServices.getEmployeeByUserId(userId);
			employeeleaves =employeeLeaveServices.getEmployeeLeaveByUserid(employee.getId());
			if(employeeleaves.size()==0){
				return new Status(1,"There is no any leave ") ;
			}
		} catch (Exception e) {
			logger.error(e);
			 
		}

		return new Status(0,"",employeeleaves);
	}

	@RequestMapping(value = "/calculateLeaveByUserId/{userId}", method = RequestMethod.GET,headers = "Accept=application/json")
	public @ResponseBody Status calculateLeaveByUserId(@PathVariable("userId") String userId ) {
		
		  List<EmployeeLeaveDto> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDto>();
		  List<Employeeleave> employeeleaves = null;
		  Employee employee = null;
		try {
			employee = employeeServices.getEmployeeByUserId(userId);
			HashMap<Long,List<EmplyeeLeavePart>> employeeLeavehash =  new HashMap<Long, List<EmplyeeLeavePart>>();
			int totalCount=0;
			int totalLeave=0;
			int totalSeekleave=0;
			int totalPaidLeave=0;
			int pendingLeave=0;
			int remaningSeekLeave=0;
			int remaningPaidLeave=0;
			   employeeleaves  = employeeLeaveServices.getEmployeeLeaveByUserid(employee.getId());
				for (Employeeleave employeeleave : employeeleaves) {	
					
					totalLeave = employeeleave.getEmployee().getEmployeetype().getTotalLeave();
				int seekLeave = employeeleave.getEmployee().getEmployeetype().getSeekLeave();
				int paidLeave = employeeleave.getEmployee().getEmployeetype().getPaidLeave();
					
					if(employeeleave.getLeavetype().getId()==1){
				 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
				  int day = Integer.valueOf(dayFormat.format(employeeleave.getFromDate()));
				  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
				  int day1 = Integer.valueOf(dayFormat1.format(employeeleave.getToDate()));
				  totalSeekleave=totalSeekleave+day1-day;
				  remaningSeekLeave = seekLeave-totalSeekleave;
				  remaningPaidLeave = paidLeave-totalPaidLeave;
				  
					}else{
						 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
						  int day = Integer.valueOf(dayFormat.format(employeeleave.getFromDate()));
						  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
						  int day1 = Integer.valueOf(dayFormat1.format(employeeleave.getToDate()));
						  totalPaidLeave = totalPaidLeave+day1-day;
						  remaningPaidLeave = paidLeave-totalPaidLeave;
						  remaningSeekLeave = seekLeave-totalSeekleave;
					}

					totalCount= totalSeekleave+totalPaidLeave;
					pendingLeave = totalLeave-totalCount;
					List<EmplyeeLeavePart> emplyeeLeaveParts = null;
					if(employeeLeavehash.get(employeeleave.getEmployee()) == null){
						emplyeeLeaveParts = new ArrayList<EmplyeeLeavePart>();
					}else{
						emplyeeLeaveParts = employeeLeavehash.get(employeeleave.getEmployee());
					}
					
				EmplyeeLeavePart emplyeeLeavePart = new EmplyeeLeavePart();			
				emplyeeLeavePart.setTotalCount(totalCount);
				emplyeeLeavePart.setPaidLeave(totalPaidLeave);
				emplyeeLeavePart.setSeekLeave(totalSeekleave);
				emplyeeLeavePart.setPendingLeave(pendingLeave);
				emplyeeLeavePart.setRemaningPaidLeave(remaningPaidLeave);
				emplyeeLeavePart.setRemaningSeekLeave(remaningSeekLeave);
				emplyeeLeaveParts.add(emplyeeLeavePart);
				employeeLeavehash.put(employeeleave.getEmployee().getId(), emplyeeLeaveParts);
		
				}
				Set<Entry<Long, List<EmplyeeLeavePart>>> setEmployeePart =  employeeLeavehash.entrySet();
				for(Entry<Long, List<EmplyeeLeavePart>> setEmployeeEntry : setEmployeePart){
					EmployeeLeaveDto employeeLeaveDto = new EmployeeLeaveDto();
					Employee employee1 = employeeServices.getEntityById(Employee.class, setEmployeeEntry.getKey());
					employeeLeaveDto.setEmployee(employee1);
					employeeLeaveDto.setEmplyeeLeaveParts(setEmployeeEntry.getValue());
					employeeLeaveDTOs.add(employeeLeaveDto);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1,"",employeeLeaveDTOs)  ; 
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
			logger.error(e);
		}
		return  employeeleaveList;
	}
		

	@RequestMapping(value = "/getEmployeeLeaveByStatus/{userId}", method = RequestMethod.GET)
	public @ResponseBody Status getEmployeeLeaveByAppliedLeave(@PathVariable("userId") String userId,HttpServletRequest request ) {
		List<EmployeeLeaveDto> employeeleaveList = new ArrayList<EmployeeLeaveDto>();
		List<Employeeleave> employeeleaves =null;
		try {
		
	        Employee employee = employeeServices.getEmployeeByUserId(userId);
	        List<Employee> employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
	       /* if(employees==null){
	        	logger.error("Employee does not exits");
	        	 return new Status(1,"Employee does not exits") ;
				}  */
	        for (Employee employee2 : employees) {
	        	 employeeleaves =  employeeLeaveServices.getEmployeeLeaveByEmployeeId(employee2.getId());
	        	for (Employeeleave employeeleave2 : employeeleaves) {
	        		int totalCount=0;
	        		if(employeeleave2.getStatus().equals("New Request For Leave")){
	        			EmployeeLeaveDto employeeLeaveDTO= new EmployeeLeaveDto();
	   				 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	   				  int day = Integer.valueOf(dayFormat.format(employeeleave2.getFromDate()));
	   				  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
	   				  int day1 = Integer.valueOf(dayFormat1.format(employeeleave2.getToDate()));
	   				 totalCount=totalCount+day1-day;
	   				// totalLeave = totalLeave-totalCount;
	   				 employeeLeaveDTO.setId(employeeleave2.getId());
	   				 employeeLeaveDTO.setToDate(employeeleave2.getToDate());
	   				 employeeLeaveDTO.setFromDate(employeeleave2.getFromDate());
	   				 employeeLeaveDTO.setSubject(employeeleave2.getSubject());
	   				 employeeLeaveDTO.setEmployee(employeeleave2.getEmployee());
	   				 employeeLeaveDTO.setTotalCount(totalCount);
	   				// employeeLeaveDTO.setPendingLeave(totalLeave);
	   				employeeleaveList.add(employeeLeaveDTO);
		        		//employeeleaveList.add(employeeleave2);
		        	}
				}
	        }     
	      
		} catch (Exception e) {
			logger.error(e);
			
			return new Status(0, e.getCause().getMessage());
			 
		}
		return new Status(0,"",employeeleaveList) ;
	}
	@RequestMapping(value = "/statusUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeLeaveStatus(@RequestBody  EmployeeLeaveDto employeeLeaveDto, HttpServletRequest request ) {
		try {
				Employeeleave employeeleave = employeeLeaveServices.getEntityById(Employeeleave.class, employeeLeaveDto.getId());
				employeeleave.setStatus(employeeLeaveDto.getStatus());
				employeeLeaveServices.updateEntity(employeeleave);				
				NotificationDTO notificationDTO = notificationService.getNotificationByCode("Approval Response");
				emailLeaveStatusApprovalResponse(employeeLeaveDto, notificationDTO);
				
			
		} catch (Exception e) {
			logger.error(e);
		}
		 return new Status(1, "Status Update Successfully");
	}
	private void emailLeaveStatusApprovalResponse( EmployeeLeaveDto employeeLeaveDto,
			NotificationDTO notificationDTO) throws Exception {
		Employeeleave employeeleave = employeeLeaveServices.getEntityById(Employeeleave.class, employeeLeaveDto.getId());
		employeeLeaveDto.setFromDate(employeeleave.getFromDate());
		employeeLeaveDto.setToDate(employeeleave.getToDate());
		employeeLeaveDto.setStatus(employeeLeaveDto.getStatus());
		Employee employee = employeeServices.getEntityById(Employee.class, employeeleave.getEmployee().getId());
		Employee employee2 = employeeServices.getEntityById(Employee.class, employee.getReportTo());
		Mail mail = mailService.setMailCCBCCAndTO(notificationDTO);
		String mailSubject  = mailService.getSubject(notificationDTO);
		//String userEmailTo = mail.getMailTo() + "," + employee2.getEmailid();
		mail.setMailSubject(mailSubject);
		mail.setMailTo(employee.getEmailid());
		mail.setModel(MailResponseRequestFactory.setMailDetailsUser(employee,employeeLeaveDto, employee2,notificationDTO));
		mailService.sendEmailWithoutPdF(mail, notificationDTO);
		
	}
	/* @Scheduled(initialDelay=10000, fixedRate=60000)
		public void executeSchedular(){
			System.out.println("Executed Scheduled method.");
		}*/
	
}


