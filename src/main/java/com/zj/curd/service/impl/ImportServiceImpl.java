package com.zj.curd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zj.curd.dao.ICompanyDao;
import com.zj.curd.poi.ReadExcel;
import com.zj.curd.pojo.Company;
import com.zj.curd.service.ImportService;


@Service
public class ImportServiceImpl implements ImportService {
	@Autowired
	private ICompanyDao companyDao;
	@Override
	public Map readExcelFile(MultipartFile file){
		Map<String,Object>  map = new HashMap<>();
		//创建处理EXCEL的类  
		ReadExcel readExcel = new ReadExcel();
		List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
		//插入前先删除全部的
		companyDao.deleteAll();
		//int a = 1/0;
		int i = companyDao.insertCompanys(userList);
		System.out.println(i); //foreacch i是数目是插入的条数
		map.put("code", 101);
		map.put("msg", "失败");
		if (i >0) {
		map.put("code", 200);
		map.put("msg", "成功");
		}
		return map;
	}
	@Override
	public void updateExcelFile() {
		// TODO Auto-generated method stub
		companyDao.deleteAll();
		int b = 1/0;
		Company company = companyDao.selectCompany(1);
		company.setCompanyName("网易");
		Integer a = companyDao.updateCompany(company);
	}
}
