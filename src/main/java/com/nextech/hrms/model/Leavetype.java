package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the leavetype database table.
 * 
 */
@Entity
@NamedQuery(name="Leavetype.findAll", query="SELECT l FROM Leavetype l")
public class Leavetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_date")
	private Timestamp createdDate;

	private boolean isActive;

	@Column(name="name")
	private String name;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Employeeleave
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "leavetype", cascade = CascadeType.ALL)
	private List<Employeeleave> employeeleaves;

	public Leavetype() {
	}
	public Leavetype(int id) {
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

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Employeeleave> getEmployeeleaves() {
		return this.employeeleaves;
	}

	public void setEmployeeleaves(List<Employeeleave> employeeleaves) {
		this.employeeleaves = employeeleaves;
	}

	public Employeeleave addEmployeeleave(Employeeleave employeeleave) {
		getEmployeeleaves().add(employeeleave);
		employeeleave.setLeavetype(this);

		return employeeleave;
	}

	public Employeeleave removeEmployeeleave(Employeeleave employeeleave) {
		getEmployeeleaves().remove(employeeleave);
		employeeleave.setLeavetype(null);

		return employeeleave;
	}

}