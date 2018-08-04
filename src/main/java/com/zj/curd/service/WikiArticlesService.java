package com.zj.curd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;

public interface WikiArticlesService {
	PageInfo<WkArticlesauthor> ListArticles(HttpServletRequest request, Integer pn,Integer status);
	
	WkArticlesauthor getWkArticle(String artId);
	
	Map saveWkArticle(WkArticles wkArticle);
	
	Map updateArticle(WkArticles wkArticle);
	
	Map deleteArticle(String id);
	
	int updateMathchTime(String artId);
	

}
