package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
			criteria.add(Restrictions.eq("designation.id", id));
			return criteria.list().size() > 0 ? (List<Employee>)criteria.list() : null;
		}

	@Override
	public Employee getEmployeeByUserIdforLeave(long userId) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("isActive", true));
		criteria.add(Restrictions.eq("userid", userId));
		Employee employee= criteria.list().size() > 0 ? (Employee) criteria.list().get(0): null;
		session.close();
		return employee;
	}

	@Override
	public List<Employee> getEmployeeByReportTo(int id) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("reportTo", id));
		return criteria.list().size() > 0 ? (List<Employee>)criteria.list() : null;
	}

	@Override
	public List<Employee> getMultipleUsersById(List<Long> ids) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.in("id", ids));
		return criteria.list().size() > 0 ? (List<Employee>)criteria.list() : null;
	}
	

}
