package com.nextech.hrms.dto;

import java.util.Date;

import com.nextech.hrms.model.Department;
import com.nextech.hrms.model.Designation;
import com.nextech.hrms.model.Employeetype;
import com.nextech.hrms.model.Usertype;

public class EmployeePart extends AbstractDTO{

	
	private String userid;
	private String password;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String emailid;
	private Date dateOfJoining;
	private Date dateOfBirth;
	private String address;
	private String salary;
	private int reportTo;
	private Usertype usertype;
	private Employeetype employeetype;
	private Designation designation;
	private Department department;
	
public EmployeePart(){
		
		
	}
	
	public EmployeePart(int id){
		this.setId(id);
		
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public int getReportTo() {
		return reportTo;
	}
	public void setReportTo(int reportTo) {
		this.reportTo = reportTo;
	}
	
	public Usertype getUsertype() {
		return usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

	public Employeetype getEmployeetype() {
		return employeetype;
	}
	public void setEmployeetype(Employeetype employeetype) {
		this.employeetype = employeetype;
	}
	public Designation getDesignation() {
		return designation;
	}
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
