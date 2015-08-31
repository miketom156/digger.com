package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IRole_rightDao;
import com.digger.model.Pageinfo;
import com.digger.model.Role_right;
import com.digger.util.TemplateDB;

public class Role_rightDao implements IRole_rightDao{

	public void savaRole_right(Role_right right){
		TemplateDB.templateSave(right);
	}
	
	public void updateRole_right(Role_right right){
		TemplateDB.templateUpdate(right);
	}
	
	public void deleteRole_right(Role_right right){
		TemplateDB.templateDelete(right);
	}
	
	public Role_right seleteRole_right(String sql){
		return (Role_right) TemplateDB.templateQuery(Role_right.class, sql);
	}
	public Role_right selectRole_rightId(int ID){
		return (Role_right) TemplateDB.template_Query(Role_right.class, ID);
	}
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAllright(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Role_right> right=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by right_id asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Role_right.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			right=TemplateDB.TemplateQuery(Role_right.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(right);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
}
