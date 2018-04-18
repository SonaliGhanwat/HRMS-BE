package com.nextech.hrms.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.UsertypepageassociationDao;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Usertypepageassociation;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
@Repository

public class UsertypepageassociationDaoImpl extends SuperDaoImpl<Usertypepageassociation> implements UsertypepageassociationDao {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;
	
	@Override
	public List<Usertypepageassociation> getPagesByUsertype(long usertypeId) {
		
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Usertypepageassociation.class);
		  criteria.add(Restrictions.eq("usertype.id",usertypeId));
		  List<Usertypepageassociation> usertypepageassociations =criteria.list();
		  return usertypepageassociations;
		
	}

	
	@Override
	public List<Usertypepageassociation> getUserTypePageAssoByPageIduserTypeId(
			long pageId, long userTypeId) throws Exception {
		
	session = sessionFactory.openSession();
	 Criteria criteria = session.createCriteria(Usertypepageassociation.class);
	  criteria.add(Restrictions.eq("usertype.id",userTypeId));
	  criteria.add(Restrictions.eq("page.id",pageId));
	  List<Usertypepageassociation> usertypepageassociations =criteria.list();
	  return usertypepageassociations;
	}


	@Override
	public boolean checkPageAccess(long usertypeId, long pageId) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Usertypepageassociation> getUserTypeDtoList(long id)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Usertypepageassociation.class);
		  criteria.add(Restrictions.eq("id",id));
		  List<Usertypepageassociation> usertypepageassociations =criteria.list();
		  return usertypepageassociations;
	}
}