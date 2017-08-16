package com.nextech.hrms.services;

import java.util.List;

public interface CRUDService<T> {
	T getEntityById(Class<T> z, long id) throws Exception;

	List<T> getEntityList(Class<T> z) throws Exception;

	boolean deleteEntity(Class<T> z, long id) throws Exception;

	T updateEntity(T bean) throws Exception;

	Long addEntity(T bean) throws Exception;
	
}
