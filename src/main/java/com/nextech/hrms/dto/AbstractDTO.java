package com.nextech.hrms.dto;

import java.sql.Timestamp;

public class AbstractDTO {
	private long id;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private boolean isIsActive;
	private long createdBy;
	private long updatedBy;
	private boolean hasRead;
	
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
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public boolean isHasRead() {
		return hasRead;
	}
	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}
	
	
}
