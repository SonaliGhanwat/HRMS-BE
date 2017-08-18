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

import com.nextech.hrms.Dto.EmployeeLeaveDto;
import com.nextech.hrms.Dto.UserTypeDto;
import com.nextech.hrms.model.Employee;
import com.nextech.hrms.model.Usertype;

public class UserTypeFactory {
	
	public static Usertype setUserType(UserTypeDto userTypeDto)throws Exception{
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDto.getId());
		usertype.setUsertypeName(userTypeDto.getUsertypeName());
		usertype.setDescription(userTypeDto.getDescription());
		usertype.setCreatedDate(userTypeDto.getCreatedDate());
		usertype.setUpdatedDate(userTypeDto.getUpdatedDate());
		usertype.setIsActive(true);
		return usertype;
		
	}
	public static UserTypeDto setUserTypeList(Usertype usertype)throws Exception{
		UserTypeDto userTypeDto = new UserTypeDto();
		userTypeDto.setId(usertype.getId());
		userTypeDto.setUsertypeName(usertype.getUsertypeName());
		userTypeDto.setDescription(usertype.getDescription());
		userTypeDto.setCreatedDate(usertype.getCreatedDate());
		userTypeDto.setUpdatedDate(usertype.getUpdatedDate());
		userTypeDto.setIsActive(true);
		return userTypeDto;
		
	}
	public static Usertype setUserTypeUpdate(UserTypeDto userTypeDto)throws Exception{
		Usertype usertype = new Usertype();
		usertype.setId(userTypeDto.getId());
		usertype.setUsertypeName(userTypeDto.getUsertypeName());
		usertype.setDescription(userTypeDto.getDescription());
		usertype.setCreatedDate(userTypeDto.getCreatedDate());
		usertype.setUpdatedDate(userTypeDto.getUpdatedDate());
		usertype.setIsActive(true);
		return usertype;
	}
	
	public static List<UserTypeDto> setUserTypeExcel(MultipartFile userTypeExcelFile)throws Exception{
		List<UserTypeDto> userTypeDtos = new ArrayList<>();
		Workbook workbook = new XSSFWorkbook(userTypeExcelFile.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = worksheet.iterator();
        while (iterator.hasNext()) {
        	UserTypeDto userTypeDto = new UserTypeDto();
        	Row row = iterator.next();
        	if(row.getRowNum()!=0){
        	userTypeDto.setUsertypeName(row.getCell(1).getStringCellValue());
        	userTypeDto.setDescription(row.getCell(2).getStringCellValue());
        	userTypeDtos.add(userTypeDto);
        	}
        }
		return userTypeDtos;
	}

}
