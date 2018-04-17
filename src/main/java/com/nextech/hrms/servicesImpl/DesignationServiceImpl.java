package com.nextech.hrms.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.DesignationDao;
import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.services.DesignationService;

@Service
public class DesignationServiceImpl extends CRUDServiceImpl<Designation>  implements DesignationService{

	@Autowired
	DesignationDao designationDao;
	
	
	@Override
	public List<Designation> getDesignationByUserTypeid(long usertypeid)
			throws Exception {
		return designationDao.getDesignationByUserTypeid(usertypeid);
	}

}
