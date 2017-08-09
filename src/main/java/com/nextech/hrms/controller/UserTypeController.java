package com.nextech.hrms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.UserTypeServices;

@Controller
@RequestMapping("/usertype")
public class UserTypeController {
	public static final String USER_DOES_NOT_EXISTS = "We are sorry. This user does not exist.";

	@Autowired
	UserTypeServices userTypeServices;

	static final Logger logger = Logger.getLogger(UserTypeController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestBody Usertype usertype) {
		try {
			Usertype usertype1 = userTypeServices.getUserTypeByIdandName(usertype.getUsertypeName());
			if(usertype1==null){
			usertype.setIsActive(true);
			userTypeServices.addEntity(usertype);
			}else{
				return new Status(1, " Usertype Name Already Exit");
			}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		return new Status(1, "Employee added Successfully !");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		Usertype usertype = null;
		try {
			usertype = userTypeServices.getEntityById(Usertype.class, id);
			if(usertype==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, "Employee List",usertype);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<Usertype> getEmployee() {

		List<Usertype> usertypeList = null;
		try {
			usertypeList = userTypeServices.getEntityList(Usertype.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usertypeList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {
		Usertype usertype = null;

		try {
			usertype =userTypeServices.getEntityById(Usertype.class, id);
            if(usertype==null){
				return new Status(1,USER_DOES_NOT_EXISTS);
			}
            usertype.setIsActive(false);
            userTypeServices.updateEntity(usertype);
		   //employeeServices.deleteEntity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Status(1, "Employee deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody Usertype usertype) {
		try {
			usertype.setIsActive(true);
			userTypeServices.updateEntity(usertype);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1, "Employee Update Successfully !");
	}
	
}
