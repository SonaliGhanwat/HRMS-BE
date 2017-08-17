package com.nextech.hrms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.HolidayDto;
import com.nextech.hrms.factory.HolidayFactory;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.model.Status;
import com.nextech.hrms.services.HolidayServices;

@RestController
public class HolidayController {

	@Autowired
	HolidayServices holidayServices;
	
	@Transactional @RequestMapping(value = "/holidayExcel",headers = "Content-Type=*/*", method = RequestMethod.POST)
	public Status processExcel(@RequestParam("holidayExcelFile") MultipartFile holidayExcelFile) {
		List<HolidayDto> holidayDtos = new ArrayList<>();
		try {
			
			//int i = 1;
	        Workbook workbook = new XSSFWorkbook(holidayExcelFile.getInputStream());
	        Sheet worksheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = worksheet.iterator();
	        while (iterator.hasNext()) {
	        	Row row = iterator.next();
	        	if(row.getRowNum()!=0){
				HolidayDto holidayDto = new HolidayDto();
				//holiday.setId((int)(row.getCell(1).getNumericCellValue()));
				holidayDto.setHolidayDate(new Date(row.getCell(1).getDateCellValue().getTime()));
				holidayDto.setHolidayName(row.getCell(2).getStringCellValue());
				holidayDtos.add(holidayDto);
	        	}
			}			
			//workbook.close();
	        for (HolidayDto holidayDto : holidayDtos) {
	        	holidayDto.setHolidayName(holidayDto.getHolidayName());
	        	holidayDto.setIsActive(true);
	        	holidayDto.setHolidayDate(holidayDto.getHolidayDate());
	        	holidayServices.addEntity(HolidayFactory.setHoliday(holidayDto));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Status(1, " Holiday List!",holidayDtos);
	}
}
