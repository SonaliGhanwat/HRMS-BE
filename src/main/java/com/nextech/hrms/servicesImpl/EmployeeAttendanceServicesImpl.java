package com.nextech.hrms.servicesImpl;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.controller.EmployeeAttendanceController;
import com.nextech.hrms.dao.EmployeeAttendanceDao;
import com.nextech.hrms.factory.EmployeeAttendanceFactory;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.services.EmployeeAttendanceServices;
@Service
@Qualifier("employeeAttendanceServicesImpl")
public class EmployeeAttendanceServicesImpl extends CRUDServiceImpl<Employeeattendance> implements EmployeeAttendanceServices {

	@Autowired
	EmployeeAttendanceDao employeeAttendanceDao;
	
	@Override
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception {
		return employeeAttendanceDao.getEmpolyeeAttendanceByIdandDate(empId, date);
	}

	@Override
	public List<Employeeattendance>  calculateEmployeeAttendanceByEmployeeIdandDate(
			long empId, Date date) throws Exception {
		return employeeAttendanceDao.calculateEmployeeAttendanceByEmployeeIdandDate(empId, date);
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date)
			throws Exception {
		return employeeAttendanceDao.getEmployeeattendanceByCurrentDate(date);
	}

	@Override
	public EmployeeAttendanceDto getEmployeeAttendanceDto(long id)
			throws Exception {
		Employeeattendance  employeeattendance =  employeeAttendanceDao.getById(Employeeattendance.class, id);
		EmployeeAttendanceDto employeeAttendanceDto = EmployeeAttendanceFactory.setEmployeeAttendanceList(employeeattendance);
		return employeeAttendanceDto;
	}

	@Override
	public List<EmployeeAttendanceDto> getEmployeeAttendanceList(List<EmployeeAttendanceDto> employeeAttendanceDtos)
			throws Exception {
		employeeAttendanceDtos = new ArrayList<EmployeeAttendanceDto>();
		List<Employeeattendance> employeeattendanceList = null;
		employeeattendanceList = employeeAttendanceDao.getList(Employeeattendance.class);
		for (Employeeattendance employeeattendance : employeeattendanceList) {
			EmployeeAttendanceDto employeeAttendanceDto = EmployeeAttendanceFactory.setEmployeeAttendanceList(employeeattendance);
			employeeAttendanceDtos.add(employeeAttendanceDto);
		}
		return employeeAttendanceDtos;
	}

	@Override
	public EmployeeAttendanceDto getEmployeeAttendanceDtoByid(long id) throws Exception {
		Employeeattendance employeeattendance =  employeeAttendanceDao.getById(Employeeattendance.class, id);
		EmployeeAttendanceDto employeeAttendanceDto = EmployeeAttendanceFactory.setEmployeeAttendanceList(employeeattendance);
		employeeattendance.setIsActive(false);
		employeeAttendanceDao.update(employeeattendance);
		return employeeAttendanceDto;
		
	}

	@Override
	public void addEmployeeAttendanceExcel(List<EmployeeAttendanceDto> employeeAttendanceDtos)
			throws Exception {
		EmployeeAttendanceController employeeAttendanceController = new EmployeeAttendanceController();
		for(EmployeeAttendanceDto employeeAttendanceDto :employeeAttendanceDtos){
			employeeAttendanceDto.setEmployee(employeeAttendanceDto.getEmployee());
			employeeAttendanceDto.setIntime(employeeAttendanceDto.getIntime());
			employeeAttendanceDto.setOuttime(employeeAttendanceDto.getOuttime());
			//employeeAttendanceDto.setTotaltime(employeeAttendanceDto.getTotaltime());
			employeeAttendanceDto.setTotaltime(employeeAttendanceController.calculateTotalTime(employeeAttendanceDto));
			employeeAttendanceDto.setDate(employeeAttendanceDto.getDate());
			employeeAttendanceDto.setStatus(employeeAttendanceController.getEmployeeAttendanceStatus(employeeAttendanceDto));			
			Employeeattendance employeeattendance1 = employeeAttendanceDao.getEmpolyeeAttendanceByIdandDate(employeeAttendanceDto.getEmployee().getId(), employeeAttendanceDto.getDate());
			if(employeeattendance1==null){
				employeeAttendanceDao.add(EmployeeAttendanceFactory.setEmployeeAttendance(employeeAttendanceDto));
		}
		
	}
	
	}

	@Override
	public List<Employeeattendance> getEmployeeattendanceByUserid(long empId)
			throws Exception {
		return employeeAttendanceDao.getEmployeeattendanceByUserid(empId);
	}
	
}
