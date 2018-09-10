package com.zj.curd.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zj.curd.dto.Result;
import com.zj.curd.pojo.Company;
import com.zj.curd.pojo.Department;
import com.zj.curd.service.ICompanyService;
import com.zj.curd.service.ImportService;

@Controller
public class CompanyController {
	
	private Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Resource
	private ICompanyService companyService;
	
	@Resource
	private ImportService importService;
	
	
	@RequestMapping(value="/indexs",method=RequestMethod.GET) 
    public String index(){  
        return "company/index";  
    } 
	
	@RequestMapping(value="/companys",method=RequestMethod.GET) 
	@ResponseBody
    public String getCompanyList(){  
		String json= companyService.getCompanystoJson();
        return json;
    }
	
	@RequestMapping(value="/improtExcel",method=RequestMethod.POST) 
	@ResponseBody
	public Result<String> improtExcel(@RequestParam(value="uploadFile") MultipartFile file){  
		Result<String> result;
		try {
			int i = importService.readExcelFile(file);
			result = new Result(true,String.valueOf(i));
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			result = new Result(false,e.getMessage());
		}
		System.out.println(result);
		return result;
    }


}
