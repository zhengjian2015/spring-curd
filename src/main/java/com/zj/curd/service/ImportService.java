package com.zj.curd.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
	/** 
     * 读取excel中的数据,生成list 
	 * @throws Exception 
     */  
    int readExcelFile(MultipartFile file) throws Exception;
    
    void updateExcelFile();
}
