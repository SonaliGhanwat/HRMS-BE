package com.nextech.hrms.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.hrms.model.Page;
import com.nextech.hrms.dto.PageDTO;

public class PageFactory {
	
	public static Page setPage(PageDTO pageDTO,HttpServletRequest request){
		Page page  = new Page();
		page.setId(pageDTO.getId());
		page.setMenu(pageDTO.getMenu());
		page.setDescription(pageDTO.getDescription());
		page.setPageName(pageDTO.getPageName());
		page.setSubmenu(pageDTO.getSubmenu());
		page.setUrl(pageDTO.getUrl());
		page.setIsactive(true);
		page.setCreatedBy(1);
		return page;
	}
	
	public static Page setPageUpdate(PageDTO pageDTO,HttpServletRequest request){
		Page page  = new Page();
		page.setId(pageDTO.getId());
		page.setMenu(pageDTO.getMenu());
		page.setDescription(pageDTO.getDescription());
		page.setPageName(pageDTO.getPageName());
		page.setSubmenu(pageDTO.getSubmenu());
		page.setUrl(pageDTO.getUrl());
		page.setIsactive(true);
		page.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		return page;
	}
	
	public static PageDTO setPageList(Page page){
		PageDTO pageDTO = new PageDTO();
		pageDTO.setDescription(page.getDescription());
		pageDTO.setMenu(page.getMenu());
		pageDTO.setPageName(page.getPageName());
		pageDTO.setSubmenu(page.getSubmenu());
		pageDTO.setUrl(page.getUrl());
		pageDTO.setIsActive(true);
		pageDTO.setCreatedBy(page.getCreatedBy());
		pageDTO.setCreatedDate(page.getCreatedDate());
		pageDTO.setUpdatedBy(page.getUpdatedBy());
		pageDTO.setUpdatedDate(page.getUpdatedDate());
		pageDTO.setId(page.getId());
		return pageDTO;
	}

}
