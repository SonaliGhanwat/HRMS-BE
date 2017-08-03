package com.nextech.hrms.services;


import java.util.List;
import com.nextech.hrms.model.Employeedailytask;

public interface EmployeeDailyTaskServices {

	public boolean addEntity(Employeedailytask employeedailytask) throws Exception;
	
	public Employeedailytask getEntityById(long id) throws Exception;
	
	public List<Employeedailytask> getEntityList() throws Exception;
	
	public boolean deleteEntity(long id) throws Exception;
	
	public boolean updateEntity(Employeedailytask employeedailytask) throws Exception;
	
}

