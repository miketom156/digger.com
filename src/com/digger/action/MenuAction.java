package com.digger.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Menu;
import com.digger.model.Pageinfo;
import com.digger.service.IMenuService;
import com.digger.service.impl.MenuService;
import com.digger.util.ConvertCharacter;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MenuAction extends ActionSupport {
	
	private Menu menu;
	private IMenuService menuService;
	private int[] menuId;
	
	public MenuAction(){
		menuService= new MenuService();
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}
	public String savaMenu()throws Exception{
		if(menu.getMenu_name().equals(""))
		{
			addFieldError("*", "菜单名不能为空！");
			return ERROR;
		}
		addFieldError("*", "保存成功！");
		menuService.savaMenu(menu);
		return SUCCESS;
	}
	public String selectMenuId()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("menu");
		String sql;
		sql=" where menu_id="+menu.getMenu_id();
		Menu m=menuService.selectMenu(sql);
		session.setAttribute("menu", m);
		return SUCCESS;
	}
	public String selectMenuInfo()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("menu");
		String sql;
		sql=" where menu_id="+menu.getMenu_id();
		Menu m=menuService.selectMenu(sql);
		session.setAttribute("menu", m);
		return SUCCESS;
	}
	public String updateMenu()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Menu m=(Menu) session.getAttribute("menu");
		System.out.println(menu.getMenu_name());
		String name=new ConvertCharacter().Convert(menu.getMenu_name());
		if(menu.getMenu_name().equals(""))
		{
			addFieldError("*", "菜单名不能为空！");
			return ERROR;
		}else{
			m.setMenu_name(name);
			m.setParent(menu.getParent());
			m.setUrl(menu.getUrl());
			m.setMenu_type(menu.getMenu_type());
			menuService.updateMenu(m);
		}
		return SUCCESS;
	}
	public String deleteMenu() throws Exception{
		String sql;
		sql=" where menu_id="+menu.getMenu_id();
		System.out.println(sql);
		Menu m=menuService.selectMenu(sql);
		menuService.deleteMenu(m);
		return SUCCESS;
	}
	public String deleteMenuAll()throws Exception{
		String sql;
		for(int i=0;i<getMenuId().length;i++){
			sql=" where menu_id="+getMenuId()[i];
			Menu m=menuService.selectMenu(sql);
			menuService.deleteMenu(m);
		}
		return "deleteMenuAll";
	}
	public String selectMenuAll()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=(String) request.getSession().getAttribute("menus");
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql;
		Pageinfo pageinfo=new Pageinfo();
		if (currentPage == null) {
			v_currPage = 1;
		} else {
			v_currPage = Integer.parseInt(currentPage);
		}
		if (pageSize == null) {
			v_pageSize = 3;
		} else {
			v_pageSize = Integer.parseInt(pageSize);
		}
		if(name==null){
			sql=" ";
			pageinfo=menuService.queryAllmenu(v_currPage, v_pageSize, sql);
		}else{
			if(name.equals("")){
				sql=" ";
				pageinfo=menuService.queryAllmenu(v_currPage, v_pageSize, sql);
			}else{
				sql=" where menu_name like'%"+name+"%'";
				pageinfo=menuService.queryAllmenu(v_currPage, v_pageSize, sql);
			}
		}
		request.setAttribute("menuinfo", pageinfo);
		return SUCCESS;
	}
	public String selectMenu()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String sql;
		String name=menu.getMenu_name();
		Pageinfo pageinfo=null;
		if(name.equals("")){
			sql=" ";
			pageinfo=menuService.queryAllmenu(1, 3, sql);
		}
		else{
			sql=" where menu_name like'%"+name+"%'";
			pageinfo=menuService.queryAllmenu(1, 3, sql);
		}
		request.setAttribute("menuinfo", pageinfo);
		request.getSession().setAttribute("menus", name);
		return "selectMenu";
	}

	public void setMenuId(int[] menuId) {
		this.menuId = menuId;
	}

	public int[] getMenuId() {
		return menuId;
	}
}
