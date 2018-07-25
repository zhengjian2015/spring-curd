package com.zj.curd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestFontController {

	@RequestMapping("/testajax")
	public String testjq(){  
        return "test/testjq";  
    }
	
	@RequestMapping("/testajax2")
	public String testjq2(){  
        return "test/testjq2";  
    }
	
	@RequestMapping("/vuedemo")
	public String vuedemo(){  
        return "test/vue_demo";  
    }
	
	
}
