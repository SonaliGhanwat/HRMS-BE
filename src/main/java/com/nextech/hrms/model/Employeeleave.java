package com.nextech.hrms.model;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="To_Date")
	private Date afterleavejoiningdate;

	@Column(name="created_date")
	private Timestamp createdDate;

	private boolean isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="from_Date")
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
	
	public Employeeleave(int id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
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