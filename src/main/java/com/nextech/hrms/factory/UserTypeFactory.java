package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.UserTypeDto;
import com.nextech.hrms.model.Usertype;

public class UserTypeFactory {
	
	public static Usertype setUserType(UserTypeDto userTypeDto)throws Exception{
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDto.getId());
		usertype.setUsertypeName(userTypeDto.getUsertypeName());
		usertype.setDescription(userTypeDto.getDescription());
		usertype.setCreatedDate(userTypeDto.getCreatedDate());
		usertype.setUpdatedDate(userTypeDto.getUpdatedDate());
		usertype.setIsActive(true);
		return usertype;
		
	}
	public static UserTypeDto setUserTypeList(Usertype usertype)throws Exception{
		UserTypeDto userTypeDto = new UserTypeDto();
		userTypeDto.setId(usertype.getId());
		userTypeDto.setUsertypeName(usertype.getUsertypeName());
		userTypeDto.setDescription(usertype.getDescription());
		userTypeDto.setCreatedDate(usertype.getCreatedDate());
		userTypeDto.setUpdatedDate(usertype.getUpdatedDate());
		userTypeDto.setIsActive(true);
		return userTypeDto;
		
	}
	public static Usertype setUserTypeUpdate(UserTypeDto userTypeDto)throws Exception{
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDto.getId());
		usertype.setUsertypeName(userTypeDto.getUsertypeName());
		usertype.setDescription(userTypeDto.getDescription());
		usertype.setCreatedDate(userTypeDto.getCreatedDate());
		usertype.setUpdatedDate(userTypeDto.getUpdatedDate());
		usertype.setIsActive(true);
		return usertype;
	}

}
