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

import com.nextech.hrms.Dto.EmployeeDailyTaskDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Employeedailytask;

public class EmployeeDailyTaskFactory {
	public static Employeedailytask setEmployeeDailyTask(EmployeeDailyTaskDto employeeDailyTaskDto)throws Exception{
		Employeedailytask employeedailytask = new Employeedailytask();
		employeedailytask.setId(employeeDailyTaskDto.getId());
		employeedailytask.setEmployee(employeeDailyTaskDto.getEmployee());
		employeedailytask.setDate(employeeDailyTaskDto.getDate());
		employeedailytask.setTaskName(employeeDailyTaskDto.getTaskName());
		employeedailytask.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
		employeedailytask.setStarttime(employeeDailyTaskDto.getStarttime());
		employeedailytask.setEndtime(employeeDailyTaskDto.getEndtime());
		employeedailytask.setTakenTime(employeeDailyTaskDto.getTakenTime());
		employeedailytask.setStatus(employeeDailyTaskDto.getStatus());
		employeedailytask.setCreatedDate(employeeDailyTaskDto.getCreatedDate());
		employeedailytask.setUpdatedDate(employeeDailyTaskDto.getUpdatedDate());
	    employeedailytask.setDescription(employeeDailyTaskDto.getDescription());
		employeedailytask.setIsActive(true);
		return employeedailytask;
	}
	public static EmployeeDailyTaskDto setEmployeeDailyTaskList(Employeedailytask employeedailytask)throws Exception{
		EmployeeDailyTaskDto employeeDailyTaskDto = new EmployeeDailyTaskDto();
		employeeDailyTaskDto.setId(employeedailytask.getId());
		employeeDailyTaskDto.setEmployee(employeedailytask.getEmployee());
		employeeDailyTaskDto.setDate(employeedailytask.getDate());
		employeeDailyTaskDto.setTaskName(employeedailytask.getTaskName());
		employeeDailyTaskDto.setEstimationTime(employeedailytask.getEstimationTime());
		employeeDailyTaskDto.setStarttime(employeedailytask.getStarttime());
		employeeDailyTaskDto.setEndtime(employeedailytask.getEndtime());
		employeeDailyTaskDto.setTakenTime(employeedailytask.getTakenTime());
		employeeDailyTaskDto.setStatus(employeedailytask.getStatus());
		employeeDailyTaskDto.setCreatedDate(employeedailytask.getCreatedDate());
		employeeDailyTaskDto.setUpdatedDate(employeedailytask.getUpdatedDate());
		employeeDailyTaskDto.setDescription(employeedailytask.getDescription());
		employeeDailyTaskDto.setIsActive(true);
		return employeeDailyTaskDto;
		
	}
	public  static Employeedailytask setEmployeeDailyTaskUpdate(EmployeeDailyTaskDto employeeDailyTaskDto)throws Exception {
		Employeedailytask employeedailytask = new Employeedailytask();
		employeedailytask.setId(employeeDailyTaskDto.getId());
		employeedailytask.setEmployee(employeeDailyTaskDto.getEmployee());
		employeedailytask.setDate(employeeDailyTaskDto.getDate());
		employeedailytask.setTaskName(employeeDailyTaskDto.getTaskName());
		employeedailytask.setEstimationTime(employeeDailyTaskDto.getEstimationTime());
		employeedailytask.setStarttime(employeeDailyTaskDto.getStarttime());
		employeedailytask.setEndtime(employeeDailyTaskDto.getEndtime());
		employeedailytask.setTakenTime(employeeDailyTaskDto.getTakenTime());
		employeedailytask.setStatus(employeeDailyTaskDto.getStatus());
		employeedailytask.setCreatedDate(employeeDailyTaskDto.getCreatedDate());
		employeedailytask.setUpdatedDate(employeeDailyTaskDto.getUpdatedDate());
		employeedailytask.setDescription(employeeDailyTaskDto.getDescription());
		employeedailytask.setIsActive(true);
		return employeedailytask;
	}
	public static List<EmployeeDailyTaskDto> setEmployeeDailyTaskExcel(MultipartFile employeeDailyTaskExcelFile)throws Exception{
		List<EmployeeDailyTaskDto> employeeDailyTaskDtos = new ArrayList<>();
		 Workbook workbook = new XSSFWorkbook(employeeDailyTaskExcelFile.getInputStream());
	        Sheet worksheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = worksheet.iterator();
	        while (iterator.hasNext()) {
	        	EmployeeDailyTaskDto employeeDailyTaskDto = new EmployeeDailyTaskDto();
	        	Row row = iterator.next();
	        	if(row.getRowNum()!=0){
	        	Employee employee =new Employee();
	        	employee.setId((int)(row.getCell(1).getNumericCellValue()));
	        	employeeDailyTaskDto.setEmployee(employee);
	        	employeeDailyTaskDto.setDate(new Date(row.getCell(2).getDateCellValue().getTime()));
	        	employeeDailyTaskDto.setTaskName(row.getCell(3).getStringCellValue());
	        	employeeDailyTaskDto.setEstimationTime(new Time(row.getCell(4).getDateCellValue().getTime()));
	        	employeeDailyTaskDto.setStarttime(new Time(row.getCell(5).getDateCellValue().getTime()));
	        	employeeDailyTaskDto.setEndtime(new Time(row.getCell(6).getDateCellValue().getTime()));
	        	employeeDailyTaskDtos.add(employeeDailyTaskDto);
	        	}
	        }
		
		return employeeDailyTaskDtos;
		
	}



}
