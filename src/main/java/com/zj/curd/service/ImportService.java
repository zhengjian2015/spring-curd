package com.zj.curd.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
	/** 
     * ��ȡexcel�е�����,����list 
	 * @throws Exception 
     */  
    Map readExcelFile(MultipartFile file);
    
    void updateExcelFile();
}
