package com.nextech.hrms.daoImpl;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.model.Employeeattendance;

@Repository
@Transactional
public class EmployeeAttendanceDaoImpl extends SuperDaoImpl<Employeeattendance> implements EmployeeAttendanceDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	
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

	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		 Employeeattendance employeeattendance = criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0) : null;
		 SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(date));

		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(date));
		  Query query = session.createQuery("from Employeeattendance where  employeeid=:employeeid and year(date)=:year and month(date)=:month");
		  query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 List<Employeeattendance> employeeattendances = query.list();
		  return employeeattendances;
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("date",date));
		  List<Employeeattendance> employeeattendance =criteria.list();
		  return employeeattendance;
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByUserid(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  List<Employeeattendance> employeeattendance =criteria.list();
		  return employeeattendance;
	}

	@Override
	public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandDate(
			long empId, Date date) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		 Employeeattendance employeeattendance = criteria.list().size() > 0 ? (Employeeattendance) criteria.list().get(0) : null;
		 SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(date));

		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(date));
		  SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		  int day = Integer.valueOf(dayFormat .format(date));
		  Query query = session.createQuery("from Employeeattendance where  employeeid=:employeeid and year(date)=:year and month(date)=:month and day(date)=:day");
		  query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 query.setParameter("day", day);
		 List<Employeeattendance> employeeattendances = query.list();
		  return employeeattendances;
	}

	@Override
	public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandStatus(
			long empId, String status) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  criteria.add(Restrictions.eq("status",status));
		  List<Employeeattendance> employeeattendance =criteria.list();
		  return employeeattendance;
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByUseridandHasRead(
			long empId) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  criteria.add(Restrictions.eq("hasRead",true));
		  List<Employeeattendance> employeeattendance =criteria.list();
		  return employeeattendance;
	}

	@Override
	public List<Employeeattendance> getEmployeeAttendanceByEmployeeIdandStatusandHasRead(
			long empId, String status) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  criteria.add(Restrictions.eq("status",status));
		  criteria.add(Restrictions.eq("hasRead",true));
		  List<Employeeattendance> employeeattendance =criteria.list();
		  return employeeattendance;
	}
}
