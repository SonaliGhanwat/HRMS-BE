package com.nextech.hrms.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Employee;

@Repository

public class EmployeeDaoImpl extends SuperDaoImpl<Employee> implements EmployeeDao {

	@Override
	public Employee getEmployeeByUserId(String userId) throws Exception {
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
