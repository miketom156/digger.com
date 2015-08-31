package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IRoleDao;
import com.digger.model.Pageinfo;
import com.digger.model.Role;
import com.digger.util.TemplateDB;

public class RoleDao implements IRoleDao {

	public void saveRole(Role role)
	{
		TemplateDB.templateSave(role);
	}
	
	public void updateRole(Role role){
		TemplateDB.templateUpdate(role);
	}
	
	public void deleteRole(Role role){
		TemplateDB.templateDelete(role);
	}
	
	public Role selectRole(int Id){
		return (Role) TemplateDB.template_Query(Role.class, Id);
	}
	
	public Role selectRoleID(String sql){
		return (Role) TemplateDB.templateQuery(Role.class, sql);
	}
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAllrole(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Role> role=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by role_id asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Role.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			role=TemplateDB.TemplateQuery(Role.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(role);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
}
