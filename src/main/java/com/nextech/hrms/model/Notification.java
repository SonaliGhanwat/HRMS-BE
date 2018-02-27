package com.nextech.hrms.model;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the notification database table.
 *
 */
@Entity
@NamedQuery(name="Notification.findAll", query="SELECT n FROM Notification n")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String beanClass;

	@Column(name="created_by")
	private long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	private String description;

	private boolean isActive;

	private String name;

	private String subject;

	private String template;

	private String type;

	@Column(name="updated_by")
	private long updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	
	
	private String code;

	//bi-directional many-to-one association to Notificationuserassociation
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "notification", cascade = CascadeType.ALL)
	private List<Notificationuserassociation> notificationuserassociations;

	public Notification() {
	}

	public Notification(int id) {
		this.id=id;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBeanClass() {
		return this.beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	
	public List<Notificationuserassociation> getNotificationuserassociations() {
		return this.notificationuserassociations;
	}

	public void setNotificationuserassociations(List<Notificationuserassociation> notificationuserassociations) {
		this.notificationuserassociations = notificationuserassociations;
	}

	public Notificationuserassociation addNotificationuserassociation(Notificationuserassociation notificationuserassociation) {
		getNotificationuserassociations().add(notificationuserassociation);
		notificationuserassociation.setNotification(this);

		return notificationuserassociation;
	}

	public Notificationuserassociation removeNotificationuserassociation(Notificationuserassociation notificationuserassociation) {
		getNotificationuserassociations().remove(notificationuserassociation);
		notificationuserassociation.setNotification(null);

		return notificationuserassociation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}