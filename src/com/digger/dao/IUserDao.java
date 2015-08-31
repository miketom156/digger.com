package com.digger.dao;

import com.digger.model.User;


public interface IUserDao {

	//µÇÂ¼
	public User getUser(String name);
	//ĞŞ¸ÄÃÜÂë
	public void updateUser(User user);
	//×¢²á
	public void registUser(User user);
	
	public User selectUser(String name);
}
