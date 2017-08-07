package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Usertype;

public interface UserTypeDao {

	public boolean addEntity(Usertype usertype) throws Exception;
	
	public Usertype getEntityById(long id) throws Exception;
	
	public List<Usertype> getEntityList() throws Exception;
	
	public boolean deleteEntity(long id) throws Exception;
	
	public boolean updateEntity(Usertype usertype) throws Exception;
	
	public Usertype getUserTypeByIdandName(String name)throws Exception;
	
	
}
