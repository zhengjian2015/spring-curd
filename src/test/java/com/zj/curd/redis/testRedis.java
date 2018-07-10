package com.zj.curd.redis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.pojo.Company;
import com.zj.curd.pojo.User;
import com.zj.curd.service.ICompanyService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class testRedis {
	
	private static Logger logger = Logger.getLogger(testRedis.class);
	
	@Resource
	private ICompanyService companyService;
	
	@Test
	public void testComp(){
		
		List<Company> companys = companyService.getCompanys();
		logger.debug(companys);
	}
}
