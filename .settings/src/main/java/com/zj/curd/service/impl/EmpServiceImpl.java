package com.zj.curd.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zj.curd.dao.IEmpDao;
import com.zj.curd.poi.WriteExcel;
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
	@Override
	public InputStream exportEmployee() throws Exception {
		// TODO Auto-generated method stub
		String[] title=new String[]{"id","姓名","性别","email","部门"};
		List<Emp> plist=empDao.getEmpAll();
		System.out.println(plist);
		List<Object[]>  dataList = new ArrayList<Object[]>();
		for(int i=0;i<plist.size();i++){
	        Object[] obj=new Object[5];
	        obj[0]=plist.get(i).getEmpId();
	        obj[1]=plist.get(i).getEmpName();
	        obj[2]=plist.get(i).getGender().equals("M")?"男":"女";
	        obj[3]=plist.get(i).getEmail();
	        obj[4]=plist.get(i).getDepartment().getDeptName();
	        dataList.add(obj);
        }
		WriteExcel ex = new WriteExcel(title, dataList);
		InputStream in;
		in = ex.export();
		return in;
	}
	
	

}
