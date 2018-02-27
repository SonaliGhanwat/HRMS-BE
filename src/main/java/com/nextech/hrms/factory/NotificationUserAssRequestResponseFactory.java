package com.nextech.hrms.factory;

import com.nextech.hrms.model.Notification;
import com.nextech.hrms.model.Notificationuserassociation;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.dto.NotificationUserAssociatinsDTO;
import com.nextech.hrms.dto.EmployeeDto;;

public class NotificationUserAssRequestResponseFactory {
	
	public static Notificationuserassociation  setNotificationUserAss(NotificationUserAssociatinsDTO notificationUserAssociatinsDTO){
		Notificationuserassociation notificationuserassociation = new Notificationuserassociation();
		notificationuserassociation.setBcc(notificationUserAssociatinsDTO.getBcc());
		notificationuserassociation.setCc(notificationUserAssociatinsDTO.getCc());
		notificationuserassociation.setTo(notificationUserAssociatinsDTO.getTo());
		notificationuserassociation.setId(notificationUserAssociatinsDTO.getId());
		Employee employee  = new Employee();
		employee.setId(notificationUserAssociatinsDTO.getEmployeeId().getId());
		notificationuserassociation.setEmployee(employee);
		Notification notification =  new Notification();
		notification.setId(notificationUserAssociatinsDTO.getNotificationId().getId());
		notificationuserassociation.setNotification(notification);
		notificationuserassociation.setIsactive(true);
		return notificationuserassociation;
	}
	public static NotificationUserAssociatinsDTO setNotifiactionDTO(Notificationuserassociation notificationuserassociation){
		NotificationUserAssociatinsDTO notificationUserAssociatinsDTO = new NotificationUserAssociatinsDTO();
		notificationUserAssociatinsDTO.setBcc(notificationuserassociation.getBcc());
		notificationUserAssociatinsDTO.setCc(notificationuserassociation.getCc());
		notificationUserAssociatinsDTO.setTo(notificationuserassociation.getTo());
		notificationUserAssociatinsDTO.setId(notificationuserassociation.getId());
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(notificationuserassociation.getEmployee().getId());
		employeeDto.setUserid(notificationuserassociation.getEmployee().getUserid());
		employeeDto.setFirstName(notificationuserassociation.getEmployee().getFirstName());
		notificationUserAssociatinsDTO.setEmployeeId(employeeDto);
		NotificationDTO notificationDTO =  new NotificationDTO();
		notificationDTO.setId(notificationuserassociation.getNotification().getId());
		notificationDTO.setSubject(notificationuserassociation.getNotification().getSubject());
		notificationDTO.setName(notificationuserassociation.getNotification().getName());
		notificationUserAssociatinsDTO.setNotificationId(notificationDTO);
		notificationUserAssociatinsDTO.setIsActive(true);
		return notificationUserAssociatinsDTO;
	}

}
