package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.model.Page;
import com.nextech.hrms.model.Usertypepageassociation;
import com.nextech.hrms.dto.PageDTO;

public interface PageService extends CRUDService<Page>{
	
	public Page getPageByUrl(String url) throws Exception;
	
	public List<PageDTO>  getPageDTOList(List<PageDTO> pageDTOs) throws Exception;
	
	public PageDTO getPageDTOById(long id) throws Exception;
	
	public PageDTO deletePageById(long id) throws Exception;
	
	public List<Page> getPageByIdList(long id) throws Exception;
	
}
