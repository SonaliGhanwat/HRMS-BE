package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the employeeattendance database table.
 * 
 */
@Entity
@NamedQuery(name="Employeeattendance.findAll", query="SELECT e FROM Employeeattendance e")
public class Employeeattendance implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Time intime;

	private boolean isActive;

	private Time outtime;

	private String status;

	private long totaltime;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Employeeattendance() {
	}
	
	public Employeeattendance(int id) {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getIntime() {
		return this.intime;
	}

	public void setIntime(Time intime) {
		this.intime = intime;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Time getOuttime() {
		return this.outtime;
	}

	public void setOuttime(Time outtime) {
		this.outtime = outtime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotaltime() {
		return this.totaltime;
	}

	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}