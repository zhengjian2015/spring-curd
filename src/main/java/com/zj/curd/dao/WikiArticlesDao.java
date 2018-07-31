package com.zj.curd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zj.curd.entity.WkArticles;

public interface WikiArticlesDao {
	List<WkArticles> ListArticles(@Param(value="status") Integer status);
	
	WkArticles getArticleById(@Param(value="artId") String artId);
	
	int saveArticle(WkArticles WkArticle);
	
	int updateArticle(WkArticles WkArticle);
	
	int deleteArticle(@Param(value="artId") String artId);
}
