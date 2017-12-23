package com.nextech.hrms.dto;

public class EmployeeTypeDto extends AbstractDTO{
	private String type;
	private int seekLeave;
	private int PaidLeave;
	private int totalLeave;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSeekLeave() {
		return seekLeave;
	}
	public void setSeekLeave(int seekLeave) {
		this.seekLeave = seekLeave;
	}
	public int getPaidLeave() {
		return PaidLeave;
	}
	public void setPaidLeave(int paidLeave) {
		PaidLeave = paidLeave;
	}
	public int getTotalLeave() {
		return totalLeave;
	}
	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}
}
