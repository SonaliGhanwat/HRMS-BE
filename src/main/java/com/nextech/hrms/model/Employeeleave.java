package com.nextech.hrms.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the employeeleave database table.
 * 
 */
@Entity
@NamedQuery(name="Employeeleave.findAll", query="SELECT e FROM Employeeleave e")
public class Employeeleave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date afterleavejoiningdate;

	@Column(name="created_date")
	private Timestamp createdDate;

	private byte isActive;

	@Temporal(TemporalType.DATE)
	private Date leavedate;

	private String subject;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Employeeleave() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAfterleavejoiningdate() {
		return this.afterleavejoiningdate;
	}

	public void setAfterleavejoiningdate(Date afterleavejoiningdate) {
		this.afterleavejoiningdate = afterleavejoiningdate;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public Date getLeavedate() {
		return this.leavedate;
	}

	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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