package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IDateMaintainDao;
import com.digger.model.DateMaintain;
import com.digger.model.Pageinfo;
import com.digger.model.showTables;
import com.digger.util.TemplateDB;

public class DateMaintainDao implements IDateMaintainDao {

	@SuppressWarnings("unchecked")
	public ArrayList<showTables> showtables(){
		return (ArrayList<showTables>)TemplateDB.executeData();
	}
	public boolean backup(String sql){
		return TemplateDB.execute(sql);
	}
	public void saveDateMaintain(DateMaintain dateMaintain){
		TemplateDB.templateSave(dateMaintain);
	}
	public DateMaintain selectDateMaintain(String sql){
		return (DateMaintain) TemplateDB.templateQuery(DateMaintain.class, sql);
	}
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAlldate(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<DateMaintain> date=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by date_id asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(DateMaintain.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			date=TemplateDB.TemplateQuery(DateMaintain.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(date);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
	
}
