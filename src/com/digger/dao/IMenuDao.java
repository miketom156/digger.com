package com.digger.dao;

import java.util.ArrayList;

import com.digger.model.Menu;
import com.digger.model.Pageinfo;

public interface IMenuDao {

	public Menu selectMenu(String sql);
	
	public Menu selectMenuID(int ID);
	
	public void savaMenu(Menu menu);
	
	public void updateMenu(Menu menu);
	
	public void deleteMenu(Menu menu);
	
	public Pageinfo queryAllmenu(int currentPage,int pageSize,String countSql);
	
	public ArrayList<Menu> queryAllRightByUID(int ID);
}
