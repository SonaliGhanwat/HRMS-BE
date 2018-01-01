package com.nextech.hrms.dto;

import java.util.Date;

import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Leavetype;

public class EmployeeLeaveDto extends AbstractDTO{
	private Employee employee;
	private String subject;
	private Date fromDate;
	private Date toDate;
	private int totalCount;
	private int pendingLeave;
	private Leavetype leavetype;
	
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
	public Leavetype getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(Leavetype leavetype) {
		this.leavetype = leavetype;
	}


}