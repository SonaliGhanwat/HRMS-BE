package com.nextech.hrms.dto;

import java.sql.Time;
import java.util.Date;

import com.nextech.hrms.model.Employee;

public class EmployeeDailyTaskDto extends AbstractDTO{
	private Employee employee;
	private Date date;
	private String taskName;
	private String estimationTime;
	private Time starttime;
	private Time endtime;
	private long takenTime;
	private String status;
	private String description;
	private long assignBy;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
	public String getEstimationTime() {
		return estimationTime;
	}
	public void setEstimationTime(String estimationTime) {
		this.estimationTime = estimationTime;
	}
	public Time getStarttime() {
		return starttime;
	}
	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}
	public Time getEndtime() {
		return endtime;
	}
	public void setEndtime(Time endtime) {
		this.endtime = endtime;
	}
	public long getTakenTime() {
		return takenTime;
	}
	public void setTakenTime(long takenTime) {
		this.takenTime = takenTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getAssignBy() {
		return assignBy;
	}
	public void setAssignBy(long assignBy) {
		this.assignBy = assignBy;
	}
	
}
