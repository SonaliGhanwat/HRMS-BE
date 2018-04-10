package com.nextech.hrms.dto;

public class EmplyeeLeavePart {

	private int totalCount;
	private int pendingLeave;
	private int seekLeave;
	private int paidLeave;
	private int remaningSeekLeave;
	private int remaningPaidLeave;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPendingLeave() {
		return pendingLeave;
	}
	public void setPendingLeave(int pendingLeave) {
		this.pendingLeave = pendingLeave;
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
	public int getRemaningSeekLeave() {
		return remaningSeekLeave;
	}
	public void setRemaningSeekLeave(int remaningSeekLeave) {
		this.remaningSeekLeave = remaningSeekLeave;
	}
	public int getRemaningPaidLeave() {
		return remaningPaidLeave;
	}
	public void setRemaningPaidLeave(int remaningPaidLeave) {
		this.remaningPaidLeave = remaningPaidLeave;
	}
	
}
