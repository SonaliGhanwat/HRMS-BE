package com.nextech.hrms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.RegularizationDao;
import com.nextech.hrms.model.Regularization;
import com.nextech.hrms.services.RegularizationServices;

@Service
public class RegularizationServicesImpl extends CRUDServiceImpl<Regularization>  implements RegularizationServices {

	@Autowired
	RegularizationDao regularizationDao;
}
