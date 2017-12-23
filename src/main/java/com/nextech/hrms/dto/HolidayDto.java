package com.nextech.hrms.dto;

import java.util.Date;

public class HolidayDto extends AbstractDTO {
	private Date holidayDate;
	private String holidayName;
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
}
