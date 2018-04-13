package com.nextech.hrms.controller;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.EmployeeAttendanceWorkInfoDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;
import com.nextech.hrms.services.EmployeeLeaveServices;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.HolidayServices;
import com.nextech.hrms.util.DateUtil;
import com.nextech.hrms.util.YearUtil;

@RestController
@RequestMapping("/employeeattendance")
public class EmployeeAttendanceController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Autowired
	EmployeeAttendanceServices employeeAttendanceServices;

	@Autowired
	EmployeeLeaveServices employeeLeaveServices;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	EmployeeServices employeeServices;
	
	@Autowired
	HolidayServices holidayServices;

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Status addEmployeeAttendance(
			@RequestBody EmployeeAttendanceDto employeeAttendanceDto) {
		try {
			Employeeleave employeeleaves = employeeLeaveServices
					.getEmpolyeeleaveByIdandDate(employeeAttendanceDto
							.getEmployee().getId(), employeeAttendanceDto
							.getDate());
			if (employeeleaves != null) {
				return new Status(
						1,
						"Sorry You have allready applied leave for this day,So you cant fill Attendance");
			} else {

				Employeeattendance employeeattendance1 = employeeAttendanceServices.getEmpolyeeAttendanceByIdandDate(employeeAttendanceDto.getEmployee().getId(), employeeAttendanceDto.getDate());
				if (employeeattendance1 == null) {
					employeeAttendanceDto
							.setTotaltime(calculateTotalTime(employeeAttendanceDto));
					employeeAttendanceDto
							.setStatus(getEmployeeAttendanceStatus(employeeAttendanceDto));
					employeeAttendanceServices
							.addEntity(EmployeeAttendanceFactory
									.setEmployeeAttendance(employeeAttendanceDto));
					// System.out.println("JSESSIONID:"+ cookie);
				} else {
					return new Status(1, "You have allready added attenadnce.");
				}
				return new Status(0, "Employee Attendance added Successfully !");

			}
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}

	}

	@Transactional
	@RequestMapping(value = "/createExcel", headers = "Content-Type=*/*", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployeeAttendance(
			@RequestParam(value = "employeeAttendanceExcelFile") MultipartFile employeeAttendanceExcelFile) {
		try {
			List<EmployeeAttendanceDto> employeeAttendanceDtos = EmployeeAttendanceFactory
					.setEmployeeAttendanceExcel(employeeAttendanceExcelFile);

			employeeAttendanceServices
					.addEmployeeAttendanceExcel(employeeAttendanceDtos);
			return new Status(1, messageSource.getMessage(
					MessageConstant.EmployeeAttendance_Added_Successfully,
					null, null));
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EmployeeAttendanceDto getEmployeeAttendance(
			@PathVariable("id") long id) {
		EmployeeAttendanceDto employeeAttendanceDto = null;
		try {
			employeeAttendanceDto = employeeAttendanceServices
					.getEmployeeAttendanceDto(id);
		} catch (Exception e) {
			e.printStackTrace();
			// return new
			// Status(1,messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS,null,null));
		}
		return employeeAttendanceDto;
	}

	@RequestMapping(value = "/getAttendanceByUserid/{Userid}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Employeeattendance> getEmployeeAttendanceByUserId(
			@PathVariable("Userid") long empId) {
		List<Employeeattendance> employeeattendanceList = null;
		try {
			employeeattendanceList = employeeAttendanceServices
					.getEmployeeattendanceByUserid(empId);
			/*
			 * if(employeeattendanceList!=null){ // TODO create constants for
			 * success status code and error status code and user everywhere
			 * return new Status(0,messageSource.getMessage(MessageConstant.
			 * EMPLOYEE_DOES_NOT_EXISTS,null,null));// TODO Use proper message
			 * to indicate correct reason user }
			 */

		} catch (Exception e) {
			e.printStackTrace();

		}
		return employeeattendanceList; // TODO Use proper message to indicate
										// correct reason user
	}

	/*
	 * @RequestMapping(value = "/list/{userId}", method =
	 * RequestMethod.GET,headers = "Accept=application/json") public
	 * @ResponseBody List<Employeeattendance>
	 * getEmployee(@PathVariable("userId") String userId ,HttpServletRequest
	 * request) {
	 * 
	 * List<Employeeattendance> employeeattendances = null; Employee employee =
	 * null; try { employee = employeeServices.getEmployeeByUserId(userId);
	 * employeeattendances =
	 * employeeAttendanceServices.getEmployeeattendanceByUserid
	 * (employee.getId()); //employeeAttendanceDtoList =
	 * employeeAttendanceServices
	 * .getEmployeeAttendanceList(employeeAttendanceDtoList);
	 * if(employee.getUsertype().getId()==2 ||employee.getUsertype().getId()==4
	 * ||employee.getUsertype().getId()==5){ return employeeattendances =
	 * employeeAttendanceServices.getEntityList(Employeeattendance.class); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return
	 * employeeattendances; }
	 */

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeAttendanceDto> getEmployee() {

		List<EmployeeAttendanceDto> employeeAttendanceDtoList = null;
		try {
			employeeAttendanceDtoList = employeeAttendanceServices
					.getEmployeeAttendanceList(employeeAttendanceDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeAttendanceDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {

		try {

			employeeAttendanceServices.getEmployeeAttendanceDtoByid(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, messageSource.getMessage(
					MessageConstant.EMPLOYEE_DOES_NOT_EXISTS, null, null));
		}
		return new Status(1, messageSource.getMessage(
				MessageConstant.EmployeeAttendance_Delete_Successfully, null,
				null));
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateEntity(
			@RequestBody EmployeeAttendanceDto employeeAttendanceDto) {

		try {
			employeeAttendanceDto
					.setTotaltime(calculateTotalTime(employeeAttendanceDto));
			employeeAttendanceDto
					.setStatus(getEmployeeAttendanceStatus(employeeAttendanceDto));
			employeeAttendanceDto.setIsActive(true);
			employeeAttendanceServices.updateEntity(EmployeeAttendanceFactory
					.setEmployeeAttendanceUpdate(employeeAttendanceDto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, messageSource.getMessage(
				MessageConstant.EmployeeAttendance_Update_Successfully, null,
				null));
	}

	/*
	 * @RequestMapping(value = "/getAttendanceByDate/{Date}", method =
	 * RequestMethod.GET,headers = "Accept=application/json") public
	 * @ResponseBody List<Employeeattendance> getEmployeeAttendanceByDate(
	 * @PathVariable("Date") String date) { List<Employeeattendance>
	 * employeeattendanceList = null; try { employeeattendanceList =
	 * employeeAttendanceServices
	 * .getEmployeeattendanceByCurrentDate(DateUtil.convertToDate(date));
	 * if(employeeattendanceList!=null){ // TODO create constants for success
	 * status code and error status code and user everywhere return new
	 * Status(0,
	 * messageSource.getMessage(MessageConstant.EMPLOYEE_DOES_NOT_EXISTS
	 * ,null,null));// TODO Use proper message to indicate correct reason user }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return employeeattendanceList; }
	 */
	/*
	 * @RequestMapping(value = "/getAttendance/{id}/{yearMonth}", method =
	 * RequestMethod.GET,headers = "Accept=application/json") public
	 * @ResponseBody List<Employeeattendance>
	 * calculateEmployeeAttendanceByIdandMonth(
	 * 
	 * @PathVariable("id") long empId, @PathVariable("yearMonth") String
	 * yearMonthString) { List<Employeeattendance> employeeattendanceList =
	 * null; String count=""; try { employeeattendanceList =
	 * employeeAttendanceServices
	 * .calculateEmployeeAttendanceByEmployeeIdandDate
	 * (empId,YearUtil.convertToDate(yearMonthString));
	 * 
	 * count = String.valueOf(employeeattendanceList.size());
	 * System.out.println("Employee Attedance"+count);
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * return employeeattendanceList;
	 * 
	 * }
	 */

	@RequestMapping(value = "/getAttendance/{userid}/{yearMonth}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status calculateEmployeeAttendanceByIdandMonth(
			@PathVariable("userid") String userid,
			@PathVariable("yearMonth") String yearMonthString) throws Exception {
		List<Employeeattendance> employeeattendanceList = null;
		Employee employee = employeeServices.getEmployeeByUserId(userid);

		try {
			employeeattendanceList = employeeAttendanceServices
					.calculateEmployeeAttendanceByEmployeeIdandDate(
							employee.getId(),
							YearUtil.convertToDate(yearMonthString));
			if(employeeattendanceList.size()==0){
				return new Status(0,"There is no any attendance for this month"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1,"",employeeattendanceList); 
	}
	 @Scheduled(initialDelay=6000000, fixedRate=1*60*60*1000)
	@RequestMapping(value = "/getAttendanceByDate", method = RequestMethod.GET, headers = "Accept=application/json")
	public void getEmployeeAttendanceByDate() {
		List<Employeeattendance> employeeattendanceList = null;
		List<Employee> employees = null;
		System.out.println("I am in sch");
		try {
			Date strDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		    String date = dateFormat.format(strDate);  
			employees = employeeServices.getEntityList(Employee.class);
			for (Employee employeeDto : employees) {
				employeeattendanceList = employeeAttendanceServices
						.getEmployeeAttendanceByEmployeeIdandDate(employeeDto.getId(),DateUtil.convertToDate(date));
				if (employeeattendanceList.size() == 0) {
					EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
					employeeAttendanceDto.setEmployee(employeeDto);
					String time = ("00:00:00");
					Time intime = Time.valueOf(time);
					employeeAttendanceDto.setDate(DateUtil.convertToDate(date));
					employeeAttendanceDto.setIntime(intime);
					employeeAttendanceDto.setOuttime(intime);
					    int year = Integer.parseInt(date.substring(0, 4));
					    int month = Integer.parseInt(date.substring(5, 7));
					    int day = Integer.parseInt(date.substring(8, 10));
					    Calendar cal = new GregorianCalendar(year, month - 1, day);
					    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);					 
					if (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek) {
						System.out.println(cal.get(Calendar.DAY_OF_MONTH));
						employeeAttendanceDto.setStatus("Holiday");
					} else {
						employeeAttendanceDto.setStatus("Absent");
					}
					employeeAttendanceServices.addEntity(EmployeeAttendanceFactory.setEmployeeAttendance(employeeAttendanceDto));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	 

	public String getEmployeeAttendanceStatus(
			EmployeeAttendanceDto employeeAttendanceDto) {
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

	@RequestMapping(value = "/calculateAttendanceWorkInfo/{userid}/{yearMonth}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status calculateEmployeeAttendanceWorkInfo(@PathVariable("userid") String userid,@PathVariable("yearMonth") String yearMonthString) throws Exception {
		List<Employeeattendance> employeeattendanceList = null;
		List<Holiday> holidays=null;
		List<EmployeeAttendanceWorkInfoDto> attendanceWorkInfoDtos= new ArrayList<EmployeeAttendanceWorkInfoDto>();
		Employee employee = employeeServices.getEmployeeByUserId(userid);
		int weekendDay=0;
		int totalWorkingDay=0;
		int holidayCount=0;
		int totalHoursReq=0;
		try {
			employeeattendanceList = employeeAttendanceServices.calculateEmployeeAttendanceByEmployeeIdandDate(employee.getId(),YearUtil.convertToDate(yearMonthString));
			if(employeeattendanceList.size()!=0){
				 int year = Integer.parseInt(yearMonthString.substring(0, 4));
				    int month = Integer.parseInt(yearMonthString.substring(6, 7));
				    int iDay = 1;
				    Calendar cal = new GregorianCalendar(year, month - 1, iDay);
				    int dayOfMonth= cal.getActualMaximum(Calendar.DAY_OF_MONTH);	
				    System.out.println("month:"+dayOfMonth);
				    for (int d = 1;  d <= dayOfMonth;  d++) {
				    	cal.set(Calendar.DAY_OF_MONTH, d);
				        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				        if (Calendar.SUNDAY == dayOfWeek|| Calendar.SATURDAY == dayOfWeek) {
				        	weekendDay++;			        	
				        }
				    }			    
				   holidays =  holidayServices.getHolidayListByYearandMonth(YearUtil.convertToDate(yearMonthString));
				   for (Holiday holiday : holidays) {
					   holidayCount++;
				}
				    totalWorkingDay = dayOfMonth - weekendDay-holidayCount;
				    totalHoursReq = totalWorkingDay*9;
				    int totalTime=0;
				    for (Employeeattendance  employeeattendance : employeeattendanceList) {
				    	
						totalTime = (int) (employeeattendance.getTotaltime()+totalTime);
					}
				
				    EmployeeAttendanceWorkInfoDto attendanceWorkInfoDto = new EmployeeAttendanceWorkInfoDto();
				    EmployeeAttendanceDto attendanceDto = new EmployeeAttendanceDto();
				    attendanceDto.setEmployee(employee);
				    attendanceWorkInfoDto.setEmployeeAttendanceDtos(attendanceDto);
				    attendanceWorkInfoDto.setTotalHoursReq(totalHoursReq);
				    attendanceWorkInfoDto.setTotalHoursWorked(totalTime);
				    attendanceWorkInfoDto.setTotalWorkingDay(totalWorkingDay);
				    attendanceWorkInfoDto.setTotalHoursReq(totalHoursReq);
				    attendanceWorkInfoDto.setTotalHolidayinMonth(holidayCount);			   
				    attendanceWorkInfoDtos.add(attendanceWorkInfoDto);
			}else{
				return new Status(0,"There is no any attendance for this month"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1,"",attendanceWorkInfoDtos); 
	}
	 
	public long calculateTotalTime(EmployeeAttendanceDto employeeAttendanceDto)
			throws ClassNotFoundException, SQLException {
		Time intime = employeeAttendanceDto.getIntime();
		Time outtime = employeeAttendanceDto.getOuttime();
		long totalTime = outtime.getTime() - intime.getTime();
		return (totalTime / (60 * 60 * 1000) % 24);
	}
	
 /*@Scheduled(initialDelay=10000, fixedRate=60000)
	public void executeSchedular(){
		System.out.println("Executed Scheduled method.");
	}*/
}
