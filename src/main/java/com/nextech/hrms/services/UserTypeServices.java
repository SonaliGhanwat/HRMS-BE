package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.model.Usertype;

public interface UserTypeServices extends CRUDService<Usertype> {
	
	
	public Usertype getUserTypeByIdandName(String name)throws Exception;
	
    public UserTypeDto getUserTypeDto(long id) throws Exception;

    public List<UserTypeDto> getUserTypeList(List<UserTypeDto> userTypeDtos)throws Exception;
    
    public UserTypeDto getUserTypeDtoByid(long id)throws Exception;
    
    public void addEmployeeExcel(List<UserTypeDto> userTypeDtos) throws Exception;


	
	
}
