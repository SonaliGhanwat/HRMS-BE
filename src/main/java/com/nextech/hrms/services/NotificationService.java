package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Notification;
import com.nextech.hrms.dto.NotificationDTO;

public interface NotificationService extends CRUDService<Notification>{

	
	public List<NotificationDTO> getNofificationList(List<NotificationDTO> notificationDTOs) throws Exception;
	
	public NotificationDTO getNotificationDTOById(long id) throws Exception;
	
	public NotificationDTO deleteNofificationById(long id) throws Exception;
	
	public NotificationDTO getNotificationByCode(String code)throws Exception;

}
