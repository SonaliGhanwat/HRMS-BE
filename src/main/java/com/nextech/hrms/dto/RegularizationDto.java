package com.nextech.hrms.dto;

import java.util.Date;

import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;

public class RegularizationDto extends AbstractDTO{
	private Employee employee;
	//private Employeeattendance employeeattendance;
	private Date date;
	private long totalNumberofHoursworked;
	private long regularizedHours;
	private String status;
	private String reason;
	private String comments;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/*public Employeeattendance getEmployeeattendance() {
		return employeeattendance;
	}
	public void setEmployeeattendance(Employeeattendance employeeattendance) {
		this.employeeattendance = employeeattendance;
	}*/
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getTotalNumberofHoursworked() {
		return totalNumberofHoursworked;
	}
	public void setTotalNumberofHoursworked(long totalNumberofHoursworked) {
		this.totalNumberofHoursworked = totalNumberofHoursworked;
	}
	public long getRegularizedHours() {
		return regularizedHours;
	}
	public void setRegularizedHours(long regularizedHours) {
		this.regularizedHours = regularizedHours;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
