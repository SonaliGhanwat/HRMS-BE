package com.nextech.hrms.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.nextech.hrms.dao.HolidayDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Holiday;

@Repository
@Transactional
public class HolidayDaoImpl extends SuperDaoImpl<Holiday> implements HolidayDao{
	
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public Holiday getHolidayByName(String Name) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Holiday.class);
		  criteria.add(Restrictions.eq("holidayName", Name));
		  Holiday holiday = criteria.list().size() > 0 ? (Holiday) criteria.list().get(0) : null;
		  return holiday;
	}
	@Override
	public List<Holiday> getHolidayList(Date date) throws Exception {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Holiday.class);
		  criteria.add(Restrictions.eq("holidayDate",date));
		  @SuppressWarnings("unchecked")
		List<Holiday> holidays =criteria.list();
		  return holidays;
	}
	@Override
	public Holiday getHolidayBYDate(Date date) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Holiday.class);
		  criteria.add(Restrictions.eq("holidayDate",date));
		  Holiday holiday = criteria.list().size() > 0 ? (Holiday) criteria.list().get(0) : null;
		  return holiday;
	}
	@Override
	public List<Holiday> getHolidayListByYearandMonth(Date date)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Holiday.class);
		 Holiday holiday = criteria.list().size() > 0 ? (Holiday) criteria.list().get(0) : null;
		 SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		  int year = Integer.valueOf(yearFormat.format(date));

		  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		  int month = Integer.valueOf(monthFormat .format(date));
		  Query query = session.createQuery("from Holiday where year(holiday_date)=:year and month(holiday_date)=:month");
		 query.setParameter("year", year);
		 query.setParameter("month", month);
		 List<Holiday> holidays = query.list();
		  return holidays;
	}
	

}
