package com.zj.curd.service;

import com.zj.curd.pojo.User;

public interface IUserService {
	//�����û����������ѯ�û�
	public User getUserByNameOrEmail(String str);
	
	//�޸��û���Ϣ
	public void updateUser(User user);
}
