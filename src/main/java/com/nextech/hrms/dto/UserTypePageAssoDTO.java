package com.nextech.hrms.dto;



import java.util.List;

import com.nextech.hrms.dto.AbstractDTO;

public class UserTypePageAssoDTO extends AbstractDTO{

	private PageDTO page;
	private UserTypeDto usertypeId;
	
	private List<UserTypePageAssoPart> userTypePageAssoParts;
	
	public UserTypePageAssoDTO(){
		
	}
	public UserTypePageAssoDTO(int id){
		this.setId(id);
	}

	public List<UserTypePageAssoPart> getUserTypePageAssoParts() {
		return userTypePageAssoParts;
	}

	public void setUserTypePageAssoParts(
			List<UserTypePageAssoPart> userTypePageAssoParts) {
		this.userTypePageAssoParts = userTypePageAssoParts;
	}

	public PageDTO getPage() {
		return page;
	}

	public void setPage(PageDTO page) {
		this.page = page;
	}
	public UserTypeDto getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(UserTypeDto usertypeId) {
		this.usertypeId = usertypeId;
	}

	
	
}
