package com.nextech.hrms.model;

import com.nextech.hrms.dto.AbstractDTO;

public class EmployeeLeaveDTO  {
	private int totalCount;
	
	private int pendingLeave;

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
