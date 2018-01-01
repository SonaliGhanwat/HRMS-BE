package com.nextech.hrms.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.HolidayDto;
import com.nextech.hrms.model.Holiday;

public class HolidayFactory {
	
	public static Holiday setHoliday(HolidayDto holidayDto)throws Exception{
		Holiday holiday = new Holiday();
		holiday.setId(holidayDto.getId());
		holiday.setHolidayDate(holidayDto.getHolidayDate());
		holiday.setHolidayName(holidayDto.getHolidayName());
		holiday.setCreatedDate(holidayDto.getCreatedDate());
		holiday.setUpdatedDate(holidayDto.getUpdatedDate());
		holiday.setActive(true);
		return holiday;
	}
	
	public static HolidayDto setHolidayList(Holiday holiday)throws Exception{
		HolidayDto holidayDto = new HolidayDto();
		holidayDto.setId(holiday.getId());
		holidayDto.setHolidayDate(holiday.getHolidayDate());
		holidayDto.setHolidayName(holiday.getHolidayName());
		holidayDto.setCreatedDate(holiday.getCreatedDate());
		holidayDto.setUpdatedDate(holiday.getUpdatedDate());
		holidayDto.setIsActive(true);
		return holidayDto;
	}
	
	public static Holiday setHolidayUpdate(HolidayDto holidayDto)throws Exception{
		Holiday holiday = new Holiday();
		holiday.setId(holidayDto.getId());
		holiday.setHolidayDate(holidayDto.getHolidayDate());
		holiday.setHolidayName(holidayDto.getHolidayName());
		holiday.setCreatedDate(holidayDto.getCreatedDate());
		holiday.setUpdatedDate(holidayDto.getUpdatedDate());
		holiday.setActive(true);
		return holiday;
	}

	public static List<HolidayDto> setHolidayExcel(MultipartFile holidayExcelFile)throws Exception{
		List<HolidayDto> holidayDtos = new ArrayList<>();
	        Workbook workbook = new XSSFWorkbook(holidayExcelFile.getInputStream());
	        Sheet worksheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = worksheet.iterator();
	        while (iterator.hasNext()) {
	        	Row row = iterator.next();
	        	if(row.getRowNum()!=0){
				HolidayDto holidayDto = new HolidayDto();
				holidayDto.setHolidayDate(new Date(row.getCell(1).getDateCellValue().getTime()));
				holidayDto.setHolidayName(row.getCell(2).getStringCellValue());
				holidayDtos.add(holidayDto);
	        	}
			}
			return holidayDtos;			
	}
}



