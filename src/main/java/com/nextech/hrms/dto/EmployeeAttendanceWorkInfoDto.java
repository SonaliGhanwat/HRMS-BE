package com.nextech.hrms.dto;

import java.util.List;

public class EmployeeAttendanceWorkInfoDto {

	private int totalWorkingDay;
	private int totalHoursReq;
	private int totalHoursWorked;
	private int totalleaveTaken;
	private int totalHolidayinMonth;
	private EmployeeAttendanceDto employeeAttendanceDtos;
	
	public int getTotalWorkingDay() {
		return totalWorkingDay;
	}
	public void setTotalWorkingDay(int totalWorkingDay) {
		this.totalWorkingDay = totalWorkingDay;
	}
	public int getTotalHoursReq() {
		return totalHoursReq;
	}
	public void setTotalHoursReq(int totalHoursReq) {
		this.totalHoursReq = totalHoursReq;
	}
	public int getTotalHoursWorked() {
		return totalHoursWorked;
	}
	public void setTotalHoursWorked(int totalHoursWorked) {
		this.totalHoursWorked = totalHoursWorked;
	}
	public int getTotalleaveTaken() {
		return totalleaveTaken;
	}
	public void setTotalleaveTaken(int totalleaveTaken) {
		this.totalleaveTaken = totalleaveTaken;
	}
	public int getTotalHolidayinMonth() {
		return totalHolidayinMonth;
	}
	public void setTotalHolidayinMonth(int totalHolidayinMonth) {
		this.totalHolidayinMonth = totalHolidayinMonth;
	}
	public EmployeeAttendanceDto getEmployeeAttendanceDtos() {
		return employeeAttendanceDtos;
	}
	public void setEmployeeAttendanceDtos(
			EmployeeAttendanceDto employeeAttendanceDtos) {
		this.employeeAttendanceDtos = employeeAttendanceDtos;
	}

	
	
}
