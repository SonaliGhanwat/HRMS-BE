package com.nextech.hrms.Dto;

import java.util.Date;

import com.nextech.hrms.model.Employee;

public class EmployeeLeaveDto extends AbstractDTO{
	private Employee employee;
	private String subject;
	private Date leavedate;
	private Date afterleavejoiningdate;
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
	public Date getLeavedate() {
		return leavedate;
	}
	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}
	public Date getAfterleavejoiningdate() {
		return afterleavejoiningdate;
	}
	public void setAfterleavejoiningdate(Date afterleavejoiningdate) {
		this.afterleavejoiningdate = afterleavejoiningdate;
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
