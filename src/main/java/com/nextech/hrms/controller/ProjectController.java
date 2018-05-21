package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.model.Department;
import com.nextech.hrms.model.Project;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private MessageSource messageSource;
	
	static  Logger logger = Logger.getLogger(DeprtmentController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addProject(@Valid @RequestBody Project project) {
		try {
			
			project.setActive(true);
			projectService.addEntity(project);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Project_Added_Successfully, null, null));
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.getCause().getMessage());
		}
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Project getProject(@PathVariable("id") long id) {
		Project project = null;
		try {
			project = projectService.getEntityById(Project.class,
					id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateProject(
			@RequestBody Project project, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			project.setActive(true);
			projectService.updateEntity(project);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Project_Update_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.getCause().getMessage());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Project> getProject() {

		List<Project> projects = null;
		try {
			projects = projectService.getEntityList(Project.class);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return projects;
	}@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteProject(@PathVariable("id") long id) {

		try {
			Project project = projectService.getEntityById(
					Project.class, id);
			project.setActive(false);
			projectService.updateEntity(project);
			return new Status(0, messageSource.getMessage(
					MessageConstant.Project_Delete_Successfully, null, null));
		} catch (Exception e) {
			logger.error(e);
			return new Status(0, e.toString());
		}
	}
}
