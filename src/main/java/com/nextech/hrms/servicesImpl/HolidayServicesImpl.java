package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.dto.HolidayDto;
import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.dao.HolidayDao;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.factory.HolidayFactory;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Holiday;
import com.nextech.hrms.model.Usertype;
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

	@Override
	public HolidayDto getHolidayDtoByid(long id) throws Exception {
		Holiday holiday =  holidayDao.getById(Holiday.class, id);
		HolidayDto holidayDto = HolidayFactory.setHolidayList(holiday);
		holiday.setActive(false);
		holidayDao.update(holiday);
		return holidayDto;
	}

	@Override
	public HolidayDto getHolidayDto(long id) throws Exception {
		Holiday  usertype =  holidayDao.getById(Holiday.class, id);
		HolidayDto userTypeDto = HolidayFactory.setHolidayList(usertype);
		return userTypeDto;
	}

	@Override
	public List<Holiday> getHolidayList(Date date) throws Exception {
		// TODO Auto-generated method stub
		return holidayDao.getHolidayList(date);
	}

	@Override
	public Holiday getHolidayBYDate(Date date) throws Exception {
		return holidayDao.getHolidayBYDate(date);
	}

	@Override
	public List<Holiday> getHolidayListByYearandMonth(Date date)
			throws Exception {
		// TODO Auto-generated method stub
		return holidayDao.getHolidayListByYearandMonth(date);
	}
	
	

	
}
