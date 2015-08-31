package com.digger.service.impl;

import java.util.ArrayList;

import com.digger.dao.impl.MenuDao;
import com.digger.model.Menu;
import com.digger.model.Pageinfo;
import com.digger.service.IMenuService;
import com.digger.util.TemplateDB;

public class MenuService implements IMenuService{

	private MenuDao menuDao=null;
	
	public MenuService(){
		menuDao=new MenuDao();
	}
	
	public Menu selectMenu(String sql){
		return menuDao.selectMenu(sql);
	}
	
	public Menu selectMenuID(int ID){
		return menuDao.selectMenuID(ID);
	}
	
	public void savaMenu(Menu menu){
		menuDao.savaMenu(menu);
	}
	
	public void updateMenu(Menu menu){
		menuDao.updateMenu(menu);
	}
	
	public void deleteMenu(Menu menu){
		menuDao.deleteMenu(menu);
	}
	
	public Pageinfo queryAllmenu(int currentPage,int pageSize,String countSql){
		return menuDao.queryAllmenu(currentPage, pageSize, countSql);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Menu> getMenu(String sql){
		return (ArrayList<Menu>)TemplateDB.TemplateQuery(Menu.class, sql, null);
	}
	
	public ArrayList<Menu> queryAllRightByUID(int ID){
		return menuDao.queryAllRightByUID(ID);
	}
}
