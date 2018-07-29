package com.zj.curd.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.entity.WkArticles;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration("classpath:spring-context.xml")
public class ListWikiArticles {
	
	private static Logger logger = Logger.getLogger(ListWikiArticles.class); 
	@Autowired
	private WikiArticlesService wikiArticlesService;
	
	@Test
    public void wikiArticles() {
		List<WkArticles> listWikiArticles = wikiArticlesService.ListArticles(0);
		logger.debug("*************************000");
		logger.debug(listWikiArticles);
		
	}
}
