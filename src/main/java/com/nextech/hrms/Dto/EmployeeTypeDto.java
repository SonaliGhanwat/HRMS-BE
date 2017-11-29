package com.nextech.hrms.Dto;

public class EmployeeTypeDto extends AbstractDTO{
	private String type;
	private int noOfLeaves;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNoOfLeaves() {
		return noOfLeaves;
	}
	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}
	

}
