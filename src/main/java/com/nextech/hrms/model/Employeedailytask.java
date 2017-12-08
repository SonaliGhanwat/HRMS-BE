package com.nextech.hrms.model;
import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the employeedailytask database table.
 * 
 */
@Entity
@NamedQuery(name="Employeedailytask.findAll", query="SELECT e FROM Employeedailytask e")
public class Employeedailytask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Time endtime;

	@Column(name="estimation_time")
	private Time estimationTime;

	private boolean isActive;

	private Time starttime;
	
	@Column(name="status")
	private String status;

	@Column(name="taken_time")
	private long takenTime;

	@Column(name="task_name")
	private String taskName;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Employeedailytask() {
	}
	
	public Employeedailytask(int id) {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Time endtime) {
		this.endtime = endtime;
	}

	public Time getEstimationTime() {
		return this.estimationTime;
	}

	public void setEstimationTime(Time estimationTime) {
		this.estimationTime = estimationTime;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Time getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}

	public long getTakenTime() {
		return this.takenTime;
	}

	public void setTakenTime(long takenTime) {
		this.takenTime = takenTime;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}