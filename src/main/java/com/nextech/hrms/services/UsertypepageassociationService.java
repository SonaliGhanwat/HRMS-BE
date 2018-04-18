package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Usertypepageassociation;
import com.nextech.hrms.dto.UserTypePageAssoDTO;

public interface UsertypepageassociationService extends CRUDService<Usertypepageassociation>{
	public List<UserTypePageAssoDTO> getPagesByUsertype(long usertypeId);
	
	public boolean checkPageAccess(long usertypeId,long pageId);
	
	public List<UserTypePageAssoDTO> getUserTypePageAssList() throws Exception;
	
	public UserTypePageAssoDTO getUserTypeDto(long id) throws Exception;
	
	public List<Usertypepageassociation> getUserTypeDtoList(long id) throws Exception;
	
	public UserTypePageAssoDTO  deleteUserTypePage(long id)throws Exception;
	
	public UserTypePageAssoDTO addMultipleUserTypePageAsso(UserTypePageAssoDTO userTypePageAssoDTO,String user) throws Exception;
	
	public List<Usertypepageassociation> getUserTypePageAssoByPageIduserTypeId(long pageId,long userTypeId) throws Exception; 
}
