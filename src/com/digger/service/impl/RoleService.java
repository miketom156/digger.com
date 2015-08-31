package com.digger.service.impl;

import java.util.ArrayList;

import com.digger.dao.impl.RoleDao;
import com.digger.model.Pageinfo;
import com.digger.model.Role;
import com.digger.service.IRoleService;
import com.digger.util.TemplateDB;

public class RoleService implements IRoleService {
	
	private RoleDao roleDao=null;
	
	public RoleService(){
		roleDao=new RoleDao();
	}
	public void saveRole(Role role){
		roleDao.saveRole(role);
	}
	
	public void updateRole(Role role){
		roleDao.updateRole(role);
	}
	
	public void deleteRole(Role role){
		roleDao.deleteRole(role);
	}
	
	public Role selectRole(int Id){
		return roleDao.selectRole(Id);
	}

	public Pageinfo queryAllrole(int currentPage,int pageSize,String sql){
		return roleDao.queryAllrole(currentPage, pageSize, sql);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Role> getRole(String sql){
		return (ArrayList<Role>)TemplateDB.TemplateQuery(Role.class, sql, null);
	}
	
	public Role selectRoleID(String sql){
		return roleDao.selectRoleID(sql);
	}

}
