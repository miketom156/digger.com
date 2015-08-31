package com.digger.service.impl;

import com.digger.dao.impl.Role_rightDao;
import com.digger.model.Pageinfo;
import com.digger.model.Role_right;
import com.digger.service.IRole_rightSerivce;

public class Role_rightSerivce implements IRole_rightSerivce{

	private Role_rightDao role_rightDao=null;
	
	public Role_rightSerivce(){
		role_rightDao=new Role_rightDao();
	}
	
	public void savaRole_right(Role_right right){
		role_rightDao.savaRole_right(right);
	}
	
	public void updateRole_right(Role_right right){
		role_rightDao.updateRole_right(right);
	}
	
	public void deleteRole_right(Role_right right){
		role_rightDao.deleteRole_right(right);
	}
	
	public Role_right seleteRole_right(String sql){
		return role_rightDao.seleteRole_right(sql);
	}
	
	public Pageinfo queryAllright(int currentPage,int pageSize,String sql){
		return role_rightDao.queryAllright(currentPage, pageSize, sql);
	}
	
	public Role_right selectRole_rightId(int ID){
		return role_rightDao.selectRole_rightId(ID);
	}
	
}
