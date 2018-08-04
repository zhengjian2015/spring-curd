package com.zj.curd.poi;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class Base64Utils {
	public static void main(String[] args) throws Exception {
		 
		//本地图片地址
		String url = "C:/Users/Administrator/Desktop/628947887489084892.jpg";
		//在线图片地址
		String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";
		
		//String str = Base64Utils.ImageToBase64ByLocal(url);
		
		//String ste = Base64Utils.ImageToBase64ByOnline(string);
		
		
	
	}
	

	 
	/**
	 * 本地图片转换成base64字符串
	 * @param imgFile	图片本地路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:40:46
	 */
	public static String ImageToBase64ByLocal(MultipartFile file) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
 
 
		InputStream in = null;
		byte[] data = null;
 
		// 读取图片字节数组
		try {
			//in = new FileInputStream(imgFile);
			
			data = file.getBytes();
			System.out.println(data);
			//in.read(data);
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
 
		return Base64.encodeBase64String(data);// 返回Base64编码过的字节数组字符串
	}


}
