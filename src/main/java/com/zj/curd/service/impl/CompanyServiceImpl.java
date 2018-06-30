package com.zj.curd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zj.curd.dao.ICompanyDao;
import com.zj.curd.pojo.Company;
import com.zj.curd.service.ICompanyService;


@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	@Override
	public String getCompanystoJson() {
		// TODO Auto-generated method stub
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
		String json = new Gson().toJson(info);
		return json;
	}
	


}
