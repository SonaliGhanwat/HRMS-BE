package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the notificationuserassociation database table.
 * 
 */
@Entity
@NamedQuery(name="Notificationuserassociation.findAll", query="SELECT n FROM Notificationuserassociation n")
public class Notificationuserassociation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private boolean bcc;

	private boolean cc;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private boolean isActive;
	
	@Column(name="too")
	private boolean to;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Notification
	@ManyToOne
	@JoinColumn(name="notificationId")
	private Notification notification;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Notificationuserassociation() {
	}
	
	public Notificationuserassociation(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean getBcc() {
		return this.bcc;
	}

	public void setBcc(boolean bcc) {
		this.bcc = bcc;
	}

	public boolean getCc() {
		return this.cc;
	}

	public void setCc(boolean cc) {
		this.cc = cc;
	}

	public long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getIsactive() {
		return this.isActive;
	}

	public void setIsactive(boolean isactive) {
		this.isActive = isactive;
	}

	public boolean getTo() {
		return this.to;
	}

	public void setTo(boolean to) {
		this.to = to;
	}

	public long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}