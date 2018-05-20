package com.zj.spring.pojo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class IOCTest {
	
	@Test
	public void testCreatePerson() {
		//创建容器
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
		//查找对象
		Person p = (Person)context.getBean("p");
		System.out.println(p);
	}
	
}