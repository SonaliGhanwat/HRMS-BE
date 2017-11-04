package com.nextech.hrms.Dto;

import java.util.Date;

import com.nextech.hrms.model.Employee;

public class EmployeeLeaveDto extends AbstractDTO{
	private Employee employee;
	private String subject;
	private Date fromDate;
	private Date toDate;
	private int totalCount;
	private int pendingLeave;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getPendingLeave() {
		return pendingLeave;
	}

	public void setPendingLeave(int pendingLeave) {
		this.pendingLeave = pendingLeave;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


}
