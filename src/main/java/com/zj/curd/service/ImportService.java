package com.zj.curd.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
	/** 
     * ��ȡexcel�е�����,����list 
	 * @throws Exception 
     */  
    int readExcelFile(MultipartFile file) throws Exception;
    
    void updateExcelFile();
}
