package com.digger.service.impl;

import com.digger.dao.IUserDao;
import com.digger.dao.impl.UserDao;
import com.digger.model.User;
import com.digger.service.IUserService;

public  class UserService implements IUserService {
	
	private IUserDao useDao=null;
	
	public UserService(){
		useDao=new UserDao();
	}
	
	public User loginUser( String name) {
		// TODO Auto-generated method stub
		return useDao.getUser( name);
	}
	
	public void updateUser(User user){
		useDao.updateUser(user);
	}
	
	public void registUser(User user){
		useDao.registUser(user);
	}

	public User select(String name){
		return useDao.selectUser(name);
	}
}
