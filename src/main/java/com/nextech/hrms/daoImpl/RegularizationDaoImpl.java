package com.nextech.hrms.daoImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.RegularizationDao;
import com.nextech.hrms.model.Regularization;

@Repository
@Transactional
public class RegularizationDaoImpl extends SuperDaoImpl<Regularization> implements RegularizationDao{

}
