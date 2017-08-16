package com.nextech.hrms.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.SuperDao;
import com.nextech.hrms.services.CRUDService;

@Service
public class CRUDServiceImpl<T> implements CRUDService<T> {

	@Qualifier("superDao")
	@Autowired
	SuperDao<T> dao;

	@Override
	public T getEntityById(Class<T> z,long id) throws Exception {
		return dao.getById(z, id);
	}

	@Override
	public List<T> getEntityList(Class<T> z) throws Exception {
		return dao.getList(z);
	}

	@Override
	public boolean deleteEntity(Class<T> z,long id) throws Exception {
		return dao.delete(z,id);
	}

	@Override
	public T updateEntity(T bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public Long addEntity(T bean) throws Exception {
		return dao.add(bean);
	}
}
