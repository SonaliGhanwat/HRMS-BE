package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Project;

public interface ProjectDao extends SuperDao<Project>{

	public List<Project> getProjectByCreateBY(long empId)  throws Exception;
}
