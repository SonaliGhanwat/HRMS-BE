package com.nextech.hrms.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.DepartmentDao;
import com.nextech.hrms.dao.DesignationDao;
import com.nextech.hrms.model.Department;
import com.nextech.hrms.services.DepartmentService;

@Service
public class DepartmentServiceImpl extends CRUDServiceImpl<Department> implements DepartmentService{

	@Autowired
	DepartmentDao departmentDao;
}
