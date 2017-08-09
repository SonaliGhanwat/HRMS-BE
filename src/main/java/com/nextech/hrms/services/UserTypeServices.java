package com.nextech.hrms.services;
import java.util.List;

import com.nextech.hrms.model.Usertype;

public interface UserTypeServices extends CRUDService<Usertype> {
	
	
	public Usertype getUserTypeByIdandName(String name)throws Exception;
	
	
}
