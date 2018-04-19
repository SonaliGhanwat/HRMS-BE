package com.nextech.hrms.dto;

public class UserTypeDto extends AbstractDTO {
	private String description;
	private String usertypeName;
	
	public UserTypeDto(){
		
	}
	public UserTypeDto(int id){
		this.setId(id);
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsertypeName() {
		return usertypeName;
	}
	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}
}
