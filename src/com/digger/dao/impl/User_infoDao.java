package com.digger.dao.impl;

import com.digger.model.User_info;
import com.digger.util.TemplateDB;
import com.digger.dao.IUser_infoDao;

public class User_infoDao implements IUser_infoDao{
	
	public void registUser_info(User_info user_info){
		TemplateDB.templateSave(user_info);
	}
	
	public User_info selectUser_info(int user_id)
	{
		return (User_info)TemplateDB.template_Query(User_info.class, user_id);
	}
	
	public void updateUser_info(User_info user_info){
		TemplateDB.templateUpdate(user_info);
	}
}
