package com.digger.dao;

import com.digger.model.Pageinfo;
import com.digger.model.Role;

public interface IRoleDao {
	
	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
	public Role selectRole(int Id);
	
	public Role selectRoleID(String sql);

	public Pageinfo queryAllrole(int currentPage,int pageSize,String countSql);
}
