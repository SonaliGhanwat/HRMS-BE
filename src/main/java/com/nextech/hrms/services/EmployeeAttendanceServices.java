package com.nextech.hrms.services;
import java.util.Date;
import java.util.List;
import com.nextech.hrms.dto.EmployeeAttendanceDto;
import com.nextech.hrms.model.Employeeattendance;


public interface EmployeeAttendanceServices extends CRUDService<Employeeattendance> {
	
	public Employeeattendance getEmpolyeeAttendanceByIdandDate(long empId,Date date)throws Exception;
	
	public List<Employeeattendance> calculateEmployeeAttendanceByEmployeeIdandDate(long empId,Date date)throws Exception;
	
    public List<Employeeattendance> getEmployeeattendanceByCurrentDate(Date date) throws Exception;	
    
    public EmployeeAttendanceDto getEmployeeAttendanceDto(long id) throws Exception;
    
    public List<EmployeeAttendanceDto> getEmployeeAttendanceList(List<EmployeeAttendanceDto> employeeAttendanceDtos)throws Exception;
    
    public EmployeeAttendanceDto getEmployeeAttendanceDtoByid(long id)throws Exception;
    
    public void addEmployeeAttendanceExcel(List<EmployeeAttendanceDto> employeeAttendanceDtos) throws Exception;
    
    public List<Employeeattendance> getEmployeeattendanceByUserid(long empId) throws Exception;
}
