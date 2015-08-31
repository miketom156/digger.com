package com.digger.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Menu;
import com.digger.model.Pageinfo;
import com.digger.model.Role;
import com.digger.model.Role_right;
import com.digger.service.IMenuService;
import com.digger.service.IRoleService;
import com.digger.service.IRole_rightSerivce;
import com.digger.service.impl.MenuService;
import com.digger.service.impl.RoleService;
import com.digger.service.impl.Role_rightSerivce;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Role_rightAction extends ActionSupport {
	
	private Role_right role_right;
	private Role role;
	private Menu menu;
	private IRoleService roleService;
	private IMenuService menuService;
	private IRole_rightSerivce role_rightService;
	private int[] rightId;
	public Role_rightAction(){
		roleService=new RoleService();
		menuService=new MenuService();
		role_rightService=new Role_rightSerivce();
	}
	public void setRole_right(Role_right role_right) {
		this.role_right = role_right;
	}
	public Role_right getRole_right() {
		return role_right;
	}
	public String selectRightInfo()throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		String sql="";
		ArrayList<Role> roles=roleService.getRole(sql);
		ArrayList<Menu> menus=menuService.getMenu(sql);
		request.setAttribute("roles", roles);
		request.setAttribute("menus", menus);
		return SUCCESS;
	}
	public String selectRightId()throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		String sql="";
		ArrayList<Role> roles=roleService.getRole(sql);
		ArrayList<Menu> menus=menuService.getMenu(sql);
		Role_right rr=role_rightService.selectRole_rightId(role_right.getRight_id());
		Role r=roleService.selectRole(rr.getRole());
		Menu m=menuService.selectMenuID(rr.getMenu());
		request.setAttribute("roles", roles);
		request.setAttribute("menus", menus);
		request.setAttribute("r", r);
		request.setAttribute("m", m);
		request.getSession().setAttribute("rr", rr);
		return SUCCESS;
	}
	public String selectRights()throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		Role_right rr=role_rightService.selectRole_rightId(role_right.getRight_id());
		Role r=roleService.selectRole(rr.getRole());
		Menu m=menuService.selectMenuID(rr.getMenu());
		request.setAttribute("r", r);
		request.setAttribute("m", m);
		return SUCCESS;
	}
	public String savaRight()throws Exception{
		String sql;
		sql=" where menu_name='"+menu.getMenu_name()+"'";
		Menu m=menuService.selectMenu(sql);
		sql=" where role_name='"+role.getRole_name()+"'";
		Role r=roleService.selectRoleID(sql);
		Role_right right=new Role_right();
		right.setRole(r.getRole_id());
		right.setMenu(m.getMenu_id());
		role_rightService.savaRole_right(right);
		addFieldError("*", "±£´æ³É¹¦£¡");
		return SUCCESS;
	}
	public String updateRight()throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		Role_right rr=(Role_right) request.getSession().getAttribute("rr");
		System.out.println(rr.getRight_id());
		String sql;
		sql=" where menu_name='"+menu.getMenu_name()+"'";
		Menu m=menuService.selectMenu(sql);
		String sql1=" where role_name='"+role.getRole_name()+"'";
		Role r=roleService.selectRoleID(sql1);
		rr.setRole(r.getRole_id());
		rr.setMenu(m.getMenu_id());
		role_rightService.updateRole_right(rr);
		return SUCCESS;
	}
	public String deleteRight()throws Exception{
		Role_right right=role_rightService.selectRole_rightId(role_right.getRight_id());
		role_rightService.deleteRole_right(right);
		return SUCCESS;
	}
	public String deleteRightAll()throws Exception{
		for(int i=0;i<getRightId().length;i++){
			Role_right right=role_rightService.selectRole_rightId(getRightId()[i]);
			role_rightService.deleteRole_right(right);
		}
		return "deleteRightAll";
	}
	public String selectRightAll()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=(String) request.getSession().getAttribute("rights");
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
		String mysql=" ";
		ArrayList<Role> roles=roleService.getRole(mysql);
		request.setAttribute("roles", roles);
		if(name==null){
			sql=" ";
			pageinfo=role_rightService.queryAllright(v_currPage, v_pageSize, sql);
		}else{
			if(name.equals("")){
				sql=" ";
				pageinfo=role_rightService.queryAllright(v_currPage, v_pageSize, sql);
			}else{
				sql=" where role_name='"+name+"'";
				Role r=roleService.selectRoleID(sql);
				sql=" where role="+r.getRole_id();
				pageinfo=role_rightService.queryAllright(v_currPage, v_pageSize, sql);
			}
		}
		request.setAttribute("rightinfo", pageinfo);
		return SUCCESS;
	}
	public String selectRight()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		Pageinfo pageinfo=null;
		String sql;
		String mysql=" ";
		ArrayList<Role> roles=roleService.getRole(mysql);
		request.setAttribute("roles", roles);
		String name=role.getRole_name();
		if(name.equals("")){
			sql=" ";
			pageinfo=role_rightService.queryAllright(1, 3, sql);
		}
		else{
			sql=" where role_name='"+name+"'";
			Role r=roleService.selectRoleID(sql);
			sql=" where role="+r.getRole_id();
			pageinfo=role_rightService.queryAllright(1, 3, sql);
		}
		request.setAttribute("rightinfo", pageinfo);
		request.getSession().setAttribute("rights", name);
		return "selectRight";
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setRightId(int[] rightId) {
		this.rightId = rightId;
	}
	public int[] getRightId() {
		return rightId;
	}

}
