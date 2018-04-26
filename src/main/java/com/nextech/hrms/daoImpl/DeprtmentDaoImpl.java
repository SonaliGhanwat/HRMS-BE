package com.nextech.hrms.daoImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.DepartmentDao;
import com.nextech.hrms.model.Department;

@Repository
@Transactional
public class DeprtmentDaoImpl extends SuperDaoImpl<Department> implements DepartmentDao{

}
