package com.zj.curd.service;

import java.util.List;

import com.zj.curd.entity.WkArticles;

public interface WikiArticlesService {
	List<WkArticles> ListArticles(Integer status);
	
	WkArticles getWkArticle(String artId);
}
