package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Employeetype;

public interface EmployeetypeService extends CRUDService<Employeetype> {
	public Employeetype getEmployeetypeByUserid(long empId) throws Exception;

}
