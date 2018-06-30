package com.zj.curd.testmybatis;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.curd.pojo.Department;
import com.zj.curd.pojo.Emp;
import com.zj.curd.service.IEmpService;


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestEmp {
	private static Logger logger = Logger.getLogger(TestMyBatis.class); 
	
	@Resource
	private IEmpService empService; 
	
	@Test
	public void testCRUD(){
		empService.saveEmployee(new Emp(null, "Molly", "M", "Molly@lvoyee.com", 5));
	}
	
	@Test
	public void testCRUD2(){
		
		 PageHelper.startPage(2,4);    
		List<Emp> emps = empService.getEmps();
		 System.out.println(emps.size());
		//logger.info(emps);
		// PageHelper.startPage(pn,5);    
	      //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以  
	     PageInfo pageInfo = new PageInfo<>(emps,5); 
	     System.out.println(pageInfo);
	}
}
