package com.nextech.hrms.factory;

import java.util.HashMap;
import java.util.Map;

import com.nextech.hrms.dto.EmployeeLeaveDto;
import com.nextech.hrms.model.Employee;



public class MailResponseRequestFactory {

	public static Map<String, Object> setMailDetailsUser(Employee employee,EmployeeLeaveDto employeeLeaveDto,Employee employee2) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", employee.getFirstName());
		model.put("lastName", employee.getLastName());
		model.put("firstName1", employee2.getFirstName());
		model.put("lastName1", employee2.getLastName());
		model.put("fromDate", employeeLeaveDto.getFromDate());
		model.put("toDate",employeeLeaveDto.getToDate());
		model.put("status",employeeLeaveDto.getStatus());
		model.put("location", "Pune");
		model.put("signature", "www.NextechServices.in");
		return model;
	}
	
	
}
