package com.nextech.hrms.daoImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.DesignationDao;
import com.nextech.hrms.model.Designation;

@Repository
@Transactional
public class DesignationDaoImpl extends SuperDaoImpl<Designation> implements DesignationDao{

}
