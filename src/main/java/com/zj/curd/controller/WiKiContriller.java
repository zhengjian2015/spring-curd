package com.zj.curd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;
import com.zj.curd.pojo.User;
import com.zj.curd.service.WikiArticlesService;


@Controller
@RequestMapping("/wiki")
public class WiKiContriller {
	
	@Resource
	private WikiArticlesService wikiArticlesService;
	
	@RequestMapping("/wklist")
	public ModelAndView ListArticles() {
		
		ModelAndView modelAndView = new ModelAndView();
		List<WkArticlesauthor> wikiArticlesList = wikiArticlesService.ListArticles(0);
		
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
	
	@RequestMapping(value="/art",method=RequestMethod.POST)
	@ResponseBody
	public Map setArticle(WkArticles article,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        article.setCreateTime(df.format(new Date()));
		String userName = user.getUserName();
		article.setCreateUser(userName);
		article.setUpdateUser(userName);
		article.setUpdateTime(df.format(new Date()));
		article.setStatus(0);
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		article.setArtId(uuid);
		article.setMathchTimes(1);
		Map<String,Object> map = new HashMap<String, Object>();
		map = wikiArticlesService.saveWkArticle(article);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="/art/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Map updateArticle(@PathVariable(value="id") String artId,WkArticles article,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        article.setCreateTime(df.format(new Date()));
		String userName = user.getUserName();
		article.setArtId(artId);
		article.setUpdateUser(userName);
		article.setUpdateTime(df.format(new Date()));
		article.setStatus(0);
		Map<String,Object> map = new HashMap<String, Object>();
		map = wikiArticlesService.updateArticle(article);
		System.out.println(map);
		return map;
	}
	
	@RequestMapping(value="/art/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Map deleteArticle(@PathVariable(value="id") String artId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map = wikiArticlesService.deleteArticle(artId);
		System.out.println(map);
		return map;
	}
	
	//编辑回写
	@RequestMapping(value="/wkedit/{id}",method=RequestMethod.GET)
	public ModelAndView showArticle(@PathVariable(value="id") String id) {
		
		ModelAndView modelAndView = new ModelAndView();
		WkArticles artldata = wikiArticlesService.getWkArticle(id);
		modelAndView.addObject("artldata",artldata);
		modelAndView.setViewName("/wiki/wkedit");
		return modelAndView;
	}
}
