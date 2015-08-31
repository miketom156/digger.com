package com.digger.service;

import com.digger.model.User_info;

public interface IUser_infoService {

	public void regist (User_info user_info);
	
	public User_info select(int user_id);
	
	public void update(User_info user_info);
}
