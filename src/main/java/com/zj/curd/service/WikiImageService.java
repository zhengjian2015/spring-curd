package com.zj.curd.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface WikiImageService {
	String saveImage(MultipartFile file, HttpServletRequest request);
	
	String getImageData(String id);
}
