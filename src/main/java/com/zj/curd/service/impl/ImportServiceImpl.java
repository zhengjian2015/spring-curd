package com.zj.curd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import com.zj.curd.testmybatis.TestMyBatis;


@Service
public class ImportServiceImpl implements ImportService {
	
	private static Logger logger = Logger.getLogger(ImportServiceImpl.class); 
	
	@Autowired
	private ICompanyDao companyDao;
	@Override
	public int readExcelFile(MultipartFile file) throws Exception {
		// Map<String,Object> map = new HashMap<>();
		// ��������EXCEL����
		ReadExcel readExcel = new ReadExcel();
		List<Map<String, Object>> userList = readExcel.getExcelInfo(file);
		// ����ǰ��ɾ��ȫ����
		try {
			companyDao.deleteAll();
			//int a = 1 / 0;
			int i = companyDao.insertCompanys(userList);
			System.out.println(i); // foreacch i����Ŀ�ǲ��������
			return i;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
			// ���б������쳣 ת��Ϊ �������쳣
			throw new Exception(e.getMessage());
		}

	}
	@Override
	public void updateExcelFile() {
		// TODO Auto-generated method stub
		companyDao.deleteAll();
		int b = 1/0;
		Company company = companyDao.selectCompany(1);
		company.setCompanyName("����");
		Integer a = companyDao.updateCompany(company);
	}
}
