package com.nextech.hrms.dto;

public class EmployeeTypeDto extends AbstractDTO {

	private String Type;
	private int seekLeave;
	private int paidLeave;
	private int totalLeave;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
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
	public int getTotalLeave() {
		return totalLeave;
	}
	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}
	
}
