package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;











import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.dto.Mail;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.dto.NotificationUserAssociatinsDTO;
import com.nextech.hrms.services.MailService;
import com.nextech.hrms.services.NotificationUserAssociationService;

@Service
public class MailServiceImpl  implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	VelocityEngine velocityEngine;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	NotificationUserAssociationService notificationUserAssociationService;

	@Async
	public void sendEmail( Mail mail,NotificationDTO notification) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

		       MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

	            mimeMessageHelper.setSubject(mail.getMailSubject());
	    //        mimeMessageHelper.setFrom(mail.getMailFrom());
	            String[] bccAddress = mail.getMailBcc().split(",");
	            mimeMessageHelper.setBcc(bccAddress);
	            
	            String[] toAddress = mail.getMailTo().split(",");
	            mimeMessageHelper.setTo(toAddress);
	            
	            String[] ccAddress = mail.getMailCc().split(",");
	            mimeMessageHelper.setCc(ccAddress);
	            
	            mail.setMailContent(geContentFromTemplate(mail.getModel(),notification));
	            mimeMessageHelper.setText(mail.getMailContent(), true);
	            	 FileSystemResource fileSystemResource = new FileSystemResource(mail.getAttachment());
	                 mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);


	            mailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public String geContentFromTemplate(Map<String, Object> model,NotificationDTO notification) {
		StringBuffer content = new StringBuffer();
		try {
			content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, notification.getTemplate(), model));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

	@Async
	public void sendEmailWithoutPdF( Mail mail,NotificationDTO notification) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {

		       MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

	            mimeMessageHelper.setSubject(mail.getMailSubject());
	         //   mimeMessageHelper.setFrom(mail.getMailFrom());
	            
	            String[] bccAddress = mail.getMailBcc().split(",");
	            mimeMessageHelper.setBcc(bccAddress);
	            
	            String[] toAddress = mail.getMailTo().split(",");
	            mimeMessageHelper.setTo(toAddress);
	            
	            String[] ccAddress = mail.getMailCc().split(",");
	            mimeMessageHelper.setCc(ccAddress);
	            
	            mail.setMailContent(geContentFromTemplate(mail.getModel(),notification));
	            mimeMessageHelper.setText(mail.getMailContent(), true);
	            mailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Mail setMailCCBCCAndTO(NotificationDTO notificationDTO)throws Exception {
		StringBuilder stringBuilderCC = new StringBuilder();
		StringBuilder stringBuilderTO = new StringBuilder();
		StringBuilder stringBuilderBCC = new StringBuilder();

		String prefixCC = "";
		String prefixTO = "";
		String prefixBCC = "";

		String multipleCC = "";
		String multipleBCC = "";
		String multipleTO = "";
		Mail mail = new Mail();
		List<NotificationUserAssociatinsDTO> notificationUserAssociatinsDTOs = notificationUserAssociationService.getNotificationUserAssociatinsDTOs(notificationDTO.getId());
		
		List<Long> ids = new ArrayList<Long>();
		for (NotificationUserAssociatinsDTO notificationUserAssociatinsDTO : notificationUserAssociatinsDTOs) {
			ids.add(notificationUserAssociatinsDTO.getEmployeeId().getId());
		}
		List<Employee> userList = employeeDao.getMultipleUsersById(ids);
		for (NotificationUserAssociatinsDTO notificationuserassociation : notificationUserAssociatinsDTOs) {
			for (Employee user : userList) {
				if (notificationuserassociation.getTo()) {
					if(notificationuserassociation.getEmployeeId().getId()==user.getId()){
					stringBuilderTO.append(prefixTO);
					prefixTO = ",";
					stringBuilderTO.append(user.getEmailid());
					multipleTO = stringBuilderTO.toString();
					}
				} else if (notificationuserassociation.getBcc()) {
					if(notificationuserassociation.getEmployeeId().getId()==user.getId()){
					stringBuilderBCC.append(prefixBCC);
					prefixBCC = ",";
					stringBuilderBCC.append(user.getEmailid());
					multipleBCC = stringBuilderBCC.toString();
					}
				} else if (notificationuserassociation.getCc()) {
					if(notificationuserassociation.getEmployeeId().getId()==user.getId()){
					stringBuilderCC.append(prefixCC);
					prefixCC = ",";
					stringBuilderCC.append(user.getEmailid());
					multipleCC = stringBuilderCC.toString();
					}
				}
			}
		}
		mail.setMailSubject(notificationDTO.getSubject());
		mail.setMailTo(multipleTO);
		mail.setMailBcc(multipleBCC);
		mail.setMailCc(multipleCC);
		return mail;
	}

	@Override
	public String getSubject(NotificationDTO notificationDTO) {
		Mail mail = new Mail();
         mail.setMailSubject(notificationDTO.getSubject());
		String mailSubject = mail.getMailSubject();
		return mailSubject;
	}

	@Override
	public String getContent(NotificationDTO notificationDTO) {
		return null;
	}
}
