package com.nextech.hrms.services;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.dto.HolidayDto;
import com.nextech.hrms.model.Holiday;

public interface HolidayServices extends CRUDService<Holiday> {
	
	public Holiday getHolidayByName(String Name)throws Exception;
	
	public void addEmployeeExcel(List<HolidayDto> holidayDtos) throws Exception;
	
    public List<HolidayDto> getHolidayList(List<HolidayDto> holidayDtos)throws Exception;

    public HolidayDto getHolidayDtoByid(long id)throws Exception;
    
    public HolidayDto getHolidayDto(long id) throws Exception;
    
    public List<Holiday> getHolidayList(Date date) throws Exception;
    
    public Holiday getHolidayBYDate(Date date)throws Exception;

}
