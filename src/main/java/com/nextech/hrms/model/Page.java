package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;



import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the page database table.
 * 
 */
@Entity
@NamedQuery(name="Page.findAll", query="SELECT p FROM Page p")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@NotBlank(message="{description should not be blank}")
	@Size(min = 4, max = 255, message = "{description sholud be greater than 4 or less than 255 characters}")
	private String description;

	private boolean isActive;

	private String menu;

	@Column(name="page_name")
	private String pageName;

	private String submenu;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	private String url;

	//bi-directional many-to-one association to Usertypepageassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page", cascade = CascadeType.ALL)
	private List<Usertypepageassociation> usertypepageassociations;

	public Page() {
	}
	public Page(int id) {
		this.id=id;
	}


	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getMenu() {
		return this.menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getSubmenu() {
		return this.submenu;
	}

	public void setSubmenu(String submenu) {
		this.submenu = submenu;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Usertypepageassociation> getUsertypepageassociations() {
		return this.usertypepageassociations;
	}

	public void setUsertypepageassociations(List<Usertypepageassociation> usertypepageassociations) {
		this.usertypepageassociations = usertypepageassociations;
	}

	public Usertypepageassociation addUsertypepageassociation(Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().add(usertypepageassociation);
		usertypepageassociation.setPage(this);

		return usertypepageassociation;
	}

	public Usertypepageassociation removeUsertypepageassociation(Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().remove(usertypepageassociation);
		usertypepageassociation.setPage(null);

		return usertypepageassociation;
	}

}