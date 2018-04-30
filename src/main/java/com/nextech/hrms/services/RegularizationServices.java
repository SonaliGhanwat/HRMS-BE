package com.nextech.hrms.services;

import java.util.Date;
import java.util.List;

import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Regularization;

public interface RegularizationServices extends CRUDService<Regularization>{

	public Regularization getRegularizationByUseridandDate(long empId,Date date)throws Exception;
	
	public List<Regularization> getRegularizationByEmployeeId(long empId)  throws Exception;
}
