package com.nextech.hrms.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.NotificationDao;
import com.nextech.hrms.model.Notification;

@Repository
public class NotificationDaoImpl extends SuperDaoImpl<Notification> implements
		NotificationDao {

	@Override
	public Notification getNotificationByCode(String code) throws Exception {

		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Notification.class);
		criteria.add(Restrictions.eq("code", code));
		Notification notification = criteria.list().size() > 0 ? (Notification) criteria
				.list().get(0) : null;
		return notification;
	}
}
