package com.zj.curd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;
import com.zj.curd.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration("classpath:spring-context.xml")
public class ListWikiArticles {
	
	private static Logger logger = Logger.getLogger(ListWikiArticles.class); 
	@Autowired
	private WikiArticlesService wikiArticlesService;
	
	@Test
    public void wikiArticles() {
		String artId = "db8282c61e5745c1b94592e0a8cf7166";
		WkArticlesauthor wkArticlesauthor= wikiArticlesService.getWkArticle(artId);
		logger.debug("*************************000");
		logger.debug(wkArticlesauthor);
		logger.debug(wkArticlesauthor.getCreatefullName());
		
	}
	
	@Test
    public void listWikiArticles() {
		List<WkArticlesauthor> wkArticlesauthors= wikiArticlesService.ListArticles(0);
		logger.debug("*************************000");
		for(WkArticlesauthor wk : wkArticlesauthors) {
			logger.debug(wk.getCreatefullName());
		}
		
	}
	
	@Test
	public void setArticles() {
		WkArticles article = new WkArticles();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        article.setCreateTime(df.format(new Date()));
		article.setCreateUser("aa");
		System.out.println(article);
		article.setStatus(0);
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		article.setArtId(uuid);
		article.setMathchTimes(1);
		article.setArtTitle("abv");
		wikiArticlesService.saveWkArticle(article);
	}
}
