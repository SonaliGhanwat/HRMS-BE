package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Page;

public interface PageDao extends SuperDao<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
	
	public List<Page> getPageByIdList(long id) throws Exception;
}
