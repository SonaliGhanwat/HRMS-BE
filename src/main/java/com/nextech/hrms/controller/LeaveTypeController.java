package com.nextech.hrms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.hrms.Dto.LeaveTypeDto;
import com.nextech.hrms.Dto.UserTypeDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.LeaveTypeFactory;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.LeaveTypeServices;

@Controller
@RequestMapping("/leavetype")
public class LeaveTypeController {

	@Autowired
	LeaveTypeServices leaveTypeServices;
	
	@Autowired
	private MessageSource messageSource;

	static final Logger logger = Logger.getLogger(UserTypeController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addLeaveType(@RequestBody LeaveTypeDto leaveTypeDto) {
		try {
			
			leaveTypeServices.addEntity(LeaveTypeFactory.setLeaveType(leaveTypeDto));
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		return new Status(0, "Leave Type added Successfully !");
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	LeaveTypeDto getLeaveType(@PathVariable("id") long id) {
		LeaveTypeDto leaveTypeDto = null;
		try {
			leaveTypeDto = leaveTypeServices.getLeaveTypeDto(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return leaveTypeDto;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    List<LeaveTypeDto> getLeaveType() {

		List<LeaveTypeDto> leaveTypeDtos = null;
		try {
			leaveTypeDtos = leaveTypeServices.getLeaveTypeList(leaveTypeDtos);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveTypeDtos;
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteLeaveType(@PathVariable("id") long id) {
		//UserTypeDto userTypeDto = null;

		try {
			leaveTypeServices.getLeaveTypeDtoByid(id);
           
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.LeaveType_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.LeaveType_Delete_Successfully, null,null));
	}
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody LeaveTypeDto leaveTypeDto) {
		try {
			leaveTypeDto.setIsActive(true);
			leaveTypeServices.updateEntity(LeaveTypeFactory.setLeaveTypeUpdate(leaveTypeDto));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new Status(1,messageSource.getMessage(MessageConstant.LeaveType_Update_Successfully, null,null));
	}
	
}
