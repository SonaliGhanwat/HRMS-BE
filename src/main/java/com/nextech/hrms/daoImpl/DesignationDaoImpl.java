package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.DesignationDao;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employeeattendance;

@Repository
@Transactional
public class DesignationDaoImpl extends SuperDaoImpl<Designation> implements DesignationDao{

	@Override
	public List<Designation> getDesignationByUserTypeid(long usertypeid)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Designation.class);
		  criteria.add(Restrictions.eq("usertype.id",usertypeid));
		  List<Designation> designations =criteria.list();
		  return designations;
	}

	
}
