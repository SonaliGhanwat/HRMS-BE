package com.nextech.hrms.Dto;

import java.sql.Timestamp;

public class AbstractDTO {
	private long id;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private boolean isIsActive;
	private int createdBy;
	private int updatedBy;
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isIsActive() {
		return isIsActive;
	}
	public void setIsActive(boolean isIsActive) {
		this.isIsActive = isIsActive;
	}
}
