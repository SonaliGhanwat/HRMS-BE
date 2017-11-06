package com.nextech.hrms.factory;

import com.nextech.hrms.Dto.LeaveTypeDto;
import com.nextech.hrms.model.Leavetype;

public class LeaveTypeFactory {
	public static Leavetype setLeaveType(LeaveTypeDto leaveTypeDto)throws Exception{
		Leavetype leavetype = new Leavetype();
		
		leavetype.setId(leaveTypeDto.getId());
		leavetype.setName(leaveTypeDto.getName());
		leavetype.setCreatedDate(leaveTypeDto.getCreatedDate());
		leavetype.setUpdatedDate(leaveTypeDto.getUpdatedDate());
		leavetype.setIsActive(true);
		return leavetype;
	}
	public static LeaveTypeDto setLeaveTypeList(Leavetype leavetype)throws Exception{
		LeaveTypeDto leaveTypeDto = new LeaveTypeDto();
		leaveTypeDto.setId(leavetype.getId());
		leaveTypeDto.setName(leavetype.getName());
		leaveTypeDto.setCreatedDate(leavetype.getCreatedDate());
		leaveTypeDto.setUpdatedDate(leavetype.getUpdatedDate());
		leaveTypeDto.setIsActive(true);
		return leaveTypeDto;
		
	}
	public static Leavetype setLeaveTypeUpdate(LeaveTypeDto leaveTypeDto)throws Exception{
        Leavetype leavetype = new Leavetype();
		leavetype.setId(leaveTypeDto.getId());
		leavetype.setName(leaveTypeDto.getName());
		leavetype.setCreatedDate(leaveTypeDto.getCreatedDate());
		leavetype.setUpdatedDate(leaveTypeDto.getUpdatedDate());
		leavetype.setIsActive(true);
		return leavetype;
	}
	
}
