package com.digger.dao.impl;

import com.digger.model.User;
import com.digger.util.TemplateDB;
import com.digger.dao.IUserDao;;

public class UserDao implements IUserDao {

	//��¼
	public User getUser(String name){
		return (User)TemplateDB.templateQuery(User.class, name);
	}
	//�޸�����
	public void updateUser(User user)
	{
		TemplateDB.templateUpdate(user);
	}
	//ע��
	public void registUser(User user){
		TemplateDB.templateSave(user);
	}
	
	public User selectUser(String name){
		return (User)TemplateDB.templateQuery(User.class, name);
	}
}
