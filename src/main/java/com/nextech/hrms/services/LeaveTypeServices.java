package com.nextech.hrms.services;

import java.util.List;

import com.nextech.hrms.dto.LeaveTypeDto;
import com.nextech.hrms.model.Leavetype;

public interface LeaveTypeServices extends CRUDService<Leavetype> {

	public LeaveTypeDto getLeaveTypeDto(long id) throws Exception;
	
	 public List<LeaveTypeDto> getLeaveTypeList(List<LeaveTypeDto> leaveTypeDtos)throws Exception;
	 
	 public LeaveTypeDto getLeaveTypeDtoByid(long id)throws Exception;

}
