package com.zj.curd.service;

import java.util.List;

import com.zj.curd.pojo.Department;

public interface IDepartmentService  {
	 public Department getDepartmentById(int departmentId); 
	 
	 public int addDepartment(Department department);
	 
	 public List<Department> getgetDepts();
	 
	 
}

