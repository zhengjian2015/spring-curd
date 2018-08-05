package com.zj.curd.dao;

import java.util.List;

import com.zj.curd.pojo.User;

public interface IUserDao {
	public User getUserByNameOrEmail(String str);
	
	public Integer updateByPrimaryKeySelective(User user);
	
	List<User> getUserCodes();
}
