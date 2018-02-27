package com.nextech.hrms.factory;


import com.nextech.hrms.model.Notification;

import com.nextech.hrms.dto.NotificationDTO;


public class NotificationRequestResponseFactory {
	
	public static Notification setNotification(NotificationDTO notificationDTO){
		Notification notification = new Notification();
		notification.setBeanClass(notificationDTO.getBeanClass());
		notification.setDescription(notificationDTO.getDescription());
		notification.setId(notificationDTO.getId());
		notification.setActive(true);
		notification.setName(notificationDTO.getName());
		notification.setSubject(notificationDTO.getSubject());
		notification.setTemplate(notificationDTO.getTemplate());
		notification.setType(notificationDTO.getType());
		
		notification.setCode(notificationDTO.getCode());
		return notification;
	}
	
	public static NotificationDTO setNotificationDTO(Notification notification){
		NotificationDTO notificationDTO = new NotificationDTO();
		notificationDTO.setBeanClass(notification.getBeanClass());
		notificationDTO.setDescription(notification.getDescription());
		notificationDTO.setId(notification.getId());
		notificationDTO.setIsActive(true);
		notificationDTO.setName(notification.getName());
		//StatusDTO statusDTO =  new StatusDTO();
		///statusDTO.setId(notification.getStatus1().getId());
		//statusDTO.setStatusName(notification.getStatus1().getName());
		//notificationDTO.setStatus1(statusDTO);
		//notificationDTO.setStatus2(statusDTO);
		notificationDTO.setSubject(notification.getSubject());
		notificationDTO.setTemplate(notification.getTemplate());
		notificationDTO.setType(notification.getType());
		//notificationDTO.setCreatedBy(notification.getCreatedBy());
		notificationDTO.setCreatedDate(notification.getCreatedDate());
		//notificationDTO.setUpdatedBy(notification.getUpdatedBy());
		notificationDTO.setUpdatedDate(notification.getUpdatedDate());
		notificationDTO.setCode(notification.getCode());
		return notificationDTO;
	}
}
