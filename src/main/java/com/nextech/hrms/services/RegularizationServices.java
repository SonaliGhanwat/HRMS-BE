package com.nextech.hrms.services;

import java.util.Date;

import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Regularization;

public interface RegularizationServices extends CRUDService<Regularization>{

	public Regularization getRegularizationByUseridandDate(long empId,Date date)throws Exception;
}
