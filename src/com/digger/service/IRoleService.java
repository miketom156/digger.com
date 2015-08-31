package com.digger.service;

import java.util.ArrayList;

import com.digger.model.Pageinfo;
import com.digger.model.Role;

public interface IRoleService {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
	public Role selectRole(int Id);
	
	public Role selectRoleID(String sql);

	public Pageinfo queryAllrole(int currentPage,int pageSize,String sql);
	
	public ArrayList<Role> getRole(String sql);
}
