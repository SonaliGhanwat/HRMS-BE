package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.hrms.factory.NotificationUserAssRequestResponseFactory;
import com.nextech.hrms.dto.NotificationUserAssociatinsDTO;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.NotificationUserAssociationService;


@Controller
@Transactional @RequestMapping("/notificationuserassociation")
public class NotificationuserassociationController {

	@Autowired
	NotificationUserAssociationService notificationservice;
	
	static Logger logger = Logger.getLogger(NotificationuserassociationController.class);
	
	@Transactional @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addNotificationUserAsso(@Valid @RequestBody NotificationUserAssociatinsDTO notificationUserAssociatinsDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			notificationUserAssociatinsDTO.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			notificationservice.addEntity(NotificationUserAssRequestResponseFactory.setNotificationUserAss(notificationUserAssociatinsDTO));
			return new Status(1, "Notification added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@Transactional @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getNotificationUserAsso(@PathVariable("id") long id) {
		NotificationUserAssociatinsDTO notification = null;
		try {
			notification = notificationservice.getNotificationUserById(id);
			if(notification==null){
				logger.error("There  is no any notification user association");
				return new Status(1,"There is no any notification user assocition");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return new Status(1,"",notification);
	}

	@Transactional @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateNotificationUserAsso(@RequestBody NotificationUserAssociatinsDTO notificationUserAssociatinsDTO,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			notificationUserAssociatinsDTO.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			notificationservice.updateEntity(NotificationUserAssRequestResponseFactory.setNotificationUserAss(notificationUserAssociatinsDTO));
			return new Status(1, "Notification update Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@Transactional @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getNotificationUserAssoList() {

		List<NotificationUserAssociatinsDTO> notificationList = null;
		try {
			notificationList = notificationservice.getNotificationUserAssoList();
			if(notificationList==null){
				
				return new Status(1,"There is no any user notification list");
			}

		} catch (Exception e) {
			logger.error(e);
		}

		return new Status(1,"",notificationList);
	}

	@Transactional @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteNotificationUserAsso(@PathVariable("id") long id) {

		try {
			notificationservice.deleteNotificationUserAsso(id);
			return new Status(1, "Notification deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
}

