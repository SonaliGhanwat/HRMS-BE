package com.nextech.hrms.dto;

import java.util.List;

public class EmployeeLeaveStatusDto {
	
	private String status;
	
	private List<EmployeeLeaveDto> empLeaveDtos;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<EmployeeLeaveDto> getEmpLeaveDtos() {
		return empLeaveDtos;
	}

	public void setEmpLeaveDtos(List<EmployeeLeaveDto> empLeaveDtos) {
		this.empLeaveDtos = empLeaveDtos;
	}
	
	

}
