package com.digger.dao;

import com.digger.model.Message;
import com.digger.model.Pageinfo;

public interface IMessageDao {
	public Pageinfo queryAllmes(int currentPage,int pageSize,String countSql);
	public int messageCount(String sql);
	public Message selectMessage(int ID);
	public void saveMessage(Message message);
	public void deleteMessage(Message message);
	public Message select(String sql);
	public void updateMessage(Message message);

}
