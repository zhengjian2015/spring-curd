package com.zj.curd.dao;

import com.zj.curd.pojo.User;

public interface IUserDao {
	public User getUserByNameOrEmail(String str);
	
	public Integer updateByPrimaryKeySelective(User user);
}
