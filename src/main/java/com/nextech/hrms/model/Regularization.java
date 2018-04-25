package com.nextech.hrms.model;

	import java.io.Serializable;
	import javax.persistence.*;
	import java.util.Date;
	import java.sql.Timestamp;


	/**
	 * The persistent class for the regularization database table.
	 * 
	 */
	@Entity
	@NamedQuery(name="Regularization.findAll", query="SELECT r FROM Regularization r")
	public class Regularization implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		private long id;

		private String comments;

		@Column(name="created_by")
		private int createdBy;

		@Column(name="created_date")
		private Timestamp createdDate;

		@Temporal(TemporalType.DATE)
		private Date date;

		private String reason;

		@Column(name="regularized_hours")
		private int regularizedHours;

		private String status;

		@Column(name="totalnumberof_hoursworked")
		private int totalnumberofHoursworked;

		@Column(name="updated_by")
		private int updatedBy;

		@Column(name="updated_date")
		private Timestamp updatedDate;

		private boolean isActive;
		
		//bi-directional many-to-one association to Employee
		@ManyToOne
		@JoinColumn(name="employeeid")
		private Employee employee;

		/*//bi-directional many-to-one association to Employeeattendance
		@ManyToOne
		@JoinColumn(name="attendanceid")
		private Employeeattendance employeeattendance;*/

		public Regularization() {
		}

		public Regularization(int id) {
			this.id=id;
		}


		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getComments() {
			return this.comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		public int getCreatedBy() {
			return this.createdBy;
		}

		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
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

		public String getReason() {
			return this.reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public int getRegularizedHours() {
			return this.regularizedHours;
		}

		public void setRegularizedHours(int regularizedHours) {
			this.regularizedHours = regularizedHours;
		}

		public String getStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getTotalnumberofHoursworked() {
			return this.totalnumberofHoursworked;
		}

		public void setTotalnumberofHoursworked(int totalnumberofHoursworked) {
			this.totalnumberofHoursworked = totalnumberofHoursworked;
		}

		public int getUpdatedBy() {
			return this.updatedBy;
		}

		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Timestamp getUpdatedDate() {
			return this.updatedDate;
		}

		
		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
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

		/*public Employeeattendance getEmployeeattendance() {
			return this.employeeattendance;
		}

		public void setEmployeeattendance(Employeeattendance employeeattendance) {
			this.employeeattendance = employeeattendance;
		}*/

	}
