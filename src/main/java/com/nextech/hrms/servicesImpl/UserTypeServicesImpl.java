package com.nextech.hrms.servicesImpl;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.UserTypeDao;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.services.UserTypeServices;

public class UserTypeServicesImpl implements UserTypeServices {

	@Autowired
	UserTypeDao userTypeDao;
	
	@Override
	public boolean addEntity(Usertype usertype) throws Exception {
		return userTypeDao.addEntity(usertype);
	}

	@Override
	public Usertype getEntityById(long id) throws Exception {
		return userTypeDao.getEntityById(id);
	}

	@Override
	public List<Usertype> getEntityList() throws Exception {
		return userTypeDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return userTypeDao.deleteEntity(id);
	}
	@Override
	public boolean updateEntity(Usertype usertype) throws Exception {
		return userTypeDao.updateEntity(usertype);
	}

	@Override
	public Usertype getUserTypeByIdandName(String name)throws Exception {
		return userTypeDao.getUserTypeByIdandName(name);
	}


}
