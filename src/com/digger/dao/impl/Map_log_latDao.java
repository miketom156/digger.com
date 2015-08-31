package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IMap_log_latDao;
import com.digger.model.Map_log_lat;
import com.digger.model.Pageinfo;
import com.digger.util.TemplateDB;

public class Map_log_latDao implements IMap_log_latDao{
	
	public Map_log_lat selectMap(String name){
		return (Map_log_lat)TemplateDB.templateQuery(Map_log_lat.class, name);
	}
	
	public void savaMap(Map_log_lat map_log_lat){
		TemplateDB.templateSave(map_log_lat);
	}
	
	public void deleteMap(Map_log_lat map_log_lat){
		TemplateDB.templateDelete(map_log_lat);
	}
	
	public void updateMap(Map_log_lat map_log_lat){
		TemplateDB.templateUpdate(map_log_lat);
	}
	
	public int count(String sql)
	{
		return (Integer)TemplateDB.queryCountRecord(Map_log_lat.class, sql);
	}
	
	public Map_log_lat selectId(int id){
		return (Map_log_lat)TemplateDB.template_Query(Map_log_lat.class, id);
	}
	
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAllmap(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Map_log_lat> map=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by ranks asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Map_log_lat.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			map=TemplateDB.TemplateQuery(Map_log_lat.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(map);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
}
