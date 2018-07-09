package com.zj.curd.service;

import com.zj.curd.pojo.User;

public interface IUserService {
	//根据用户名和邮箱查询用户
	public User getUserByNameOrEmail(String str);
	
	//修改用户信息
	public void updateUser(User user);
}
