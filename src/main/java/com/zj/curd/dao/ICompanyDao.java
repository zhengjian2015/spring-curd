package com.zj.curd.dao;

import java.util.List;
import java.util.Map;

import com.zj.curd.pojo.Company;

public interface ICompanyDao {
	public List<Company> getCompanys();
	
	public int insertCompanys(List<Map<String, Object>> companyList);
	
	
	public void deleteAll();
	
	public Company selectCompany(Integer companyId);
	
	public int updateCompany(Company company);
}


