package com.nextech.hrms.controller;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Regularization;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.RegularizationServices;

@RestController
@RequestMapping("/regularization")
public class RegularizationController {

	
	@Autowired
	RegularizationServices regularizationServices;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody Status addRegularization(
			@Valid @RequestBody Regularization regularization,
			BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new Status(0, bindingResult.getFieldError().getDefaultMessage());
			}
			regularization.setActive(true);
			regularizationServices.addEntity(regularization);
			return new Status(0, "Regularization added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new Status(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new Status(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new Status(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Regularization getRegularization(@PathVariable("id") long id) {
		Regularization regularization = null;
		try {
			regularization = regularizationServices.getEntityById(Regularization.class,
					id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return regularization;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody Status updateRegularization(
			@RequestBody Regularization regularization, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			regularization.setActive(true);
			regularizationServices.updateEntity(regularization);
			return new Status(0, "Regularization update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(0, e.toString());
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Regularization> getRegularization() {

		List<Regularization> regularizations = null;
		try {
			regularizations = regularizationServices.getEntityList(Regularization.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return regularizations;
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody Status deleteRegularization(@PathVariable("id") long id) {

		try {
			Regularization regularization = regularizationServices.getEntityById(
					Regularization.class, id);
			regularization.setActive(false);
			regularizationServices.updateEntity(regularization);
			return new Status(0, "Regularization deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}

}
