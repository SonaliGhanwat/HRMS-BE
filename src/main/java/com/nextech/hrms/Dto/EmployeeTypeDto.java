package com.nextech.hrms.Dto;

public class EmployeeTypeDto extends AbstractDTO{
	private String type;
	private int noOfLeves;
	public int getNoOfLeves() {
		return noOfLeves;
	}
	public void setNoOfLeves(int noOfLeves) {
		this.noOfLeves = noOfLeves;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
