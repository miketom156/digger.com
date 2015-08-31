package com.digger.dao.impl;

import com.digger.model.User;
import com.digger.util.TemplateDB;
import com.digger.dao.IUserDao;;

public class UserDao implements IUserDao {

	//µÇÂ¼
	public User getUser(String name){
		return (User)TemplateDB.templateQuery(User.class, name);
	}
	//ÐÞ¸ÄÃÜÂë
	public void updateUser(User user)
	{
		TemplateDB.templateUpdate(user);
	}
	//×¢²á
	public void registUser(User user){
		TemplateDB.templateSave(user);
	}
	
	public User selectUser(String name){
		return (User)TemplateDB.templateQuery(User.class, name);
	}
}
