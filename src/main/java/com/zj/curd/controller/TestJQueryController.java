package com.zj.curd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestJQueryController {

	@RequestMapping("/testajax")
	public String testjq(){  
        return "test/testjq";  
    }
	
	@RequestMapping("/testajax2")
	public String testjq2(){  
        return "test/testjq2";  
    } 
}
