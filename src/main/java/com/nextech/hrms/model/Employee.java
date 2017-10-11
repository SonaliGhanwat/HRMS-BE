package com.nextech.hrms.model;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	//@NotBlank(message="address should not be blank")
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_joining")
	private Date dateOfJoining;

	//@NotBlank(message="Department should not be blank")
	private String department;
	
	//@Email(message="{Emailid should be enter valid")
	//@Size(min = 2, max = 255, message = "{Email sholud be greater than 2 or less than 255 characters}")
	private String emailid;

	//@NotBlank(message="FirstName should not be blank")
	//@Pattern(regexp = "[a-zA-z]+",message="{firstName sholud be Enter only characters}")  
	@Column(name="first_name")
	private String firstName;

	private boolean isActive;

	//@NotBlank(message="lastName should not be blank")
	//@Pattern(regexp = "[a-zA-z]+",message="{lastName sholud be Enter only characters}") 
	@Column(name="last_name")
	private String lastName;
	
	//@NotBlank(message="password should not be blank")
   // @Size(min=2,max=8,message="{password sholud be greater than 2 or less than 8 characters}")
	private String password;

    /*@NotBlank(message="phoneNumber should not be blank")
    @Pattern(regexp = "[0-9]+",message="{phoneNumber sholud be Enter only Number}")  
    @Size(min=10,max=10,message="{phoneNumber sholud be 10 Number}")*/
	@Column(name="phone_number")
	private long phoneNumber;

   // @NotBlank(message="salary should not be blank")
	private String salary;

	//@NotBlank(message="UserId should not be blank")
    //@Size(min=2,max=255,message="{UserId sholud be greater than 2 or less than 255 characters}")
	private String userid;
	
	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Usertype

	@ManyToOne
	@JoinColumn(name="usertypeId")
	private Usertype usertype;

	//bi-directional many-to-one association to Employeeattendance
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Employeeattendance> employeeattendances;

	//bi-directional many-to-one association to Employeedailytask
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Employeedailytask> employeedailytasks;

	//bi-directional many-to-one association to Employeelessave
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Employeeleave> employeeleaves;

	public Employee() {
		
	}
	public Employee(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfJoining() {
		return this.dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmailid() {
		return this.emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Usertype getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Employeeattendance> getEmployeeattendances() {
		return this.employeeattendances;
	}

	public void setEmployeeattendances(List<Employeeattendance> employeeattendances) {
		this.employeeattendances = employeeattendances;
	}

	public Employeeattendance addEmployeeattendance(Employeeattendance employeeattendance) {
		getEmployeeattendances().add(employeeattendance);
		employeeattendance.setEmployee(this);

		return employeeattendance;
	}

	public Employeeattendance removeEmployeeattendance(Employeeattendance employeeattendance) {
		getEmployeeattendances().remove(employeeattendance);
		employeeattendance.setEmployee(null);

		return employeeattendance;
	}

	public List<Employeedailytask> getEmployeedailytasks() {
		return this.employeedailytasks;
	}

	public void setEmployeedailytasks(List<Employeedailytask> employeedailytasks) {
		this.employeedailytasks = employeedailytasks;
	}

	public Employeedailytask addEmployeedailytask(Employeedailytask employeedailytask) {
		getEmployeedailytasks().add(employeedailytask);
		employeedailytask.setEmployee(this);

		return employeedailytask;
	}

	public Employeedailytask removeEmployeedailytask(Employeedailytask employeedailytask) {
		getEmployeedailytasks().remove(employeedailytask);
		employeedailytask.setEmployee(null);

		return employeedailytask;
	}

	public List<Employeeleave> getEmployeeleaves() {
		return this.employeeleaves;
	}

	public void setEmployeeleaves(List<Employeeleave> employeeleaves) {
		this.employeeleaves = employeeleaves;
	}

	public Employeeleave addEmployeeleave(Employeeleave employeeleave) {
		getEmployeeleaves().add(employeeleave);
		employeeleave.setEmployee(this);

		return employeeleave;
	}

	public Employeeleave removeEmployeeleave(Employeeleave employeeleave) {
		getEmployeeleaves().remove(employeeleave);
		employeeleave.setEmployee(null);

		return employeeleave;
	}

}