package com.nextech.hrms.dto;

public class EmplyeeLeavePart {

	private int totalCount;
	private int pendingLeave;
	private int seekLeave;
	private int paidLeave;
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
	
}
