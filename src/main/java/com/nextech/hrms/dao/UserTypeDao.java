package com.nextech.hrms.dao;
import java.util.List;

import com.nextech.hrms.model.Usertype;

public interface UserTypeDao extends SuperDao<Usertype> {

	
	public Usertype getUserTypeByIdandName(String name)throws Exception;
	
	
}
