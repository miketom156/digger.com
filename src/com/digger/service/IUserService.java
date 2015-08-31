package com.digger.service;

import com.digger.model.User;

public interface IUserService {
 
	public User loginUser(String name);
	
	public void updateUser(User user);
	
	public void registUser(User user);
	
	public User select(String name);
}
