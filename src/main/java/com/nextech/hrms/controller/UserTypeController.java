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




import com.nextech.hrms.Dto.UserTypeDto;
import com.nextech.hrms.factory.UserTypeFactory;
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
	public @ResponseBody Status addEmployee(@RequestBody UserTypeDto userTypeDto) {
		try {
			Usertype usertype1 = userTypeServices.getUserTypeByIdandName(userTypeDto.getUsertypeName());
			if(usertype1==null){
				userTypeDto.setIsActive(true);
			userTypeServices.addEntity(UserTypeFactory.setUserType(userTypeDto));
			}else{
				return new Status(1, " Usertype Name Already Exist");
			}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		return new Status(1, "UserType added Successfully !");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status getEmployee(@PathVariable("id") long id) {
		UserTypeDto userTypeDto = null;
		try {
			userTypeDto = userTypeServices.getUserTypeDto(id);

		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,USER_DOES_NOT_EXISTS);
		}
		 return new Status(1, "UserType List",userTypeDto);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<UserTypeDto> getEmployee() {

		List<UserTypeDto> userTypeDtoList = null;
		try {
			userTypeDtoList = userTypeServices.getUserTypeList(userTypeDtoList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userTypeDtoList;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {
		UserTypeDto userTypeDto = null;

		try {
			userTypeDto =userTypeServices.getUserTypeDtoByid(id);
           
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,USER_DOES_NOT_EXISTS);
		}
		return new Status(1, "UserType deleted Successfully !");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody UserTypeDto userTypeDto) {
		try {
			userTypeDto.setIsActive(true);
			userTypeServices.updateEntity(UserTypeFactory.setUserTypeUpdate(userTypeDto));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1, "UserType Update Successfully !");
	}
	
}
