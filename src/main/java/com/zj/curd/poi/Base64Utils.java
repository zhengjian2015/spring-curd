package com.zj.curd.poi;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class Base64Utils {
	public static void main(String[] args) throws Exception {
		 
		//����ͼƬ��ַ
		String url = "C:/Users/Administrator/Desktop/628947887489084892.jpg";
		//����ͼƬ��ַ
		String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";
		
		//String str = Base64Utils.ImageToBase64ByLocal(url);
		
		//String ste = Base64Utils.ImageToBase64ByOnline(string);
		
		
	
	}
	

	 
	/**
	 * ����ͼƬת����base64�ַ���
	 * @param imgFile	ͼƬ����·��
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:40:46
	 */
	public static String ImageToBase64ByLocal(MultipartFile file) {// ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
 
 
		InputStream in = null;
		byte[] data = null;
 
		// ��ȡͼƬ�ֽ�����
		try {
			//in = new FileInputStream(imgFile);
			
			data = file.getBytes();
			System.out.println(data);
			//in.read(data);
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ���ֽ�����Base64����
 
		return Base64.encodeBase64String(data);// ����Base64��������ֽ������ַ���
	}


}
