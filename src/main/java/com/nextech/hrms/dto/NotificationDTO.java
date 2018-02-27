package com.nextech.hrms.dto;

import java.util.List;



public class NotificationDTO extends AbstractDTO{
	
	private String beanClass;
	private String name;
	private String subject;
	private String template;
	private String type;

	private String code;
	private String description;
	public NotificationDTO(){
		
	}
       public NotificationDTO(int id){
		this.setId(id);
	}
	
	private List<NotificationUserAssociatinsDTO> notificationUserAssociatinsDTOs;
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public List<NotificationUserAssociatinsDTO> getNotificationUserAssociatinsDTOs() {
		return notificationUserAssociatinsDTOs;
	}
	public void setNotificationUserAssociatinsDTOs(
			List<NotificationUserAssociatinsDTO> notificationUserAssociatinsDTOs) {
		this.notificationUserAssociatinsDTOs = notificationUserAssociatinsDTOs;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
