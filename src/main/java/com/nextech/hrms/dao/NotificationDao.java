package com.nextech.hrms.dao;

import com.nextech.hrms.model.Notification;

public interface NotificationDao extends SuperDao<Notification>{

	
	public Notification getNotificationByCode(String code) throws Exception;

}
