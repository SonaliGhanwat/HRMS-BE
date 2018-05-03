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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.hrms.factory.PageFactory;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.dto.PageDTO;
import com.nextech.hrms.services.PageService;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Page;
import com.nextech.hrms.model.Status;

@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	PageService pageservice;
	
	@Autowired
	private MessageSource messageSource;
	
	static Logger logger = Logger.getLogger(PageController.class);
	
	@Transactional @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addPage(@Valid @RequestBody PageDTO pageDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			pageservice.addEntity(PageFactory.setPage(pageDTO, request));
			return new Status(1, messageSource.getMessage(MessageConstant.Page_Added_Successfully, null,null));
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

	@Transactional @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updatePage(@RequestBody PageDTO pageDTO,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			pageservice.updateEntity(PageFactory.setPageUpdate(pageDTO, request));
			return new Status(1, messageSource.getMessage(MessageConstant.Page_Update_Successfully, null,null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}

	@Transactional @RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getPageList() {

		List<PageDTO> pageList = null;
		try {
			pageList = pageservice.getPageDTOList(pageList);
			if(pageList==null){
				
				return new Status(1,"There is no any page list");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return new Status(1,"",pageList);
	}

	@Transactional @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Status getPageById(@PathVariable("id") long id) {
		List<Page> pages = null;
		try {
			pages = pageservice.getPageByIdList(id);
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return new Status(1,"",pages);
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deletePage(@PathVariable("id") long id) {

		try {
			Page page = pageservice.getEntityById(
					Page.class, id);
			page.setActive(false);
			pageservice.updateEntity(page);
			return new Status(0, messageSource.getMessage(MessageConstant.Page_Delete_Successfully, null,null));
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
}
