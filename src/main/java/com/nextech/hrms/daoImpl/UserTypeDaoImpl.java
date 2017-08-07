package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.UserTypeDao;
import com.nextech.hrms.model.Usertype;

public class UserTypeDaoImpl implements UserTypeDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Usertype usertype) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(usertype);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Usertype getEntityById(long id) throws Exception {

		 session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Usertype.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("id", id));
		Usertype usertype= criteria.list().size() > 0 ? (Usertype) criteria.list().get(0): null;
		session.close();
		return usertype;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usertype> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Usertype> usertypeList = session.createCriteria(Usertype.class)
				.list();
		tx.commit();
		session.close();
		return usertypeList;
	}
	
	@Override
	public boolean deleteEntity(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Usertype.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
	@Override
	public boolean updateEntity(Usertype usertype)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(usertype);
		tx.commit();
		session.close();
		return false;
	}
	
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
