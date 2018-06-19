package com.zj.curd.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zj.curd.pojo.Department;
import com.zj.curd.service.IDepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping(value="/depts",method=RequestMethod.GET)  
	@ResponseBody
    public Collection<Department> getDepts(){  
        Collection<Department> departList = departmentService.getgetDepts();
        return departList;  
    }  
	
	
}
