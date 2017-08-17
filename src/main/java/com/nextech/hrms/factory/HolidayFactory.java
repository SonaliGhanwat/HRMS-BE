package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.HolidayDto;
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


}
