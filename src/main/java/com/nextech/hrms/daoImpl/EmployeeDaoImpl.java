package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeeDao;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;

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
	public Employee getEmployeeByphoneNumber(long phoneNumber)
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

	@Override
	public List<Employee> getDesignationById(long id) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employee.class);
		  criteria.add(Restrictions.eq("designation.id",id));
		  List<Employee> employees =criteria.list();
		  return employees;
	}

}
