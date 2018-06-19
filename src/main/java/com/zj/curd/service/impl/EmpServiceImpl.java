package com.zj.curd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.curd.dao.IEmpDao;
import com.zj.curd.pojo.Emp;
import com.zj.curd.service.IEmpService;


@Service("empService")
public class EmpServiceImpl implements IEmpService{

	@Resource
	private IEmpDao empDao;
	@Override
	public List<Emp> getEmps() {
		// TODO Auto-generated method stub
		return empDao.getEmpAll();
	}
	@Override
	public int saveEmployee(Emp emp) {
		return empDao.insert(emp);
	}
	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		empDao.deleteByPrimaryKey(id);
	}
	

}
