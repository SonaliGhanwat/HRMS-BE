package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
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

import com.nextech.hrms.factory.UserTypePageAssoFactory;
import com.nextech.hrms.model.Page;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.model.Usertypepageassociation;
import com.nextech.hrms.dto.PageDTO;
import com.nextech.hrms.dto.UserTypePageAssoDTO;
import com.nextech.hrms.dto.UserTypePageAssoPart;
import com.nextech.hrms.services.PageService;
import com.nextech.hrms.services.UserTypeServices;
import com.nextech.hrms.services.UsertypepageassociationService;
import com.nextech.hrms.model.Status;

@Controller
@RequestMapping("/usertypepageassociation")
public class UsertypepageassociationController {

	@Autowired
	UsertypepageassociationService usertypepageassociationService;
	
	@Autowired
	PageService pageService;
	
	@Autowired
	UserTypeServices userTypeService;

	@Autowired
	private MessageSource messageSource;
	
	static Logger logger = Logger.getLogger(UsertypepageassociationController.class);
	

	@RequestMapping(value = "/createMultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addMultipleUserTypePageAsso(
			@Valid @RequestBody UserTypePageAssoDTO userTypePageAssoDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			List<UserTypePageAssoPart> userTypePageAssoParts =	userTypePageAssoDTO.getUserTypePageAssoParts();
			if(!userTypePageAssoParts.isEmpty()){
			for (UserTypePageAssoPart userTypePageAssoPart : userTypePageAssoParts) {	
			if (usertypepageassociationService.getUserTypePageAssoByPageIduserTypeId((userTypePageAssoPart.getPageId().getId()),userTypePageAssoDTO.getUsertypeId().getId()) == null){
				Usertypepageassociation usertypepageassociation =	 UserTypePageAssoFactory.setUserTypePageAss(userTypePageAssoDTO);
				 usertypepageassociation =  setMultiplePage(userTypePageAssoPart);
					usertypepageassociation.setUsertype(userTypeService.getEntityById(Usertype.class, userTypePageAssoDTO.getUsertypeId().getId()));
					usertypepageassociation.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
					usertypepageassociationService.addEntity(usertypepageassociation);
			}else{
				PageDTO pageDTO = pageService.getPageDTOById(userTypePageAssoPart.getPageId().getId());
				String pageName = pageDTO.getPageName()+" already exists";
				return new Status(2, pageName);
			}
			}
			}else{
				return new Status(2,"Please select page and click on add button");
			}
			return new Status(1,"User Type Page Association added uccessfully !");
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getUserTypePageAsso(
			@PathVariable("id") long id) {
		UserTypePageAssoDTO userTypePageAssoDTO = null;
		try {
			userTypePageAssoDTO = usertypepageassociationService.getUserTypeDto(id);
			if(userTypePageAssoDTO==null){
				logger.info("There is no any user type page association");
				return new Status(1,"There is no any user type page association");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return new Status(1,"",userTypePageAssoDTO);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getUserTypePageAsso(
			@PathVariable("id") long id) {
		List<Usertypepageassociation> usertypepageassociations = null;
		try {
			usertypepageassociations = usertypepageassociationService.getUserTypeDtoList(id);
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return new Status(1,"",usertypepageassociations);
	}*/
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateUserTypePageAsso(
			@RequestBody UserTypePageAssoDTO userTypePageAssoDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			usertypepageassociationService.addEntity(UserTypePageAssoFactory.setUserTypePageAssUpdate(userTypePageAssoDTO, request));
			return new Status(1,"Usertypepageassociation update Successfully !");
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getUserTypePageAsso() {

		List<UserTypePageAssoDTO> userTypePageAssoDTOs = null;
		try {
			userTypePageAssoDTOs = usertypepageassociationService.getUserTypePageAssList();
			if(userTypePageAssoDTOs.isEmpty()){
				logger.info("There is no any user type page association list");
				return new Status(1,"There is no any user type page association list");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Status(1,"",userTypePageAssoDTOs);
	}
	
	@RequestMapping(value = "/UserTypePageAsso/{UserTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getPageAssByUserTypeId(@PathVariable("UserTypeId") long id) {

		List<UserTypePageAssoDTO> UserTypePageAssoDTO = null;
		try {
			UserTypePageAssoDTO = usertypepageassociationService.getPagesByUsertype(id);
			if(UserTypePageAssoDTO==null){
				logger.info("There is no any user type page association");
				return new Status(1,"There is no user type page association");
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return new Status(1,"",UserTypePageAssoDTO);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteUserTypePageAsso(@PathVariable("id") long id) {

		try {
			UserTypePageAssoDTO userTypePageAssoDTO =	usertypepageassociationService.deleteUserTypePage(id);
			if(userTypePageAssoDTO==null){
				logger.info("There is no any user type page association");
				return new Status(1,"There is no any user type page assocition");
			}
			PageDTO pageDTO = pageService.getPageDTOById(userTypePageAssoDTO.getPage().getId());
			String message = "From User Type Pagee Association "+pageDTO.getPageName()+" page deleted successfully ";
			return new Status(1,message);
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}

	}
	
	private Usertypepageassociation setMultiplePage(UserTypePageAssoPart userTypePageAssoPart) throws Exception {
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setPage(pageService.getEntityById(Page.class, userTypePageAssoPart.getPageId().getId()));
		usertypepageassociation.setIsactive(true);
		return usertypepageassociation;
	}
}
