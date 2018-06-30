package com.zj.curd.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zj.curd.pojo.Department;
@Repository
public interface IDepartmentService  {
	 public Department getDepartmentById(int departmentId); 
	 
	 public int addDepartment(Department department);
	 
	 public List<Department> getgetDepts();
	 
	 
}

