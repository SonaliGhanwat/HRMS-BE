package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.dao.UserTypeDao;
import com.nextech.hrms.factory.EmployeeFactory;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.services.UserTypeServices;
@Service
public class UserTypeServicesImpl extends CRUDServiceImpl<Usertype>implements UserTypeServices {

	@Autowired
	UserTypeDao userTypeDao;
	
	@Override
	public Usertype getUserTypeByIdandName(String name)throws Exception {
		return userTypeDao.getUserTypeByIdandName(name);
	}

	@Override
	public UserTypeDto getUserTypeDto(long id) throws Exception {
		Usertype  usertype =  userTypeDao.getById(Usertype.class, id);
		return UserTypeFactory.getUserTypeDTO(usertype);
	}

	@Override
	public List<UserTypeDto> getUserTypeList(List<UserTypeDto> userTypeDtos)
			throws Exception {
		userTypeDtos = new ArrayList<UserTypeDto>();
		List<Usertype> usertypeList = null;
		usertypeList = userTypeDao.getList(Usertype.class);
		for (Usertype usertype : usertypeList) {
			UserTypeDto userTypeDto = UserTypeFactory.getUserTypeDTO(usertype);
			userTypeDtos.add(userTypeDto);
		}
		return userTypeDtos;
	}

	@Override
	public UserTypeDto getUserTypeDtoByid(long id) throws Exception {
		Usertype usertype =  userTypeDao.getById(Usertype.class, id);
		UserTypeDto userTypeDto = UserTypeFactory.getUserTypeDTO(usertype);
		usertype.setIsActive(false);
		userTypeDao.update(usertype);
		return userTypeDto;
	}

	@Override
	public void addEmployeeExcel(List<UserTypeDto> userTypeDtos)
			throws Exception {
		for(UserTypeDto userTypeDto : userTypeDtos){
			userTypeDto.setUsertypeName(userTypeDto.getUsertypeName());
			userTypeDto.setDescription(userTypeDto.getDescription());
			Usertype usertype1 = userTypeDao.getUserTypeByIdandName(userTypeDto.getUsertypeName());
			if(usertype1==null){
				userTypeDao.add(UserTypeFactory.getUserTypeModel(userTypeDto));
			}
			
		}
		
	}
}
