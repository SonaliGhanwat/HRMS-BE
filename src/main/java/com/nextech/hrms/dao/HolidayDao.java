package com.nextech.hrms.dao;


import java.util.Date;
import java.util.List;

import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Holiday;


public interface HolidayDao extends SuperDao<Holiday> {
	
	public Holiday getHolidayByName(String Name) throws Exception;

	public List<Holiday> getHolidayList(Date date) throws Exception;

	public Holiday getHolidayBYDate(Date date)throws Exception;

}
