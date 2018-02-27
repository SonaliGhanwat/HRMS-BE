package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.NotificationUserassociationDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Notificationuserassociation;

@Repository

public class NotificationUserassociationDaoImpl extends
		SuperDaoImpl<Notificationuserassociation> implements
		NotificationUserassociationDao {


	@Override
	public List<Notificationuserassociation> getNotificationuserassociationByUserId(
			long employeeId) throws Exception {
		
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("employee",employeeId));
		  List<Notificationuserassociation> notificationuserassociations =criteria.list();
		  return notificationuserassociations;
	}

	@Override
	public List<Notificationuserassociation> getNotificationuserassociationBynotificationId(
			long notificationId) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Employeeattendance.class);
		  criteria.add(Restrictions.eq("notification",notificationId));
		  List<Notificationuserassociation> notificationuserassociations =criteria.list();
		  return notificationuserassociations;
	}

}
