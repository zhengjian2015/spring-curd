package com.zj.curd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.curd.dao.WikiArticlesDao;
import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;
import com.zj.curd.service.WikiArticlesService;

@Service("wikiArticlesService")
public class WikiArticlesServiceImpl implements WikiArticlesService{

	@Autowired
	private WikiArticlesDao wikiArticlesDao;
	
	@Override
	public List<WkArticlesauthor> ListArticles(Integer status) {
		// TODO Auto-generated method stub
		List<WkArticlesauthor> wkArticles = wikiArticlesDao.ListArticles(status);
		return wkArticles;
	}

	@Override
	public WkArticlesauthor getWkArticle(String artId) {
		// TODO Auto-generated method stub
		
		WkArticlesauthor wkArticles = wikiArticlesDao.getArticleById(artId);
		
		return wkArticles;
	}

	@Override
	public Map saveWkArticle(WkArticles wkArticle) {
		// TODO Auto-generated method stub
		Map<String,Object>  map = new HashMap<>();
		int i = wikiArticlesDao.saveArticle(wkArticle);
		map.put("code", 101);
		map.put("msg", "失败");
		if (i >0) {
		map.put("code", 200);
		map.put("msg", "成功");
		}
		return map;
	}

	@Override
	public Map updateArticle(WkArticles wkArticle) {
		// TODO Auto-generated method stub
		Map<String,Object>  map = new HashMap<>();
		int i = wikiArticlesDao.updateArticle(wkArticle);
		map.put("code", 101);
		map.put("msg", "失败");
		if (i >0) {
		map.put("code", 200);
		map.put("msg", "成功");
		}
		return map;
	}

	@Override
	public Map deleteArticle(String id) {
		Map<String,Object>  map = new HashMap<>();
		int i = wikiArticlesDao.deleteArticle(id);
		map.put("code", 101);
		map.put("msg", "失败");
		if (i >0) {
		map.put("code", 200);
		map.put("msg", "成功");
		}
		return map;
	}

}
