package com.zj.curd.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;

public interface WikiArticlesDao {
	List<WkArticlesauthor> ListArticles(@Param(value="status") Integer status);
	
	WkArticlesauthor getArticleById(@Param(value="artId") String artId);
	
	int saveArticle(WkArticles WkArticle);
	
	int updateArticle(WkArticles WkArticle);
	
	int deleteArticle(@Param(value="artId") String artId);
}
