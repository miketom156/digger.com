package com.digger.action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.DateMaintain;
import com.digger.model.Pageinfo;
import com.digger.model.showTables;
import com.digger.service.IDateMaintainService;
import com.digger.service.impl.DateMaintainService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DateMaintainAction extends ActionSupport {
	private DateMaintain dateMaintain;
	private IDateMaintainService dateMaintainService;
	private String path="E:/digger.com/digger/";
	private String dot=".sql";
	
	public DateMaintainAction(){
		dateMaintainService=new DateMaintainService();
	}

	public void setDateMaintain(DateMaintain dateMaintain) {
		this.dateMaintain = dateMaintain;
	}

	public DateMaintain getDateMaintain() {
		return dateMaintain;
	}
	public String showTables()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		ArrayList<showTables> tables=dateMaintainService.showtables();
		session.setAttribute("table", tables);
		return SUCCESS;
	}
	public String backup()throws Exception{
		String mpath;
		String name=dateMaintain.getDate_name();
		String mysql=" where date_name='"+dateMaintain.getDate_name()+dot+"'";
		String sql="select * into outfile '";
		String from=" ' from ";
		mpath=path+dateMaintain.getDate_name();
		mpath+=dot;
		sql+=mpath;
		from+=dateMaintain.getTable_name();
		sql+=from;
		System.out.println(sql);
		java.util.Date mydate = new Date(System.currentTimeMillis());
		java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
		name+=dot;
		dateMaintain.setDate_name(name);
		dateMaintain.setDate_path(mpath);
		dateMaintain.setDate_time(sqlDate);
		DateMaintain date=dateMaintainService.selectDateMaintain(mysql);
		if(date!=null){
			System.out.println(sql);
			addFieldError("*", "数据库备份名存在，请重新输入！");
			return ERROR;
		}else{
			dateMaintainService.backup(sql);
			dateMaintainService.saveDateMaintain(dateMaintain);	
			addFieldError("*", "备份成功!");
			return SUCCESS;
		}
		
	}
	public String selectDateAll()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得当前页和每页的记录数
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql=" ";
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
		Pageinfo pageinfo=dateMaintainService.queryAlldate(v_currPage, v_pageSize, sql);
		request.setAttribute("dateinfo", pageinfo);
		ArrayList<showTables> tables=dateMaintainService.showtables();
		request.setAttribute("table", tables);
		return SUCCESS;
	}
	public String selectdate()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得当前页和每页的记录数
		request.getSession().removeAttribute("flag");
		String table=dateMaintain.getTable_name();
		Pageinfo pageinfo=null;
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql;
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
		
			sql=" where table_name='"+table+"'";
			pageinfo=dateMaintainService.queryAlldate(v_currPage, v_pageSize, sql);
		ArrayList<showTables> tables=dateMaintainService.showtables();
		request.setAttribute("table", tables);
		request.setAttribute("dateinfo", pageinfo);
		return SUCCESS;
	}
	public String recovery() throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		String sql=" load data infile '";
		String mysql="' into table ";
		boolean flag=false;
		sql+=dateMaintain.getDate_path();
		mysql+=dateMaintain.getTable_name();
		sql+=mysql;
		System.out.println(sql);
		flag=dateMaintainService.backup(sql);
		System.out.println(flag);
		if(flag==true){
			session.setAttribute("flag", flag);
			return ERROR;
		}else{
		session.setAttribute("flag", flag);
		}
		return SUCCESS;
	}

}
