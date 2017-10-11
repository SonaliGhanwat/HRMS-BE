package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.Dto.HolidayDto;
import com.nextech.hrms.dao.HolidayDao;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.HolidayFactory;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.services.HolidayServices;

@Service
public class HolidayServicesImpl extends CRUDServiceImpl<Holiday>implements HolidayServices {


	@Autowired
	HolidayDao holidayDao;
	
	@Override
	public void addEmployeeExcel(List<HolidayDto> holidayDtos) throws Exception {

		for (HolidayDto holidayDto : holidayDtos) {
        	holidayDto.setHolidayName(holidayDto.getHolidayName());
        	holidayDto.setIsActive(true);
        	holidayDto.setHolidayDate(holidayDto.getHolidayDate());
        	Holiday holiday = holidayDao.getHolidayByName(holidayDto.getHolidayName());
        	if(holiday==null){
        	holidayDao.add(HolidayFactory.setHoliday(holidayDto));
        	}
		}
	}

	@Override
	public Holiday getHolidayByName(String Name) throws Exception {
		return holidayDao.getHolidayByName(Name);
	}

	@Override
	public List<HolidayDto> getHolidayList(List<HolidayDto> holidayDtos)
			throws Exception {
		holidayDtos = new ArrayList<HolidayDto>();
		List<Holiday> holidayList = null;
		holidayList = holidayDao.getList(Holiday.class);
		for (Holiday holiday : holidayList) {
			HolidayDto holidayDto = HolidayFactory.setHolidayList(holiday);
			holidayDtos.add(holidayDto);
		}
		return holidayDtos;
	}
	
	

	
}