package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.model.Employeedailytask;

public class EmployeeDailyTaskDaoImpl implements EmployeeDailyTaskDao {
	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employeedailytask employeedailytask) throws Exception{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(employeedailytask);
		tx.commit();
		session.close();
		return false;
	}
	
	public Employeedailytask getEntityById(long id) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employeedailytask.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("id", id));
		Employeedailytask employeedailytask= criteria.list().size() > 0 ? (Employeedailytask) criteria.list().get(0): null;
		session.close();
		return employeedailytask;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employeedailytask> getEntityList() throws Exception{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employeedailytask> employeedailytaskList = session.createCriteria(Employeedailytask.class).list();
		tx.commit();
		session.close();
		return employeedailytaskList;
	}
	
	@Override
	public boolean deleteEntity(long id) throws Exception{
		session = sessionFactory.openSession();
		Object o = session.load(Employeedailytask.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		session.close();
		return false;
	}
	
	@Override
	public boolean updateEntity(Employeedailytask employeedailytask) throws Exception{
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(employeedailytask);
		tx.commit();
		session.close();
		return false;
	}

}
