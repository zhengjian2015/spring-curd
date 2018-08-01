package com.zj.curd.service;

import java.util.List;
import java.util.Map;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;

public interface WikiArticlesService {
	List<WkArticlesauthor> ListArticles(Integer status);
	
	WkArticlesauthor getWkArticle(String artId);
	
	Map saveWkArticle(WkArticles wkArticle);
	
	Map updateArticle(WkArticles wkArticle);
	
	Map deleteArticle(String id);
}
