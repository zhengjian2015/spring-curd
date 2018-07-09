package com.zj.curd.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zj.curd.dao.IUserDao;
import com.zj.curd.pojo.User;
import com.zj.curd.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Override
	public User getUserByNameOrEmail(String str) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByNameOrEmail(str);
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.updateByPrimaryKeySelective(user);
	}

}
