package com.nextech.hrms.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.LeaveTypeDao;
import com.nextech.hrms.model.Leavetype;

@Repository
public class LeaveTypeDaoImpl extends SuperDaoImpl<Leavetype>implements LeaveTypeDao {
	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	@Override
	public LeaveTypeDao getLeaveTypeDto(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
