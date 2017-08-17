package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.HolidayDao;
import com.nextech.hrms.model.Holiday;

@Repository
@Transactional
public class HolidayDaoImpl extends SuperDaoImpl<Holiday> implements HolidayDao{
	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	

}
