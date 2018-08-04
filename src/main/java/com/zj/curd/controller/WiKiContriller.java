package com.zj.curd.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.zj.curd.entity.WkArticles;
import com.zj.curd.entity.WkArticlesauthor;
import com.zj.curd.pojo.User;
import com.zj.curd.service.WikiArticlesService;
import com.zj.curd.service.WikiImageService;
import com.zj.curd.testmybatis.TestMyBatis;


@Controller
@RequestMapping("/wiki")
public class WiKiContriller {
	private static Logger logger = Logger.getLogger(WiKiContriller.class); 
	@Resource
	private WikiArticlesService wikiArticlesService;
	
	@Resource
	private WikiImageService wikiImageService;
	
	
	@RequestMapping("/wklist")
	public ModelAndView ListArticles(@RequestParam(value = "pn", defaultValue = "1") Integer pn,HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		PageInfo<WkArticlesauthor> page = wikiArticlesService.ListArticles(request,pn,0);
		List<WkArticles> wkArticlesHot = wikiArticlesService.hotWkArticles(0);
		logger.debug("page "+page);
		modelAndView.addObject("hotart",wkArticlesHot);
		modelAndView.addObject("pageInfo",page);
		modelAndView.setViewName("/wiki/wklist");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/wkv2/{id}")
	public ModelAndView getArticles(@PathVariable(value="id") String id) {
		
		ModelAndView modelAndView = new ModelAndView();
		WkArticlesauthor artldata = wikiArticlesService.getWkArticle(id);
		List<WkArticles> wkArticlesHot = wikiArticlesService.listrelaWkArticles(artldata);
		//更新watchtime
		wikiArticlesService.updateMathchTime(id);
		List<WkArticles> wkArticlesRelist = wikiArticlesService.listrelaWkArticles(artldata);
		modelAndView.addObject("listArt",wkArticlesRelist);
		modelAndView.addObject("artldata",artldata);
		modelAndView.setViewName("/wiki/wkv2");
		return modelAndView;
	}
	
	@RequestMapping(value="/wkv1/{id}")
	public String getArticleonly(@PathVariable(value="id") String id,Model model) {
		model.addAttribute("artldata", id);
		return "wiki/wkv1";
		
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
		return map;
	}
	
	@RequestMapping(value="/art/{id}",method=RequestMethod.GET)
	@ResponseBody
	public WkArticlesauthor selectArticle(@PathVariable(value="id") String artId) {
		WkArticlesauthor artldata = wikiArticlesService.getWkArticle(artId);
		return artldata;
	}
	
	@RequestMapping(value="/art/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Map deleteArticle(@PathVariable(value="id") String artId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map = wikiArticlesService.deleteArticle(artId);
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
	
     //上传图片
	 @RequestMapping(value="/uploadfile",method=RequestMethod.POST)
	 @ResponseBody
	 public Map<String,Object> getImage(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "editormd_image_file", required = true) MultipartFile file){
	
		 String path = request.getContextPath();
		 Map<String,Object> result = new HashMap<String,Object>();
		 result.put("success", 1);
		 result.put("message", "上传成功");
		 result.put("url", "图片地址");
		 String imagId = wikiImageService.saveImage(file,request);
		 String path1 = path+"/wiki/img?id="+imagId;
		 result.put("url", path1);
		 if (imagId == "0") {
			 result.put("success", 0);
			 result.put("message", "上传图标数据为空！");
		 }
		 return result;
	 }
	 
	 @RequestMapping(value="/img",method=RequestMethod.GET)
	 @ResponseBody
	 public byte[] getImage(HttpServletRequest request) {
		 String ids = request.getParameter("id");
		 String imgData = wikiImageService.getImageData(ids);
		 //imgData是base64,需要解码为byte
		 return Base64.decodeBase64(imgData);
		 
	 }
}
