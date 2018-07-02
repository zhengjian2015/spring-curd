package com.zj.curd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zj.curd.dao.ICompanyDao;
import com.zj.curd.pojo.Company;



@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestService {
	
	private static Logger logger = Logger.getLogger(TestService.class); 
	
	@Autowired
	private ICompanyService companyService = null;
	@Autowired
	private ICompanyDao companyDao = null;
	
	@Autowired
	private ImportService importService = null;
	
	
/*	@Test
	public void testCompanys(){
		List<Company> companys = new ArrayList<Company>();
		companys = companyDao.getCompanys();
		List<Object[]> datas = new ArrayList<Object[]>();
		for (Company company:companys) {
			Object[] obj = new Object[5];
			obj[0] = company.getCompanyName();
			obj[1] = company.getCompanyAddress();
			obj[2] = company.getCompanyIncome();
			obj[3] = company.getCompanyLogo();
			obj[4] = company.getCompanySort();
			datas.add(obj);
		}
		Map<String,List> info = new HashMap<String,List>();
		info.put("data", datas);
		System.out.println(info);
		String json = new Gson().toJson(info);
		System.out.println(json);
	}*/
	
/*	@Test
	public void testCompanys2(){
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map.put("companyName", "华为");
		map.put("companyAddress", "杭州");
		map.put("companyIncome", 2800);
		map.put("companyLogo", 1);
		map.put("companySort", 1);
		
		map2.put("companyName", "华为2");
		map2.put("companyAddress", "杭州2");
		map2.put("companyIncome", 2800);
		map2.put("companyLogo", 1);
		map2.put("companySort", 1);
        userList.add(map);
        userList.add(map2);
        System.out.println(userList);
        companyDao.deleteAll();
        int i = companyDao.insertCompanys(userList);
        logger.debug(i);
		
	}*/
	
	/*@Test
	public void testUpdateCompany() {
		importService.updateExcelFile();
	}*/
}