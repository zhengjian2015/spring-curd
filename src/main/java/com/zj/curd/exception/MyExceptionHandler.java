package com.zj.curd.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class MyExceptionHandler {
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Map handeException(Exception ex) {
		Map<String,Object> map = new HashMap();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
	}
}