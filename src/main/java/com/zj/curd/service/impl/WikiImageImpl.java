package com.zj.curd.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zj.curd.controller.WiKiContriller;
import com.zj.curd.dao.WikiImagesDao;
import com.zj.curd.entity.WkImages;
import com.zj.curd.poi.Base64Utils;
import com.zj.curd.poi.MD5Util;
import com.zj.curd.pojo.User;
import com.zj.curd.service.WikiImageService;


@Service("wikiImageService")
public class WikiImageImpl implements WikiImageService{
	private static Logger logger = Logger.getLogger(WikiImageImpl.class); 
	@Autowired
	private WikiImagesDao wikiImagesDao;

	@Override
	public String saveImage(MultipartFile file,HttpServletRequest request) {
		// TODO Auto-generated method stub
		//System.out.println(file);
		String fileName = file.getOriginalFilename();
		//转为base64
		String fileData = Base64Utils.ImageToBase64ByLocal(file);
		WkImages wkImage = new WkImages();
		wkImage.setFileName(fileName);
		wkImage.setFileData(fileData);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		wkImage.setCreateTime(df.format(new Date()));
		//hash值
		String imgId = MD5Util.md5HashCode32(file);
		wkImage.setImgId(imgId);
		Integer imgSize = fileData.length();
		wkImage.setFileSize(imgSize);
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getUserName();
		wkImage.setCreateUser(userName);
		//如果已经有了就不保存了
		WkImages wkImagea = wikiImagesDao.getImageById(imgId);
		if (wkImagea != null) {
			return imgId;
		}
		int a = wikiImagesDao.saveImages(wkImage);
		if (a != 1) {
			imgId = "0";
		}
		return imgId;
	}

	@Override
	public String getImageData(String imgId) {
		// TODO Auto-generated method stub
		WkImages wkImages = wikiImagesDao.getImageById(imgId);
		String imgData =null;
		imgData = wkImages.getFileData();
		if(imgData != null && imgData != "")
			return imgData;
		return imgData;
	}

}
