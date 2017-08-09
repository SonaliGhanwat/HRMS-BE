package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.nextech.hrms.dao.SuperDao;

@Transactional
public class SuperDaoImpl<T> implements SuperDao<T>{

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public Long add(T bean) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Long id = (Long) session.save(bean);
		tx.commit();
		return id;
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public T getById(Class<T> z,long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(z);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("id", id));
		T t= criteria.list().size() > 0 ? (T) criteria.list().get(0): null;
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(Class<T> z) throws Exception {
		session = sessionFactory.openSession();
		//@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(z);
		criteria.add(Restrictions.eq("isActive", true));
		List<T> list = criteria.list();
		return list;
	}

	@Override
	public boolean delete(Class<T> z,long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(z, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return true;
	}

	@Override
	public T update(T bean) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.merge(bean);
		tx.commit();
		return bean;
	}

}