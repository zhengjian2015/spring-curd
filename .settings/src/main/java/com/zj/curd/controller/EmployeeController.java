package com.zj.curd.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zj.curd.pojo.Department;
import com.zj.curd.pojo.Emp;
import com.zj.curd.service.IEmpService;

@Controller
public class EmployeeController {
	
	@Autowired
	private IEmpService empService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)  
    public String getIndex(){  
		
        return "index";  
    }
	
	@RequestMapping(value="/emps-json",method=RequestMethod.GET)  
    @ResponseBody
	public PageInfo getEmpsJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn){  
		
		PageHelper.startPage(pn, 5);
		List<Emp> emptList = empService.getEmps();
		PageInfo page = new PageInfo(emptList, 6);
        return page;  
    }  
	
	
	@RequestMapping(value="/emps",method=RequestMethod.GET)  
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
        //Collection<Emp> emptList = empService.getEmps();
        PageHelper.startPage(pn, 5);
        System.out.println(pn);
		// startPage��������������ѯ����һ����ҳ��ѯ
        List<Emp> emptList = empService.getEmps();
		// ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ������ˡ�
		// ��װ����ϸ�ķ�ҳ��Ϣ,���������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
		PageInfo page = new PageInfo(emptList, 6);
		model.addAttribute("pageInfo", page);
        return "emps/list";  
    }  
	
	/**
	 * ������������һ
	 * ����ɾ����1-2-3
	 * ����ɾ����1
	 * 
	 * @param ids ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Map<String,String> deleteEmp(@PathVariable("ids") String ids) {
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			//employeeService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			empService.deleteEmployee(id);
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "����ɹ�");
		return map;
	}
	
	@RequestMapping(value="/emps",method=RequestMethod.POST)
	@ResponseBody
    public JSONObject saveEmps(Emp emp){  
		System.out.println(emp);
		emp.setdId(9);
        //Collection<Emp> emptList = empService.getEmps();
		JSONObject jsonObject = new JSONObject();
		
		 int num = 0;
		try {
	        num = empService.saveEmployee(emp);
	        if (num != 0) {
	        	jsonObject.put("code", "200");
	        }
		} catch(Exception e) {
			e.printStackTrace();  
			jsonObject.put("message","err");
		}
	       System.out.println(num);
        return jsonObject;  
    }
	
	@RequestMapping(value="/export")
	public void exportExcel(HttpServletResponse response)  throws Exception{
		InputStream is=empService.exportEmployee();
		response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
	}
	
	
}
