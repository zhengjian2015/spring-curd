package com.zj.curd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zj.curd.dao.IDepartDao;
import com.zj.curd.pojo.Department;
import com.zj.curd.service.IDepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {
	
	@Resource
	private IDepartDao departDao;
	
	@Override
	public Department getDepartmentById(int departmentId) {
		// TODO Auto-generated method stub
		return departDao.selectByPrimaryKey(departmentId);
	}

	@Override
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		
		return departDao.insert(department);
	}

	@Override
	public List<Department> getgetDepts() {
		// TODO Auto-generated method stub
		return departDao.selectByExample(null);
	}

}


