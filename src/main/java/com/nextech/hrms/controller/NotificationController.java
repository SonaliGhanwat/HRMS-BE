package com.nextech.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.hrms.factory.NotificationRequestResponseFactory;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.NotificationService;


@Controller
 @RequestMapping("/notification")
public class NotificationController {

	@Autowired
	NotificationService notificationservice;
	
	@Autowired
	private MessageSource messageSource;

	static  Logger logger = Logger.getLogger(NotificationController.class);
	
	 @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addNotification(@Valid @RequestBody NotificationDTO notificationDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			
			
			notificationservice.addEntity(NotificationRequestResponseFactory.setNotification(notificationDTO));
			return new Status(1, messageSource.getMessage(MessageConstant.Notification_Added_Successfully, null,null));
			
		} catch (ConstraintViolationException cve) {
		logger.error(cve);
			return new Status(0, cve.getMessage());
		
	}
	 }

	 @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getNotification(@PathVariable("id") long id) {
		NotificationDTO notification = null;
		try {
			notification = notificationservice.getNotificationDTOById(id);
			if(notification==null){
				
				return  new Status(1,messageSource.getMessage(MessageConstant.Notification_Does_Not_Exits, null,null));
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return new Status(1,"",notification);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateNotification(@RequestBody NotificationDTO notificationDTO,
			HttpServletRequest request, HttpServletResponse response) {
		try {
	
			notificationservice.updateEntity(NotificationRequestResponseFactory.setNotification(notificationDTO));
			return new Status(1, messageSource.getMessage(MessageConstant.Notification_Update_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	 @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getNotification() {

		List<NotificationDTO> notificationList = null;
		try {
			notificationList = notificationservice.getNofificationList(notificationList);
			if(notificationList==null){
				
				return new Status(1,"There is no any list");
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return new Status(1, "",notificationList);
	}

 @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteNotification(@PathVariable("id") long id) {

		try {
			NotificationDTO notificationDTO =notificationservice.deleteNofificationById(id);
			if(notificationDTO==null){
				
				return  new Status(1,messageSource.getMessage(MessageConstant.Notification_Does_Not_Exits, null,null));
			}
			return new Status(1, messageSource.getMessage(MessageConstant.Notification_Delete_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
}

