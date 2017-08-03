package com.nextech.hrms.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.model.Employeeattendance;

public class EmployeeAttendanceDaoImpl implements EmployeeAttendanceDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employeeattendance employeeattendance) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(employeeattendance);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Employeeattendance getEntityById(long id) throws Exception {

		 session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employeeattendance.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("employee.id", id));
		Employeeattendance employeeattendance= criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0): null;
		session.close();
		return employeeattendance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employeeattendance> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employeeattendance> employeeattendanceList = session.createCriteria(Employeeattendance.class)
				.list();
		tx.commit();
		session.close();
		return employeeattendanceList;
	}
	
	@Override
	public boolean deleteEntity(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employeeattendance.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
	@Override
	public boolean updateEntity(Employeeattendance employeeattendance)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(employeeattendance);
		tx.commit();
		session.close();
		return false;
	}
	
	@Override
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("date",date));
		  Employeeattendance employeeattendance = criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0) : null;
		  return employeeattendance;
	}

	@Override
	public boolean updateEntity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employeeattendance.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.update(o);
		tx.commit();
		return  false;
	}

	@Override
	public Employeeattendance getEmployeeAttendanceByEmployeeId(long empId,Date date)
			throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("date", date));
		  criteria.add(Restrictions.eq("isActive", true));
		  Employeeattendance employeeattendance = criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0) : null;
		  return employeeattendance;
	}
}
