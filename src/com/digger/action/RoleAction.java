package com.digger.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Pageinfo;
import com.digger.model.Role;
import com.digger.service.IRoleService;
import com.digger.service.impl.RoleService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RoleAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	@Override
	public void validate() {
		super.validate();
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	private Role role;
	private IRoleService roleService;
	private int[]roleId;
	
	public RoleAction(){
		roleService=new RoleService();
	}
	
	public void setRoleId(int[] roleId) {
		this.roleId = roleId;
	}

	public int[] getRoleId() {
		return roleId;
	}
	
	public String saveRole() throws Exception{
		if(role.getRole_name().equals("")){
			addFieldError("*", "角色名不能为空");
			return ERROR;
		}
		roleService.saveRole(role);
		addFieldError("*", "保存成功！");
		return SUCCESS;
	}
	
	public String selectId()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Role r=roleService.selectRole(role.getRole_id());
		session.setAttribute("role", r);
		return SUCCESS;
	}
	
	public String updateRole()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		Role r=(Role) session.getAttribute("role");
		r.setRole_name(role.getRole_name());
		roleService.updateRole(r);
		return SUCCESS;
	}
	
	public String deleteRole()throws Exception{
		Role r=roleService.selectRole(role.getRole_id());
		roleService.deleteRole(r);
		return SUCCESS;
	}
	
	public String deleteRoleAll()throws Exception{
		for(int i=0;i<getRoleId().length;i++){
			Role r=roleService.selectRole(getRoleId()[i]);
			roleService.deleteRole(r);
		}
		return "deleteRoleAll";
	}
	
	public String selectRoleAll()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=(String) request.getSession().getAttribute("roles");
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
			pageinfo=roleService.queryAllrole(v_currPage, v_pageSize, sql);
		}else{
			if(name.equals("")){
				sql=" ";
				pageinfo=roleService.queryAllrole(v_currPage, v_pageSize, sql);
			}else{
				sql=" where role_name like '%"+name+"%'";
				pageinfo=roleService.queryAllrole(v_currPage, v_pageSize, sql);
			}
		}
		request.setAttribute("roleinfo", pageinfo);
		return SUCCESS;
	}
	
	public String selectRole() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		Pageinfo pageinfo=null;
		String sql;
		String name=role.getRole_name();
		if(name.equals("")){
			sql=" ";
			pageinfo=roleService.queryAllrole(1, 3, sql);
		}else
		{
			sql=" where role_name like '%"+name+"%'";
			pageinfo=roleService.queryAllrole(1, 3, sql);
		}
		request.setAttribute("roleinfo", pageinfo);
		request.getSession().setAttribute("roles", name);
		return "selectRole";
	}
}
