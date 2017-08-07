package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employee employee) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(employee);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Employee getEntityById(long id) throws Exception {

		 session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("id", id));
		Employee employee= criteria.list().size() > 0 ? (Employee) criteria.list().get(0): null;
		session.close();
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employee> employeeList = session.createCriteria(Employee.class)
				.list();
		tx.commit();
		session.close();
		return employeeList;
	}
	
	@Override
	public boolean deleteEntity(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employee.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
	@Override
	public boolean updateEntity(Employee employee)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(employee);
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public Employee getEmployeeByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		 session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("isActive", true));
			criteria.add(Restrictions.eq("userid", userId));
			Employee employee= criteria.list().size() > 0 ? (Employee) criteria.list().get(0): null;
			session.close();
			return employee;
	}

	@Override
	public Employee getEmployeeByphoneNumber(String phoneNumber)
			throws Exception {
		session = sessionFactory.openSession();
	  Criteria criteria = session.createCriteria(Employee.class);
	  criteria.add(Restrictions.eq("phoneNumber",phoneNumber));
	  Employee employee = criteria.list().size() > 0 ? (Employee) criteria.list().get(0) : null;
	  return employee;
		
	}

	@Override
	public Employee getEmpolyeeByEmailid(String emailId) throws Exception {
		session = sessionFactory.openSession();
		  Criteria criteria = session.createCriteria(Employee.class);
		  criteria.add(Restrictions.eq("emailid",emailId));
		  Employee employee = criteria.list().size() > 0 ? (Employee) criteria.list().get(0) : null;
		  return employee;
	}

}
