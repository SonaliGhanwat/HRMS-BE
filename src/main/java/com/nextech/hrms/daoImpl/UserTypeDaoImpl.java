package com.nextech.hrms.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.UserTypeDao;
import com.nextech.hrms.model.Usertype;

@Repository
public class UserTypeDaoImpl extends SuperDaoImpl<Usertype>implements UserTypeDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Usertype getUserTypeByIdandName(String name)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Usertype.class);
		  criteria.add(Restrictions.eq("usertypeName",name));
		  Usertype usertype = criteria.list().size() > 0 ? (Usertype) criteria.list().get(0) : null;
		  return usertype;
	}
}
