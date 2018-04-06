package com.nextech.hrms.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.nextech.hrms.dto.HolidayDto;
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
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addHoliday(@RequestBody HolidayDto holidayDto) {
	//public Status processExcel(@RequestParam("holidayExcelFile") MultipartFile holidayExcelFile) {
		
		try {
			holidayServices.addEntity(HolidayFactory.setHoliday(holidayDto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, messageSource.getMessage(MessageConstant.Holiday_Added_Successfully, null,null));
	}
	
	@Transactional @RequestMapping(value = "/createExcel",headers = "Content-Type=*/*", method = RequestMethod.POST)
	public Status processExcel(@RequestParam("holidayExcelFile") MultipartFile holidayExcelFile) {
		
		try {
			List<HolidayDto> holidayDtos = HolidayFactory.setHolidayExcel(holidayExcelFile);
			holidayServices.addEmployeeExcel(holidayDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, messageSource.getMessage(MessageConstant.Holiday_Added_Successfully, null,null));
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	HolidayDto getEmployee(@PathVariable("id") long id) {
		HolidayDto holidayDto = null;
		try {
			holidayDto = holidayServices.getHolidayDto(id);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		 return holidayDto;
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
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {
		//UserTypeDto userTypeDto = null;

		try {
			holidayServices.getHolidayDtoByid(id);
           
		} catch (Exception e) {
			e.printStackTrace();
			return new Status(1,messageSource.getMessage(MessageConstant.Holiday_DOES_NOT_EXISTS, null,null));
		}
		return new Status(1, messageSource.getMessage(MessageConstant.Holiday_Delete_Successfully, null,null));
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
