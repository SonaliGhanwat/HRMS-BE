package com.nextech.hrms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.services.UserTypeServices;

@Controller
@RequestMapping("/usertype")
public class UserTypeController {

	@Autowired
	UserTypeServices userTypeServices;
	
	@Autowired
	private MessageSource messageSource;

	static final Logger logger = Logger.getLogger(UserTypeController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestBody UserTypeDto userTypeDto) {
		try {
			Usertype usertype1 = userTypeServices.getUserTypeByIdandName(userTypeDto.getUsertypeName());
			if(usertype1==null){
				userTypeDto.setIsActive(true);
			userTypeServices.addEntity(UserTypeFactory.getUserTypeModel(userTypeDto));
			}else{
				return new Status(1, " Usertype Name Already Exit");
			}
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		return new Status(0, "UserType added Successfully !");
	}

	@Transactional @RequestMapping(value = "/createExcel", headers = "Content-Type=*/*",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestParam("userTypeExcelFile") MultipartFile userTypeExcelFile) {
		try {
			List<UserTypeDto> userTypeDtos = UserTypeFactory.setUserTypeExcel(userTypeExcelFile);
			userTypeServices.addEmployeeExcel(userTypeDtos);
		} catch (Exception e) {
		     e.printStackTrace();
		}
		return new Status(1, messageSource.getMessage(MessageConstant.UserType_Added_Successfully, null,null));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	UserTypeDto getEmployee(@PathVariable("id") long id) {
		UserTypeDto userTypeDto = null;
		try {
			userTypeDto = userTypeServices.getUserTypeDto(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return userTypeDto;
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
		try {
			    userTypeServices.getUserTypeDtoByid(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.UserType_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.UserType_Delete_Successfully, null,null));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody UserTypeDto userTypeDto) {
		try {
			userTypeDto.setIsActive(true);
			userTypeServices.updateEntity(UserTypeFactory.setUserTypeUpdate(userTypeDto));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1,messageSource.getMessage(MessageConstant.UserType_Update_Successfully, null,null));
	}
	
}
