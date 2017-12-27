package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeetypeDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.services.EmployeetypeService;

@Repository
@Transactional
public class EmployeetypeDaoImpl extends SuperDaoImpl<Employeetype> implements EmployeetypeDao {

	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public Employeetype getEmployeetypeByUserid(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeetype.class);
		  criteria.add(Restrictions.eq("empid",empId));
		  Employeetype employeetypes =criteria.list().size() > 0 ? (Employeetype) criteria.list().get(0) : null;
		  return employeetypes;
	}

}
