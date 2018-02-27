package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Notificationuserassociation;
import com.nextech.hrms.dto.NotificationUserAssociatinsDTO;

public interface NotificationUserAssociationService extends CRUDService<Notificationuserassociation>{

	List<Notificationuserassociation> getNotificationUserAssociationByUserId(long userId) throws Exception;
	
	List<Notificationuserassociation> getNotificationuserassociationBynotificationId(long notificationId) throws Exception;
	
	List<NotificationUserAssociatinsDTO> getNotificationUserAssociatinsDTOs(long notificationId)throws Exception;
	
	List<NotificationUserAssociatinsDTO> getNotificationUserAssoList() throws Exception;
	
	public NotificationUserAssociatinsDTO getNotificationUserById(long id) throws Exception;
	
	public NotificationUserAssociatinsDTO deleteNotificationUserAsso(long id) throws Exception;

}
