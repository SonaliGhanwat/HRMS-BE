package com.nextech.hrms.servicesImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.UserTypeDao;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.services.UserTypeServices;

public class UserTypeServicesImpl extends CRUDServiceImpl<Usertype>implements UserTypeServices {

	@Autowired
	UserTypeDao userTypeDao;
	
	@Override
	public Usertype getUserTypeByIdandName(String name)throws Exception {
		return userTypeDao.getUserTypeByIdandName(name);
	}


}
