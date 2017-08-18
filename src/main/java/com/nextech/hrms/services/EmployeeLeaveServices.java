package com.nextech.hrms.services;
import java.util.Date;
import java.util.List;

import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeeleave;

public interface EmployeeLeaveServices extends CRUDService<Employeeleave>{
	
	
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception;
	
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId) throws Exception;
	
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(long empId,Date date)throws Exception;
	
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date) throws Exception;	
	
	public EmployeeLeaveDto getEmployeeLeaveDto(long id) throws Exception;
	
	public List<EmployeeLeaveDto> getEmployeeLeaveDtoList(List<EmployeeLeaveDto> employeeLeaveDtos)throws Exception;

	public EmployeeLeaveDto getEmployeeLeaveDtoByid(long id)throws Exception;
	
	public void addEmployeeLeaveExcel(List<EmployeeLeaveDto> employeeLeaveDtos)throws Exception;

	
}
