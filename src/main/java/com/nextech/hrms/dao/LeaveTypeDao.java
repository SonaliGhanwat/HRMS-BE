package com.nextech.hrms.dao;

import com.nextech.hrms.model.Leavetype;


	public interface LeaveTypeDao extends SuperDao<Leavetype> {
		
		  public LeaveTypeDao getLeaveTypeDto(long id) throws Exception;


}
