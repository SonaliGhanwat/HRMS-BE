
package com.nextech.hrms.model;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
@Entity
@NamedQuery(name="Employeeleave.findAll", query="SELECT e FROM Employeeleave e")
public class Employeeleave implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;

	@Column(name="created_date")
	private Timestamp createdDate;

	private boolean isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;

	private String subject;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	private String status;
	
	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="leavetypeid")
	private Leavetype leavetype;


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

	

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
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
	public Leavetype getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(Leavetype leavetype) {
		this.leavetype = leavetype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}