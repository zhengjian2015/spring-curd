package com.zj.curd.service;

import java.io.InputStream;
import java.util.List;

import com.zj.curd.pojo.Emp;

public interface IEmpService {
	public List<Emp> getEmps();
	
	public int saveEmployee(Emp emp);
	
	
	public void deleteEmployee(Integer id);
	
	public InputStream exportEmployee() throws Exception;
	
	public Emp selectByPrimaryKey(Integer empId);

	int updateByPrimaryKey(Emp emps);
}
