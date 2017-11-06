package com.nextech.hrms.daoImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeetypeDao;
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.services.EmployeetypeService;

@Repository
@Transactional
public class EmployeetypeDaoImpl extends SuperDaoImpl<Employeetype> implements EmployeetypeDao {

}
