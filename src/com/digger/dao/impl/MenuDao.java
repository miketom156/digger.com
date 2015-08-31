package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IMenuDao;
import com.digger.model.Menu;
import com.digger.model.Pageinfo;
import com.digger.util.TemplateDB;

public class MenuDao implements IMenuDao {

	public Menu selectMenu(String sql){
		return (Menu) TemplateDB.templateQuery(Menu.class, sql);
	}
	
	public Menu selectMenuID(int ID){
		return (Menu) TemplateDB.template_Query(Menu.class, ID);
	}
	
	public void savaMenu(Menu menu){
		TemplateDB.templateSave(menu);
	}
	
	public void updateMenu(Menu menu){
		TemplateDB.templateUpdate(menu);
	}
	
	public void deleteMenu(Menu menu){
		TemplateDB.templateDelete(menu);
	}
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAllmenu(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Menu> map=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by menu_id asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Menu.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			map=TemplateDB.TemplateQuery(Menu.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(map);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Menu> queryAllRightByUID(int ID){
		String sql=" m, role_right rr, role r, user u where m.menu_id=rr.menu and rr.role=r.role_id and r.role_id=u.rights and u.user_id="+ID;
		return (ArrayList<Menu>)TemplateDB.TemplateQuery(Menu.class, sql, null);
	}
}
