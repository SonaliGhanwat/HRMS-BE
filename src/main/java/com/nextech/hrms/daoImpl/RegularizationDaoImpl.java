package com.nextech.hrms.daoImpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.RegularizationDao;
import com.nextech.hrms.dto.RegularizationDto;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.model.Regularization;

@Repository
@Transactional
public class RegularizationDaoImpl extends SuperDaoImpl<Regularization> implements RegularizationDao{

	@Override
	public Regularization getRegularizationByUseridandDate(long empId,
			Date date) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Regularization.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("date",date));
		  Regularization regularization = criteria.list().size() > 0 ? (Regularization) criteria.list().get(0) : null;
		  return regularization;
	}

	@Override
	public List<Regularization> getRegularizationByEmployeeId(long empId)
			throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Regularization.class);
		  criteria.add(Restrictions.eq("employee.id", empId));
		  List<Regularization> regularizations =criteria.list();
		  return regularizations;
	}

	@Override
	public List<Regularization> getRegularizationByEmployeeIdandDate(
			long empId, Date date) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Regularization.class);
		 criteria.add(Restrictions.eq("employee.id", empId));
		  criteria.add(Restrictions.eq("date",date));
		  List<Regularization> regularizations =criteria.list();
		  return regularizations;
	}

}
