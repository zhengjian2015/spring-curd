package com.zj.curd.service;

import java.util.List;

import com.zj.curd.pojo.Emp;

public interface IEmpService {
	public List<Emp> getEmps();
	
	public int saveEmployee(Emp emp);
	
	public void deleteEmployee(Integer id);
}
