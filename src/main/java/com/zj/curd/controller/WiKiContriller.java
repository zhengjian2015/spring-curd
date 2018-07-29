package com.zj.curd.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.service.WikiArticlesService;


@Controller
@RequestMapping("/wiki")
public class WiKiContriller {
	
	@Resource
	private WikiArticlesService wikiArticlesService;
	
	@RequestMapping("/wklist")
	public ModelAndView ListArticles() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<WkArticles> wikiArticlesList = wikiArticlesService.ListArticles(0);
		
		modelAndView.addObject("wikiArticlesList",wikiArticlesList);
		
		modelAndView.setViewName("/wiki/wklist");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/wkv2/{id}")
	public ModelAndView getArticles(@PathVariable(value="id") String id) {
		
		ModelAndView modelAndView = new ModelAndView();
		String keywords = "Java";
		modelAndView.addObject("keywords",keywords);
		WkArticles artldata = wikiArticlesService.getWkArticle(id);
		modelAndView.addObject("artldata",artldata);
		modelAndView.setViewName("/wiki/wkv2");
		return modelAndView;
	}
	
	@RequestMapping("/wkedit")
	public String addArticle() {
		return "wiki/wkedit";
	}
}
