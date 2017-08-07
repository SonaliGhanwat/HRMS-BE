package com.nextech.hrms.daoImpl;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;

public class EmployeeLeaveDaoImpl implements EmployeeLeaveDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employeeleave employeeleave) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(employeeleave);
		tx.commit();
		session.close();
		return false;
	}

	@Override
	public Employeeleave getEntityById(long id) throws Exception {

		 session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Employeeleave.class);
			criteria.add(Restrictions.eq("isActive", true));
			criteria.add(Restrictions.eq("id", id));
			Employeeleave employeeleave= criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0): null;
			session.close();
			return employeeleave;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employeeleave> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employeeleave> employeeleaveList = session.createCriteria(Employeeleave.class)
				.list();
		tx.commit();
		session.close();
		return employeeleaveList;
	}
	
	@Override
	public boolean deleteEntity(long id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employeeleave.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
	@Override
	public boolean updateEntity(Employeeleave employeeleave)
			throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(employeeleave);
		tx.commit();
		session.close();
		return false;
	}
	
	@Override
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("leavedate",date));
		  Employeeleave employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  return employeeleave;
	}

	@Override
	public boolean updateEntity(long id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employeeleave.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.update(o);
		tx.commit();
		return  false;
	}

	@Override
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId)
			throws Exception {
		  Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  Employeeleave employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  
		  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(employeeleave.getLeavedate()));
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(leavedate)=:year");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 List<EmployeeLeaveDTO> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDTO>();
		 List<Employeeleave> employeeleaves = query.list();
		 int totalCount=0;
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getLeavedate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getAfterleavejoiningdate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(leavedate)=:day and Day(afterleavejoiningdate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;
			EmployeeLeaveDTO employeeLeaveDTO= new EmployeeLeaveDTO();
			employeeLeaveDTO.setTotalCount(totalCount);
			employeeLeaveDTOs.add(employeeLeaveDTO);
		}
		 System.out.println("Total Leave Count YEAR:"+totalCount);
			return employeeLeaveDTOs;
		
	}

	@Override
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(
			long empId, Date date) throws Exception {
		 Session session = sessionFactory.openSession();
		  session.beginTransaction();
		  
		  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(date));
		  
		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(date));
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(leavedate)=:year and month(leavedate)=:month");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 //query.setParameter("year1", year1);
		 int totalCount=0;
		 List<Employeeleave> employeeleaves = query.list();
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getLeavedate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getAfterleavejoiningdate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(leavedate)=:day and Day(afterleavejoiningdate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;
		}
		 System.out.println("Total Leave Count MONTH :"+totalCount);
			return employeeleaves;
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date)
			throws Exception {
			session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(Employeeleave.class);
			  criteria.add(Restrictions.eq("leavedate",date));
			  List<Employeeleave> employeeleaveList =criteria.list();
			  return employeeleaveList;
		}
	
}
