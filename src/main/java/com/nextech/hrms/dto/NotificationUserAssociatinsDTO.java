package com.nextech.hrms.dto;






public class NotificationUserAssociatinsDTO extends AbstractDTO {

	private boolean bcc;
	private boolean cc;
	private boolean to;
	private NotificationDTO notificationId;
	private EmployeeDto employeeId;
	
	public NotificationUserAssociatinsDTO(){
		
	}
	public NotificationUserAssociatinsDTO(int id){
		this.setId(id);
		
	}
	public boolean isBcc() {
		return bcc;
	}
	public void setBcc(boolean bcc) {
		this.bcc = bcc;
	}
	public boolean isCc() {
		return cc;
	}
	public void setCc(boolean cc) {
		this.cc = cc;
	}
	public boolean isTo() {
		return to;
	}
	public void setTo(boolean to) {
		this.to = to;
	}

	public NotificationDTO getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(NotificationDTO notificationId) {
		this.notificationId = notificationId;
	}
	
	
	public EmployeeDto getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(EmployeeDto employeeId) {
		this.employeeId = employeeId;
	}
	public boolean getBcc() {
		return this.bcc;
	}

	public boolean getCc() {
		return this.cc;
	}

	public boolean getTo() {
		return this.to;
	}
	
	
}
