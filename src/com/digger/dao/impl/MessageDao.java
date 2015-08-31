package com.digger.dao.impl;

import java.util.ArrayList;

import com.digger.dao.IMessageDao;
import com.digger.model.Message;
import com.digger.model.Pageinfo;
import com.digger.util.TemplateDB;

public class MessageDao implements IMessageDao {
	//查询信息
	@SuppressWarnings("unchecked")
	public Pageinfo queryAllmes(int currentPage,int pageSize,String countSql)
	{
		Pageinfo pageinfo=new Pageinfo();
		pageinfo.setCurrentPage(currentPage);
		pageinfo.setPageSize(pageSize);
		
		int totalCount=0;
		int totalPage=0;
		ArrayList<Message> mas=new ArrayList();
		Object values[]={(currentPage-1)*pageSize,pageSize};
		String sql=" order by mes_id desc limit ?,?";
		String mySql;
		mySql=countSql+sql;
		try {
			//求总记录数
			totalCount=TemplateDB.queryCountRecord(Message.class, countSql);
			//求总页数
			totalPage=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize)+1;
			
			mas=TemplateDB.TemplateQuery(Message.class,mySql,values);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageinfo.setLstResult(mas);
		pageinfo.setTotalCount(totalCount);
		pageinfo.setTotalPage(totalPage);
		return pageinfo;
	}
	public int messageCount(String sql){
		return (Integer)TemplateDB.queryCountRecord(Message.class, sql);
	}
	public Message selectMessage(int ID){
		return (Message) TemplateDB.template_Query(Message.class, ID);
	}
	public void saveMessage(Message message){
		TemplateDB.templateSave(message);
	}
	public void deleteMessage(Message message){
		TemplateDB.templateDelete(message);
	}
	public Message select(String sql){
		return (Message) TemplateDB.templateQuery(Message.class, sql);
	}
	public void updateMessage(Message message){
		TemplateDB.templateUpdate(message);
	}

}
