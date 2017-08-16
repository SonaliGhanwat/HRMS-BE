package com.nextech.hrms.dao;

import java.util.List;

public interface SuperDao<T> {
	public Long add(T bean) throws Exception;

	public T getById(Class<T> z,long id) throws Exception;

	public List<T> getList(Class<T> z) throws Exception;

	public boolean delete(Class<T> z,long id) throws Exception;

	public T update(T bean) throws Exception;

}

