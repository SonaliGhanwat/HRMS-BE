package com.nextech.hrms.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.ProjectDao;
import com.nextech.hrms.dao.RegularizationDao;
import com.nextech.hrms.dto.ProjectDto;
import com.nextech.hrms.model.Project;
import com.nextech.hrms.services.ProjectService;

@Service
public class ProjectServiceImpl extends CRUDServiceImpl<Project> implements ProjectService{

	@Autowired
	ProjectDao projectDao;
	
	@Override
	public List<Project> getProjectByCreateBY(long empId) throws Exception {
		// TODO Auto-generated method stub
		return projectDao.getProjectByCreateBY(empId);
	}

}
