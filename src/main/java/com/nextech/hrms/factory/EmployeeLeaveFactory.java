package com.nextech.hrms.factory;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeeleave;

public class EmployeeLeaveFactory {
	public static Employeeleave setEmployeeleave(EmployeeLeaveDto employeeLeaveDto)throws Exception{
		Employeeleave employeeleave = new Employeeleave();
		employeeleave.setId(employeeLeaveDto.getId());
		employeeleave.setEmployee(employeeLeaveDto.getEmployee());
		employeeleave.setSubject(employeeLeaveDto.getSubject());
		employeeleave.setFromDate(employeeLeaveDto.getFromDate());
		employeeleave.setToDate(employeeLeaveDto.getToDate());
		employeeleave.setCreatedDate(employeeLeaveDto.getCreatedDate());
		employeeleave.setUpdatedDate(employeeLeaveDto.getUpdatedDate());
		employeeleave.setIsActive(true);
		return employeeleave;
		
	}
	public static  EmployeeLeaveDto setEmployeeLeaveList(Employeeleave employeeleave)throws Exception{
		EmployeeLeaveDto employeeLeaveDto = new EmployeeLeaveDto();
		employeeLeaveDto.setId(employeeleave.getId());
		employeeLeaveDto.setEmployee(employeeleave.getEmployee());
		employeeLeaveDto.setSubject(employeeleave.getSubject());
		employeeLeaveDto.setFromDate(employeeleave.getFromDate());
		employeeLeaveDto.setToDate(employeeleave.getToDate());
		employeeLeaveDto.setCreatedDate(employeeleave.getCreatedDate());
		employeeLeaveDto.setUpdatedDate(employeeleave.getUpdatedDate());
		employeeLeaveDto.setIsActive(true);
		return employeeLeaveDto;
		
	}
	public static Employeeleave setEmployeeLeaveUpdate(EmployeeLeaveDto employeeLeaveDto)throws Exception{
		Employeeleave employeeleave = new Employeeleave();
		employeeleave.setId(employeeLeaveDto.getId());
		employeeleave.setEmployee(employeeLeaveDto.getEmployee());
		employeeleave.setSubject(employeeLeaveDto.getSubject());
		employeeleave.setFromDate(employeeLeaveDto.getFromDate());
		employeeleave.setToDate(employeeLeaveDto.getToDate());
		employeeleave.setCreatedDate(employeeLeaveDto.getCreatedDate());
		employeeleave.setUpdatedDate(employeeLeaveDto.getUpdatedDate());
		employeeleave.setIsActive(true);
		return employeeleave;
	}
	
	public static List<EmployeeLeaveDto> setEmployeeLeaveExcel(MultipartFile employeeLeaveExcelFile)throws Exception{
		List<EmployeeLeaveDto> employeeLeaveDtos = new ArrayList<>();
		Workbook workbook = new XSSFWorkbook(employeeLeaveExcelFile.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = worksheet.iterator();
        while (iterator.hasNext()) {
        	EmployeeLeaveDto employeeLeaveDto = new EmployeeLeaveDto();
        	Row row = iterator.next();
        	if(row.getRowNum()!=0){
        	Employee employee =new Employee();
        	employee.setId((int)(row.getCell(1).getNumericCellValue()));
        	employeeLeaveDto.setEmployee(employee);
        	employeeLeaveDto.setSubject(row.getCell(2).getStringCellValue());
        	employeeLeaveDto.setFromDate(new Date(row.getCell(3).getDateCellValue().getTime()));
        	employeeLeaveDto.setToDate(new Date(row.getCell(4).getDateCellValue().getTime()));
        	employeeLeaveDto.setIsActive(true);
        	employeeLeaveDtos.add(employeeLeaveDto);
        	}
        }
		return employeeLeaveDtos;
	}


}
