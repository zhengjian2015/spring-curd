package com.zj.curd.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zj.curd.pojo.Department;
import com.zj.curd.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class); 
	@Resource
	private IDepartmentService departmentService = null;  
	@Test
	public void testCRUD(){
		int a = departmentService.addDepartment(new Department(null,"开发部"));
		logger.info(a);
	}
	
	@Test
	public void testCRUD2(){
		Department dep = departmentService.getDepartmentById(1);
		logger.info(dep);
	}
	
	@Test
	public void testCRUD3(){
		List<Department> depts = departmentService.getgetDepts();
		for(Department dept:depts) {
			
			logger.info("******");
			logger.info(dept);
		}
    }
	
}
