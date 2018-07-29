package com.zj.curd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.curd.dao.WikiArticlesDao;
import com.zj.curd.entity.WkArticles;
import com.zj.curd.service.WikiArticlesService;

@Service("wikiArticlesService")
public class WikiArticlesServiceImpl implements WikiArticlesService{

	@Autowired
	private WikiArticlesDao wikiArticlesDao;
	
	@Override
	public List<WkArticles> ListArticles(Integer status) {
		// TODO Auto-generated method stub
		List<WkArticles> wkArticles = wikiArticlesDao.ListArticles(status);
		return wkArticles;
	}

	@Override
	public WkArticles getWkArticle(String artId) {
		// TODO Auto-generated method stub
		
		WkArticles wkArticles = wikiArticlesDao.getArticleById(artId);
		
		return wkArticles;
	}

}
