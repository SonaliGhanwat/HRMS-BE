package com.nextech.hrms.Dto;

import java.sql.Time;
import java.util.Date;

import com.nextech.hrms.model.Employee;

public class EmployeeAttendanceDto extends AbstractDTO {
	private Employee employee;
	private Time intime;
	private Time outtime;
	private long totaltime;
	private Date date;
	private String status;
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
}
