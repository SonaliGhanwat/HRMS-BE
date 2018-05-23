package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Project;

public interface ProjectService extends CRUDService<Project>{

	public List<Project> getProjectByCreateBY(long empId)  throws Exception;
}
