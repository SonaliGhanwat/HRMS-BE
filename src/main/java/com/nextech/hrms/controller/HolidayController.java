package com.nextech.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.HolidayDto;
import com.nextech.hrms.constant.MessageConstant;
import com.nextech.hrms.factory.HolidayFactory;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.HolidayServices;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

	@Autowired
	HolidayServices holidayServices;
	
	@Autowired
	private MessageSource messageSource;
	
	@Transactional @RequestMapping(value = "/create",headers = "Content-Type=*/*", method = RequestMethod.POST)
	public Status processExcel(@RequestParam("holidayExcelFile") MultipartFile holidayExcelFile) {
		
		try {
			List<HolidayDto> holidayDtos = HolidayFactory.setHolidayExcel(holidayExcelFile);
			holidayServices.addEmployeeExcel(holidayDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, messageSource.getMessage(MessageConstant.Holiday_Added_Successfully, null,null));
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public @ResponseBody List<HolidayDto> getEmployee() {
		List<HolidayDto> holidayDtos = new ArrayList<>();
		try {
			holidayDtos = holidayServices.getHolidayList(holidayDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return holidayDtos;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Status updateEntity(@RequestBody HolidayDto holidayDto) throws Exception {
		
		try{
		holidayServices.updateEntity(HolidayFactory.setHolidayUpdate(holidayDto));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Status(1, messageSource.getMessage(MessageConstant.Holiday_Update_Successfully, null,null));
		
	}
}
