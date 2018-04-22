package com.nextech.hrms.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nextech.hrms.dto.EmployeeDto;
import com.nextech.hrms.dto.UserTypeDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Usertype;

public class EmployeeFactory {

	public static Employee getEmployeeModel(EmployeeDto employeeDto)
			throws Exception {
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setUserid(employeeDto.getUserid());
		employee.setPassword(employeeDto.getPassword());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmailid(employeeDto.getEmailid());
		employee.setDateOfJoining(employeeDto.getDateOfJoining());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setAddress(employeeDto.getAddress());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setSalary(employeeDto.getSalary());
		employee.setUsertype(UserTypeFactory.getUserTypeModel(employeeDto.getUsertype()));
		employee.setEmployeetype(employeeDto.getEmployeetype());
		employee.setDesignation(employeeDto.getDesignation());
		employee.setReportTo(employeeDto.getReportTo());
		employee.setCreatedDate(employeeDto.getCreatedDate());
		employee.setUpdatedDate(employeeDto.getUpdatedDate());
		employee.setIsActive(true);
		return employee;
	}

	public static EmployeeDto getEmployeeDTO(Employee employee)
			throws Exception {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setUserid(employee.getUserid());
		employeeDto.setPassword(employee.getPassword());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setEmailid(employee.getEmailid());
		employeeDto.setDateOfJoining(employee.getDateOfJoining());
		employeeDto.setDateOfBirth(employee.getDateOfBirth());
		employeeDto.setAddress(employee.getAddress());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setSalary(employee.getSalary());
		employeeDto.setUsertype(UserTypeFactory.getUserTypeDTO(employee.getUsertype()));
		employeeDto.setEmployeetype(employee.getEmployeetype());
		employeeDto.setDesignation(employee.getDesignation());
		employeeDto.setReportTo(employee.getReportTo());
		employeeDto.setCreatedDate(employee.getCreatedDate());
		employeeDto.setUpdatedDate(employee.getUpdatedDate());
		employeeDto.setIsActive(true);
		return employeeDto;

	}

	public static EmployeeDto toDTO(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		try {
			employeeDto.setFirstName(employee.getFirstName());
			employeeDto.setLastName(employee.getLastName());
			
			employeeDto.setUsertype(UserTypeFactory.getUserTypeDTO(employee
					.getUsertype()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeeDto;
	}

	public static Employee setEmployeeUpdate(EmployeeDto employeeDto)
			throws Exception {

		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setUserid(employeeDto.getUserid());
		employee.setPassword(employeeDto.getPassword());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setEmailid(employeeDto.getEmailid());
		employee.setDateOfJoining(employeeDto.getDateOfJoining());
		employee.setDateOfBirth(employeeDto.getDateOfBirth());
		employee.setAddress(employeeDto.getAddress());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setSalary(employeeDto.getSalary());
		employee.setUsertype(UserTypeFactory.getUserTypeModel(employeeDto.getUsertype()));
		employee.setEmployeetype(employeeDto.getEmployeetype());
		employee.setDesignation(employeeDto.getDesignation());
		employee.setReportTo(employeeDto.getReportTo());
		employee.setCreatedDate(employeeDto.getCreatedDate());
		employee.setUpdatedDate(employeeDto.getUpdatedDate());
		employee.setIsActive(true);
		return employee;

	}

	public static List<EmployeeDto> setEmployeeExcel(
			MultipartFile employeeExcelFile) throws Exception {
		List<EmployeeDto> employeeDtos = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(employeeExcelFile.getInputStream());
		Sheet worksheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = worksheet.iterator();
		while (iterator.hasNext()) {
			EmployeeDto employeeDto = new EmployeeDto();
			Row row = iterator.next();
			if (row.getRowNum() != 0) {
				employeeDto.setUserid(row.getCell(1).getStringCellValue());
				employeeDto.setPassword(row.getCell(2).getStringCellValue());
				employeeDto.setFirstName(row.getCell(3).getStringCellValue());
				employeeDto.setLastName(row.getCell(4).getStringCellValue());
				employeeDto.setPhoneNumber((long) (row.getCell(5)
						.getNumericCellValue()));
				employeeDto.setEmailid(row.getCell(6).getStringCellValue());
				employeeDto.setDateOfJoining(new Date(row.getCell(7)
						.getDateCellValue().getTime()));
				employeeDto.setDateOfBirth(new Date(row.getCell(8)
						.getDateCellValue().getTime()));
				employeeDto.setAddress(row.getCell(9).getStringCellValue());
				employeeDto.setDepartment(row.getCell(10).getStringCellValue());
				employeeDto.setSalary(row.getCell(11).getStringCellValue());
				UserTypeDto usertype = new UserTypeDto();
				usertype.setId((int) (row.getCell(12).getNumericCellValue()));
				employeeDto.setUsertype(usertype);
				employeeDtos.add(employeeDto);
			}
		}
		return employeeDtos;
	}
}
