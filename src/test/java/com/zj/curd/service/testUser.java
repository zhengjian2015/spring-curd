package com.zj.curd.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class testUser {
	
	private static Logger logger = Logger.getLogger(TestService.class);
	
	@Resource
	private IUserService userService;
	
	@Test
	public void testUsers(){
		
		String name = "admin";
		User user = userService.getUserByNameOrEmail(name);
		logger.debug(user);
	}
}
