package com.nextech.hrms.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextech.hrms.dao.PageDao;
import com.nextech.hrms.factory.PageFactory;
import com.nextech.hrms.model.Page;
import com.nextech.hrms.dto.PageDTO;
import com.nextech.hrms.services.PageService;
@Service
public class PageServiceImpl extends CRUDServiceImpl<Page> implements PageService{

	@Autowired
	PageDao pageDao;
	@Override
	public Page getPageByUrl(String url) throws Exception {
		
		return pageDao.getPageByUrl(url);
	}
	@Override
	public List<PageDTO> getPageDTOList(List<PageDTO> pageDTOs) throws Exception {
		 pageDTOs = new ArrayList<PageDTO>();
		List<Page> pages = null;
		pages = pageDao.getList(Page.class);
		if(pages.isEmpty()){
			return null;
		}
		for (Page page : pages) {
			PageDTO pageDTO = PageFactory.setPageList(page);
			pageDTOs.add(pageDTO);
		}
		return pageDTOs;
	}
	@Override
	public PageDTO getPageDTOById(long id) throws Exception {
		Page page = pageDao.getById(Page.class, id);
		if(page==null){
			return null;
		}
		PageDTO pageDTO = PageFactory.setPageList(page);
		return pageDTO;
	}
	@Override
	public PageDTO deletePageById(long id) throws Exception {
		Page page = pageDao.getById(Page.class, id);
		if(page==null){
			return null;
		}
		page.setActive(false);
		pageDao.update(page);
		PageDTO pageDTO = PageFactory.setPageList(page);
		return pageDTO;
		
	}
	@Override
	public List<Page> getPageByIdList(long id) throws Exception {
		// TODO Auto-generated method stub
		return pageDao.getPageByIdList(id);
	}
	
}
