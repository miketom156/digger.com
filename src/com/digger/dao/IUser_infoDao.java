package com.digger.dao;

import com.digger.model.User_info;
public interface IUser_infoDao {

	public void registUser_info(User_info user_info);
	
	public User_info selectUser_info(int user_id);
	
	public void updateUser_info(User_info user_info);
	
}
