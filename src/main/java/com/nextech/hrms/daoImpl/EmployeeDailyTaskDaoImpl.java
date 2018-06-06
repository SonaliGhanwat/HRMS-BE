package com.nextech.hrms.daoImpl;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeeDailyTaskDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;

@Repository
public class EmployeeDailyTaskDaoImpl extends SuperDaoImpl<Employeedailytask> implements EmployeeDailyTaskDao {
	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public List<Employeedailytask> getEmployeeDailytaskByEmployeeIdandCurrentDate(
			long empId, Date date) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeedailytask.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  criteria.add(Restrictions.eq("date",date));
		  List<Employeedailytask> employeedailytasks =criteria.list();
		  return employeedailytasks;
	}
	@Override
	public List<Employeedailytask> getEmployeeDailyTaskByUserid(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeedailytask.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  List<Employeedailytask> employeedailytasks =criteria.list();
		  return employeedailytasks;
	}
	@Override
	public List<Employeedailytask> getEmployeeTaskByEmployeeId(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeedailytask.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  List<Employeedailytask> employeedailytasks =criteria.list();
		  return employeedailytasks;
	}
	@Override
	public List<Employeedailytask> getEmployeeDailyTaskByUseridandHasRead(
			long empId) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeedailytask.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("hasRead",true));
		  List<Employeedailytask> employeedailytasks =criteria.list();
		  return employeedailytasks;
	}

}
