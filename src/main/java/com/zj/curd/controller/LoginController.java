package com.zj.curd.controller;

import java.net.InetAddress;

//import static com.zj.blog.util.Functions.getIpAddr;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zj.curd.pojo.User;
import com.zj.curd.service.IUserService;

@Controller
public class LoginController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)  
    public String getLogin(){  
		
        return "login/login";  
    }
	
	
	//��¼��֤
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if(user==null) {
            map.put("code",0);
            map.put("msg","�û�����Ч��");
        } else if(!user.getUserPass().equals(password)) {
            map.put("code",0);
            map.put("msg","�������");
        } else {
            //��¼�ɹ�
            map.put("code",1);
            map.put("msg","");
            //���session
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("user_code", user.getUserCode());          
            //���cookie
            if(rememberme!=null) {
                //��������Cookie����
                Cookie nameCookie = new Cookie("username", username);
                //����Cookie����Ч��Ϊ3��
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
        user.setUserLastLoginTime(new Date());
        InetAddress address = InetAddress.getLocalHost();//��ȡ���Ǳ��ص�IP��ַ //PC-20140317PXKX/192.168.0.121
        String hostAddress = address.getHostAddress();//192.168.0.121     
        user.setUserLastLoginIp(hostAddress);
        userService.updateUser(user);

        }
        String result = new JSONObject(map).toString();
        System.out.println(result);
        return result;
    }

}
