package com.nextech.hrms.controller;

import java.util.ArrayList;
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
import com.nextech.hrms.dto.EmployeeLeaveDto;
import com.nextech.hrms.dto.NotificationDTO;
import com.nextech.hrms.dto.ProjectDto;
import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Department;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Project;
import com.nextech.hrms.model.Regularization;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.EmployeeServices;
import com.nextech.hrms.services.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	EmployeeServices employeeServices;
	
	static  Logger logger = Logger.getLogger(DeprtmentController.class);
	
	@RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addProject(@Valid @RequestBody Project project, @PathVariable("userId") String userId) {
		try {
			
			 Employee employee = employeeServices.getEmployeeByUserId(userId);
			project.setActive(true);
			String status = "New Request for Project";
			project.setStatus(status);
			project.setCreatedBy( employee.getId());
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
	}
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
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
	
	@RequestMapping(value = "/getProjectByStatus/{userId}", method = RequestMethod.GET)
	public @ResponseBody Status getProjectByStatus(@PathVariable("userId") String userId,HttpServletRequest request ) {
		List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
		List<Project> projects =null;
		try {
		
	        Employee employee = employeeServices.getEmployeeByUserId(userId);
	        List<Employee> employees = employeeServices.getEmployeeByReportTo((int) employee.getId());
	        for (Employee employee2 : employees) {
	        	projects =  projectService.getProjectByCreateBY(employee2.getId());
	        	for (Project project : projects) {
	        		
	        		if(project.getStatus().equals("New Request for Project")){
	        			ProjectDto projectDto= new ProjectDto();	   				
	        			projectDto.setId(project.getId());
	        			projectDto.setName(project.getName());
	        			projectDto.setStartDate(project.getStartDate());
	        			projectDto.setEndDate(project.getEndDate());
	        			projectDto.setStatus(project.getStatus());
	        			projectDto.setProjecttype(project.getProjecttype());
	        			projectDto.setCreatedBy(project.getCreatedBy());
	        			projectDtos.add(projectDto);
		        		
		        	}
				}if(projectDtos.size()==0){
	        	 return new Status(1,"Project does not exits") ;
				}
	        }       
	      
		} catch (Exception e) {
			e.printStackTrace();	
			 
		}
		return new Status(0,"",projectDtos) ;
	}
	
	@RequestMapping(value = "/projectStatusUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addProjectStatus(@RequestBody  ProjectDto projectDto, HttpServletRequest request ) {
		try {
				Project project = projectService.getEntityById(Project.class, projectDto.getId());
				project.setStatus(projectDto.getStatus());
				projectService.updateEntity(project);										
		} catch (Exception e) {
			logger.error(e);
		}
		 return new Status(1, "Status Update Successfully");
	}
}
