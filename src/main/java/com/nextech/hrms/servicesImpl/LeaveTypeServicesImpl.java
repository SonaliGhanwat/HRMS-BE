package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dto.LeaveTypeDto;
import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.dao.LeaveTypeDao;
import com.nextech.hrms.factory.LeaveTypeFactory;
import com.nextech.hrms.factory.UserTypeFactory;
import com.nextech.hrms.model.Leavetype;
import com.nextech.hrms.model.Usertype;
import com.nextech.hrms.services.LeaveTypeServices;

@Service
public class LeaveTypeServicesImpl extends CRUDServiceImpl<Leavetype>implements LeaveTypeServices{

	@Autowired
	LeaveTypeDao leaveTypeDao;
	
	@Override
	public LeaveTypeDto getLeaveTypeDto(long id) throws Exception {
		Leavetype  leavetype =  leaveTypeDao.getById(Leavetype.class, id);
		LeaveTypeDto leaveTypeDto = LeaveTypeFactory.setLeaveTypeList(leavetype);
		return leaveTypeDto;
	}

	@Override
	public List<LeaveTypeDto> getLeaveTypeList(List<LeaveTypeDto> leaveTypeDtos)
			throws Exception {
		leaveTypeDtos = new ArrayList<LeaveTypeDto>();
		List<Leavetype> leavetypes = null;
		leavetypes = leaveTypeDao.getList(Leavetype.class);
		for (Leavetype leavetype : leavetypes) {
			LeaveTypeDto leaveTypeDto = LeaveTypeFactory.setLeaveTypeList(leavetype);
			leaveTypeDtos.add(leaveTypeDto);
		}
		return leaveTypeDtos;
	}

	@Override
	public LeaveTypeDto getLeaveTypeDtoByid(long id) throws Exception {
		Leavetype leavetype =  leaveTypeDao.getById(Leavetype.class, id);
		LeaveTypeDto leaveTypeDto = LeaveTypeFactory.setLeaveTypeList(leavetype);
		leavetype.setIsActive(false);
		leaveTypeDao.update(leavetype);
		return leaveTypeDto;
	}
	
}
