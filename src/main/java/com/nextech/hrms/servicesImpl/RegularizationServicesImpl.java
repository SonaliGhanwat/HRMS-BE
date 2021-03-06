package com.nextech.hrms.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.RegularizationDao;
import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Regularization;
import com.nextech.hrms.services.RegularizationServices;

@Service
public class RegularizationServicesImpl extends CRUDServiceImpl<Regularization>  implements RegularizationServices {

	@Autowired
	RegularizationDao regularizationDao;

	@Override
	public Regularization getRegularizationByUseridandDate(long empId,
			Date date) throws Exception {
		return regularizationDao.getRegularizationByUseridandDate(empId, date);
	}

	@Override
	public List<Regularization> getRegularizationByEmployeeId(long empId)
			throws Exception {
		// TODO Auto-generated method stub
		return regularizationDao.getRegularizationByEmployeeId(empId);
	}

	@Override
	public List<Regularization> getRegularizationByEmployeeIdandDate(
			long empId, Date date) throws Exception {
		// TODO Auto-generated method stub
		return regularizationDao.getRegularizationByEmployeeIdandDate(empId, date);
	}
}
