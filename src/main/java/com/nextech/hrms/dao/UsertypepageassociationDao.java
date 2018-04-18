package com.nextech.hrms.dao;

import java.util.List;

import com.nextech.hrms.model.Usertypepageassociation;

public interface UsertypepageassociationDao extends SuperDao<Usertypepageassociation>{
	public List<Usertypepageassociation> getPagesByUsertype(long usertypeId);
	
	public boolean checkPageAccess(long usertypeId,long pageId);
	
	public List<Usertypepageassociation> getUserTypeDtoList(long id) throws Exception;
	
	public List<Usertypepageassociation> getUserTypePageAssoByPageIduserTypeId(long pageId,long userTypeId) throws Exception; 
}
