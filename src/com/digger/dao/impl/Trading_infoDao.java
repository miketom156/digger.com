package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.ITrading_infoDao;
import com.digger.model.Pageinfo;
import com.digger.model.Trading_info;
import com.digger.util.TemplateDB;

public class Trading_infoDao implements ITrading_infoDao {

	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAlltrading(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Trading_info> trading=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by pay_id asc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Trading_info.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			trading=TemplateDB.TemplateQuery(Trading_info.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(trading);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
	public Trading_info selectTradingId(int ID){
		return (Trading_info) TemplateDB.template_Query(Trading_info.class, ID);
	}
	public void updateTrading(Trading_info trading_info){
		TemplateDB.templateUpdate(trading_info);
	}
	public Trading_info selectTrading(String sql){
		return (Trading_info) TemplateDB.templateQuery(Trading_info.class, sql);
	}
}
