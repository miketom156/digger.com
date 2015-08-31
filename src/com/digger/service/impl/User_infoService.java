package com.digger.service.impl;

import com.digger.dao.IUser_infoDao;
import com.digger.dao.impl.User_infoDao;
import com.digger.model.User_info;
import com.digger.service.IUser_infoService;

public class User_infoService implements IUser_infoService {
	
	private IUser_infoDao user_infoDao=null;
	
	public User_infoService(){
		user_infoDao=new User_infoDao();
	}
	
	public void regist (User_info user_info){
		user_infoDao.registUser_info(user_info);
	}
	
	public User_info select(int user_id){
		return user_infoDao.selectUser_info(user_id);
	}
	
	public void update(User_info user_info){
		user_infoDao.updateUser_info(user_info);
	}

}
