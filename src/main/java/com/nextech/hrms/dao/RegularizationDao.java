package com.nextech.hrms.dao;

import java.util.Date;

import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Regularization;

public interface RegularizationDao extends SuperDao<Regularization>{

	public Regularization getRegularizationByUseridandDate(long empId,Date date)throws Exception;
}
