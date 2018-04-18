package com.nextech.hrms.dto;

import com.nextech.hrms.dto.AbstractDTO;




public class PageDTO extends AbstractDTO{

	private String menu;
	private String pageName;
	private String submenu;
	private String url;
	private String description;
	public PageDTO(){
		
	}
	public PageDTO(int id){
		this.setId(id);
	}

	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getSubmenu() {
		return submenu;
	}
	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
