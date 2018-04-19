package com.nextech.hrms.model;

import javax.servlet.http.Cookie;

public class Status {

	private int code;
	private String message;
	private Object data;
	private Object user;
	private Cookie cookie;

	public Status() {
	}
	public Status(Object data) {
		this.data = data;
	}
	public Status(int code, String message) {
		this.code = code;
		this.message = message;
		
	}
	
	public Status(int code, String message ,Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Status(int code, String message,Object data, Object user,Cookie cookie) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.user= user;
		this.cookie = cookie;
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	public Object getUser() {
		return user;
	}
	public void setUser(Object user) {
		this.user = user;
	}
	public Cookie getCookie() {
		return cookie;
	}
	public void setCookie(Cookie cookie) {
		this.cookie = cookie;
	}
	
}
