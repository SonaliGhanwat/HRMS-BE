package com.nextech.hrms.daoImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nextech.hrms.dao.ProjectDao;
import com.nextech.hrms.model.Project;

@Repository
@Transactional
public class ProjectDaoImpl extends SuperDaoImpl<Project> implements ProjectDao{

}
