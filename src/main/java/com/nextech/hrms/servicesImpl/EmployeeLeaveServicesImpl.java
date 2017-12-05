package com.nextech.hrms.servicesImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.dao.EmployeeLeaveDao;
import com.nextech.hrms.factory.EmployeeDailyTaskFactory;
import com.nextech.hrms.factory.EmployeeLeaveFactory;
import com.nextech.hrms.model.EmployeeLeaveDTO;
import com.nextech.hrms.model.Employeedailytask;
import com.nextech.hrms.model.Employeeleave;
import com.nextech.hrms.services.EmployeeLeaveServices;
@Service
public class EmployeeLeaveServicesImpl extends CRUDServiceImpl<Employeeleave> implements EmployeeLeaveServices {

	@Autowired
	EmployeeLeaveDao employeeLeaveDao;

	@Override
	public Employeeleave getEmpolyeeleaveByIdandDate(long empId,Date date)throws Exception {
		return employeeLeaveDao.getEmpolyeeleaveByIdandDate(empId, date);
	}

	@Override
	public List<EmployeeLeaveDTO> getYearlyEmployeeLeaveByEmployeeId(long empId)
			throws Exception {
		return employeeLeaveDao.getYearlyEmployeeLeaveByEmployeeId(empId);
	}

	@Override
	public List<Employeeleave> getMonthlyEmployeeLeaveByEmployeeId(
			long empId, Date date) throws Exception {
		return employeeLeaveDao.getMonthlyEmployeeLeaveByEmployeeId(empId, date);
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByCurrentDate(Date date)
			throws Exception {
		return employeeLeaveDao.getEmployeeLeaveByCurrentDate(date);
	}

	@Override
	public EmployeeLeaveDto getEmployeeLeaveDto(long id) throws Exception {
		Employeeleave  employeeleave =  employeeLeaveDao.getById(Employeeleave.class, id);
		EmployeeLeaveDto employeeLeaveDto = EmployeeLeaveFactory.setEmployeeLeaveList(employeeleave);
		return employeeLeaveDto;
	}

	@Override
	public List<EmployeeLeaveDto> getEmployeeLeaveDtoList() throws Exception {
		List<EmployeeLeaveDto> employeeLeaveDtos = new ArrayList<EmployeeLeaveDto>();
		List<Employeeleave> employeeDailyTaskDtoList = null;
		employeeDailyTaskDtoList = employeeLeaveDao.getList(Employeeleave.class);
		for (Employeeleave employeeleave : employeeDailyTaskDtoList) {
			EmployeeLeaveDto employeeLeaveDto = EmployeeLeaveFactory.setEmployeeLeaveList(employeeleave);
			employeeLeaveDtos.add(employeeLeaveDto);
		}
		return employeeLeaveDtos;
	}

	@Override
	public EmployeeLeaveDto getEmployeeLeaveDtoByid(long id) throws Exception {
		Employeeleave employeeleave =  employeeLeaveDao.getById(Employeeleave.class, id);
		EmployeeLeaveDto employeeLeaveDto = EmployeeLeaveFactory.setEmployeeLeaveList(employeeleave);
		employeeleave.setIsActive(false);
		employeeLeaveDao.update(employeeleave);
		return employeeLeaveDto;
		
	}

	@Override
	public void addEmployeeLeaveExcel(List<EmployeeLeaveDto> employeeLeaveDtos)
			throws Exception {
		for(EmployeeLeaveDto employeeLeaveDto:employeeLeaveDtos){
			employeeLeaveDto.setEmployee(employeeLeaveDto.getEmployee());
			employeeLeaveDto.setSubject(employeeLeaveDto.getSubject());
			employeeLeaveDto.setFromDate(employeeLeaveDto.getFromDate());
			employeeLeaveDto.setToDate(employeeLeaveDto.getToDate());
			Employeeleave employeeleave = employeeLeaveDao.getEmpolyeeleaveByIdandDate(employeeLeaveDto.getEmployee().getId(),employeeLeaveDto.getFromDate());
			if(employeeleave==null){
				employeeLeaveDao.add(EmployeeLeaveFactory.setEmployeeleave(employeeLeaveDto));
			}

			
		}
		
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveList(Date date) throws Exception {
		// TODO Auto-generated method stub
		return employeeLeaveDao.getEmployeeLeaveListByDate(date);
	}

	@Override
	public List<Employeeleave> getEmployeeLeaveByUserid(long empId)
			throws Exception {
		// TODO Auto-generated method stub
		return employeeLeaveDao.getEmployeeLeaveByUserid(empId);
	}



	

}
