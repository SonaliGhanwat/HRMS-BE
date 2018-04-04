package com.nextech.hrms.dto;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Leavetype;

public class EmployeeLeaveDto extends AbstractDTO{
	private Employee employee;
	private String subject;
	private Date fromDate;
	private Date toDate;
	private int totalCount;
	private String status;
	private int pendingLeave;
	private int seekLeave;
	private int paidLeave;
	private Leavetype leavetype;
	private List<EmplyeeLeavePart> emplyeeLeaveParts;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSeekLeave() {
		return seekLeave;
	}
	public void setSeekLeave(int seekLeave) {
		this.seekLeave = seekLeave;
	}
	public int getPaidLeave() {
		return paidLeave;
	}
	public void setPaidLeave(int paidLeave) {
		this.paidLeave = paidLeave;
	}
	public List<EmplyeeLeavePart> getEmplyeeLeaveParts() {
		return emplyeeLeaveParts;
	}
	public void setEmplyeeLeaveParts(List<EmplyeeLeavePart> emplyeeLeaveParts) {
		this.emplyeeLeaveParts = emplyeeLeaveParts;
	}


}