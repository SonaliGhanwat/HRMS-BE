package com.nextech.hrms.dao;


import com.nextech.hrms.model.Holiday;


public interface HolidayDao extends SuperDao<Holiday> {
	
	public Holiday getHolidayByName(String Name)throws Exception;

}
