package com.nextech.hrms.services;

import com.nextech.hrms.dto.Mail;
import com.nextech.hrms.dto.NotificationDTO;

public interface MailService {

	public void sendEmail(Mail mail, NotificationDTO notification);

	public void sendEmailWithoutPdF(Mail mail, NotificationDTO notification);

	public Mail setMailCCBCCAndTO(NotificationDTO notificationDTO) throws Exception;

	public String getSubject(NotificationDTO notificationDTO);

	public String getContent(NotificationDTO notificationDTO);
}