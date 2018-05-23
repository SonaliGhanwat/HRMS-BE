package com.nextech.hrms.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.ProjectDao;
import com.nextech.hrms.model.Project;
import com.nextech.hrms.model.Regularization;

@Repository
@Transactional
public class ProjectDaoImpl extends SuperDaoImpl<Project> implements ProjectDao{

	@Override
	public List<Project> getProjectByCreateBY(long empId) throws Exception {
		session = sessionFactory.openSession();
		 Criteria criteria = session.createCriteria(Project.class);
		  criteria.add(Restrictions.eq("createdBy", empId));
		  List<Project> projects =criteria.list();
		  return projects;
	}

}
