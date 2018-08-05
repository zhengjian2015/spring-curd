package com.zj.curd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-context.xml"})  
public class testUser {
	
	private static Logger logger = Logger.getLogger(TestService.class);
	
	@Resource
	private IUserService userService;
	
	@Test
	public void testUsers(){
		
		String name = "admin";
		User user = userService.getUserByNameOrEmail(name);
		logger.debug("3333");
		logger.debug(user);
		logger.debug(user.getUserCode());
	}
	
	@Test
	public void testUsers2(){
		
		String name = "admin";
		List<Map> ol = new ArrayList<Map>();
		List<User> users = userService.getUserCodes();
		logger.debug("3333");
		//logger.debug(users);
		for (User u:users) {
			Map<String,String> m = new HashMap<String,String>();
			m.put(u.getUserCode(),u.getUserNickname());
			ol.add(m);
		}
		logger.debug(ol);
		//logger.debug(user.getUserCode());
	}
}
