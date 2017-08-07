package com.nextech.hrms.model;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usertype database table.
 * 
 */
@Entity
@NamedQuery(name="Usertype.findAll", query="SELECT u FROM Usertype u")
public class Usertype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isActive;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	@Column(name="usertype_name")
	private String usertypeName;

	//bi-directional many-to-one association to Employee
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usertype", cascade = CascadeType.ALL)
	private List<Employee> employees;

	public Usertype() {
	}
	public Usertype(int id) {
		this.id=id;
	}
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsertypeName() {
		return this.usertypeName;
	}

	public void setUsertypeName(String usertypeName) {
		this.usertypeName = usertypeName;
	}

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		employee.setUsertype(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setUsertype(null);

		return employee;
	}

}