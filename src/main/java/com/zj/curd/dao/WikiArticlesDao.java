package com.zj.curd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;

public interface WikiArticlesDao {
	//������ģ�������ͱ�ǩ�� ��ȡ��������
	List<WkArticlesauthor> ListArticles(@Param(value="status") Integer status,@Param(value="list_qry") List<String> list_qry,@Param(value="tag") String tag);
	
	WkArticlesauthor getArticleById(@Param(value="artId") String artId);
	
	int saveArticle(WkArticles WkArticle);
	
	int updateArticle(WkArticles WkArticle);
	
	int deleteArticle(@Param(value="artId") String artId);
	
	int updateMathchTime(@Param(value="artId") String artId);
	
	List<WkArticles> hotWkArticles(@Param(value="status") Integer status);
	
	List<WkArticles> listrelaWkArticles(@Param(value="status") Integer status,@Param(value="KeywordsList") List<String> KeywordsList,@Param(value="artId") String artId);
	
	//tag��ǩ
	List<String> getTags(@Param(value="status") Integer status);

	int saveModiUsers(@Param(value="artId") String artId,@Param(value="canmodiUsers") String canmodiUsers);
	
	
	
}