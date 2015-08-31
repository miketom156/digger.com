package com.digger.dao;

import com.digger.model.User;


public interface IUserDao {

	//��¼
	public User getUser(String name);
	//�޸�����
	public void updateUser(User user);
	//ע��
	public void registUser(User user);
	
	public User selectUser(String name);
}
