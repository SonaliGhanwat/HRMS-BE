package com.nextech.hrms.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employee;

public class EmployeeAttendanceDto extends AbstractDTO {
	private Employee employee;
	private Time intime;
	private Time outtime;
	private long totaltime;
	private Date date;
	private String status;	
	private List<EmployeeAttendancePart> employeeAttendanceParts;
	
	public EmployeeAttendanceDto(){
		
	}
	
	public EmployeeAttendanceDto(int id){
		this.setId(id);
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Time getIntime() {
		return intime;
	}
	public void setIntime(Time intime) {
		this.intime = intime;
	}
	public Time getOuttime() {
		return outtime;
	}
	public void setOuttime(Time outtime) {
		this.outtime = outtime;
	}
	public long getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<EmployeeAttendancePart> getEmployeeAttendanceParts() {
		return employeeAttendanceParts;
	}
	public void setEmployeeAttendanceParts(
			List<EmployeeAttendancePart> employeeAttendanceParts) {
		this.employeeAttendanceParts = employeeAttendanceParts;
	}
	
}
