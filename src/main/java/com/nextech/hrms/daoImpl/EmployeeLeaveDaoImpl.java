package com.nextech.hrms.daoImpl;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Holiday;

@Repository
public class EmployeeLeaveDaoImpl extends SuperDaoImpl<Employeeleave> implements EmployeeLeaveDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	@Override
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("fromDate",date));
		  Employeeleave employeeleave = criteria.list().size() > 0 ? (Employeeleave) criteria.list().get(0) : null;
		  return employeeleave;
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
		  int year = Integer.valueOf(yearFormat.format(employeeleave.getFromDate()));
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(fromDate)=:year");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 List<EmployeeLeaveDTO> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDTO>();
		 List<Employeeleave> employeeleaves = query.list();
		 int totalCount=0;
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getFromDate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getToDate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(fromDate)=:day and Day(toDate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;
			
		}
		    EmployeeLeaveDTO employeeLeaveDTO= new EmployeeLeaveDTO();
			employeeLeaveDTO.setTotalCount(totalCount);
			employeeLeaveDTOs.add(employeeLeaveDTO);
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
		  Query query = session.createQuery("FROM Employeeleave where employeeid=:employeeid and year(fromDate)=:year and month(fromDate)=:month");
		 query.setParameter("employeeid", empId);
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 //query.setParameter("year1", year1);
		 int totalCount=0;
		 List<EmployeeLeaveDTO> employeeLeaveDTOs = new ArrayList<EmployeeLeaveDTO>();
		 List<Employeeleave> employeeleaves = query.list();
		 for (Employeeleave employeeleave1 : employeeleaves) {
			 SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			  int day = Integer.valueOf(dayFormat.format(employeeleave1.getFromDate()));
			  SimpleDateFormat dayFormat1 = new SimpleDateFormat("dd");
			  int day1 = Integer.valueOf(dayFormat1.format(employeeleave1.getToDate()));
			 Query query1 = session.createQuery("FROM Employeeleave where employeeid=:employeeid and Day(fromDate)=:day and Day(toDate)=:day1");
			 query1.setParameter("employeeid", empId);
			 query1.setParameter("day", day);
			 query1.setParameter("day1", day1);
			 totalCount=totalCount+day1-day;  
		}
		 EmployeeLeaveDTO employeeLeaveDTO= new EmployeeLeaveDTO();
			employeeLeaveDTO.setTotalCount(totalCount);
			employeeLeaveDTOs.add(employeeLeaveDTO);
		 System.out.println("Total Leave Count YEAR :"+totalCount);
			return employeeleaves;
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date)
			throws Exception {
			session = sessionFactory.openSession();
			 Criteria criteria = session.createCriteria(Employeeleave.class);
			  criteria.add(Restrictions.eq("fromDate",date));
			  List<Employeeleave> employeeleaveList =criteria.list();
			  return employeeleaveList;
		}

	@Override
	public List<Employeeleave> getEmployeeLeaveListByDate(Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("fromDate",date));
		  @SuppressWarnings("unchecked")
		List<Employeeleave> employeeleaves =criteria.list();
		  return employeeleaves;
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByUserid(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee.id",empId));
		  List<Employeeleave> employeeleaves =criteria.list();
		  return employeeleaves;
	}


	@Override
	public List<Employeeleave> getEmployeeLeaveByLeaveTypeid(long leaveid)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("leavetype.id",leaveid));
		  List<Employeeleave> employeeleaves =criteria.list();
		  return employeeleaves;
	}


	@Override
	public List<Employeeleave> getEmployeeLeaveByStatus(String status)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("status",status));
		  List<Employeeleave> employeeleaves =criteria.list();
		  return employeeleaves;
	}


	@Override
	public List<Employeeleave> getEmployeeLeaveByEmployeeId(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeleave.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  List<Employeeleave> employeeleaves =criteria.list();
		  return employeeleaves;
	}
	
}
