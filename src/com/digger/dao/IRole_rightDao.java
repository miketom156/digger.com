package com.digger.dao;

import com.digger.model.Pageinfo;
import com.digger.model.Role_right;

public interface IRole_rightDao {

	public void savaRole_right(Role_right right);
	
	public void updateRole_right(Role_right right);
	
	public void deleteRole_right(Role_right right);
	
	public Role_right seleteRole_right(String sql);
	
	public Pageinfo queryAllright(int currentPage,int pageSize,String countSql);
	
	public Role_right selectRole_rightId(int ID);
}
