package com.zj.spring.pojo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class IOCTest {
	
	@Test
	public void testCreatePerson() {
		//��������
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("applicationContext.xml");
		//���Ҷ���
		Person p = (Person)context.getBean("p");
		System.out.println(p);
	}
	
}