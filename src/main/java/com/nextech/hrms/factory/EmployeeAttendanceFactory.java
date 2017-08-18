package com.nextech.hrms.factory;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.EmployeeAttendanceDto;
import com.nextech.hrms.Dto.EmployeeDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeattendance;
import com.nextech.hrms.model.Usertype;

public class EmployeeAttendanceFactory {
	
	public static Employeeattendance setEmployeeAttendance(EmployeeAttendanceDto employeeAttendanceDto)throws Exception{
		Employeeattendance employeeattendance = new Employeeattendance();
		employeeattendance.setId(employeeAttendanceDto.getId());
		employeeattendance.setEmployee(employeeAttendanceDto.getEmployee());
		employeeattendance.setIntime(employeeAttendanceDto.getIntime());
		employeeattendance.setOuttime(employeeAttendanceDto.getOuttime());
		employeeattendance.setTotaltime(employeeAttendanceDto.getTotaltime());
		employeeattendance.setDate(employeeAttendanceDto.getDate());
		employeeattendance.setStatus(employeeAttendanceDto.getStatus());
		employeeattendance.setCreatedDate(employeeAttendanceDto.getCreatedDate());
		employeeattendance.setUpdatedDate(employeeAttendanceDto.getUpdatedDate());
		employeeattendance.setIsActive(true);
		return employeeattendance;
	}
	
	public  static Employeeattendance setEmployeeAttendanceUpdate(EmployeeAttendanceDto employeeAttendanceDto)throws Exception {
		Employeeattendance employeeattendance = new Employeeattendance();
		employeeattendance.setId(employeeAttendanceDto.getId());
		employeeattendance.setEmployee(employeeAttendanceDto.getEmployee());
		employeeattendance.setIntime(employeeAttendanceDto.getIntime());
		employeeattendance.setOuttime(employeeAttendanceDto.getOuttime());
		employeeattendance.setTotaltime(employeeAttendanceDto.getTotaltime());
		employeeattendance.setDate(employeeAttendanceDto.getDate());
		employeeattendance.setStatus(employeeAttendanceDto.getStatus());
		employeeattendance.setCreatedDate(employeeAttendanceDto.getCreatedDate());
		employeeattendance.setUpdatedDate(employeeAttendanceDto.getUpdatedDate());
		employeeattendance.setIsActive(true);
		return employeeattendance;
	}

	
	public static EmployeeAttendanceDto setEmployeeAttendanceList(Employeeattendance employeeattendance)throws Exception{
		EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
		employeeAttendanceDto.setId(employeeattendance.getId());
		employeeAttendanceDto.setEmployee(employeeattendance.getEmployee());
		employeeAttendanceDto.setIntime(employeeattendance.getIntime());
		employeeAttendanceDto.setOuttime(employeeattendance.getOuttime());
		employeeAttendanceDto.setTotaltime(employeeattendance.getTotaltime());
		employeeAttendanceDto.setDate(employeeattendance.getDate());
		employeeAttendanceDto.setStatus(employeeattendance.getStatus());
		employeeAttendanceDto.setCreatedDate(employeeattendance.getCreatedDate());
		employeeAttendanceDto.setUpdatedDate(employeeattendance.getUpdatedDate());
		employeeAttendanceDto.setIsActive(true);
		return employeeAttendanceDto;
	}

	public static List<EmployeeAttendanceDto> setEmployeeAttendanceExcel(MultipartFile employeeAttendanceExcelFile)throws Exception{
		List<EmployeeAttendanceDto> employeeAttendanceDtos = new ArrayList<>();
		
        Workbook workbook = new XSSFWorkbook(employeeAttendanceExcelFile.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = worksheet.iterator();
        while (iterator.hasNext()) {
        	EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();
        	Row row = iterator.next();
        	if(row.getRowNum()!=0){
        	Employee employee =new Employee();
        	employee.setId((int)(row.getCell(1).getNumericCellValue()));
        	employeeAttendanceDto.setEmployee(employee);
        	employeeAttendanceDto.setIntime(new Time(row.getCell(2).getDateCellValue().getTime()));
        	employeeAttendanceDto.setOuttime(new Time(row.getCell(3).getDateCellValue().getTime()));
        	//employeeAttendanceDto.setTotaltime((long)row.getCell(4).getNumericCellValue());
        	employeeAttendanceDto.setDate(new Date(row.getCell(4).getDateCellValue().getTime()));
        	//employeeAttendanceDto.setStatus(row.getCell(6).getStringCellValue());
    		employeeAttendanceDtos.add(employeeAttendanceDto);
        	}
        }
		return employeeAttendanceDtos;
		
	}
}
