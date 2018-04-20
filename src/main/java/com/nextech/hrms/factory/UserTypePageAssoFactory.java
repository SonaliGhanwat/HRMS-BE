package com.nextech.hrms.factory;

import javax.servlet.http.HttpServletRequest;

import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.model.Usertypepageassociation;
import com.nextech.hrms.dto.PageDTO;
import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.dto.UserTypePageAssoDTO;

public class UserTypePageAssoFactory {

	public static  Usertypepageassociation setUserTypePageAss(UserTypePageAssoDTO userTypePageAssoDTO){
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setId(userTypePageAssoDTO.getId());
		Usertype usertype = new Usertype();
		usertype.setId(userTypePageAssoDTO.getUsertypeId().getId());
		usertypepageassociation.setUsertype(usertype);
		usertypepageassociation.setActive(true);
		return usertypepageassociation;
	}
	public static  Usertypepageassociation setUserTypePageAssUpdate(UserTypePageAssoDTO userTypePageAssoDTO, HttpServletRequest request){
		Usertypepageassociation usertypepageassociation = new Usertypepageassociation();
		usertypepageassociation.setId(userTypePageAssoDTO.getId());
		Usertype usertype = new Usertype();
		usertype.setId(userTypePageAssoDTO.getUsertypeId().getId());
		usertypepageassociation.setActive(true);
		usertypepageassociation.setUpdatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		return usertypepageassociation;
	}
	public static UserTypePageAssoDTO setUserTypePageDTO(Usertypepageassociation usertypepageassociation){
		UserTypePageAssoDTO userTypePageAssoDTO = new UserTypePageAssoDTO();
		userTypePageAssoDTO.setId(usertypepageassociation.getId());
		PageDTO pageDTO = new PageDTO();
		pageDTO.setId(usertypepageassociation.getPage().getId());
		pageDTO.setPageName(usertypepageassociation.getPage().getPageName());
		pageDTO.setMenu(usertypepageassociation.getPage().getMenu());
		pageDTO.setSubmenu(usertypepageassociation.getPage().getSubmenu());
		pageDTO.setUrl(usertypepageassociation.getPage().getUrl());
		userTypePageAssoDTO.setPage(pageDTO);
		UserTypeDto userTypeDTO = new UserTypeDto();
		userTypeDTO.setId(usertypepageassociation.getUsertype().getId());
		userTypeDTO.setUsertypeName(usertypepageassociation.getUsertype().getUsertypeName());
		userTypePageAssoDTO.setUsertypeId(userTypeDTO);
		userTypePageAssoDTO.setIsActive(true);
		return userTypePageAssoDTO;
	}
	
}
