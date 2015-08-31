package com.digger.service.impl;

import com.digger.dao.impl.MessageDao;
import com.digger.model.Message;
import com.digger.model.Pageinfo;
import com.digger.service.IMessageService;

public class MessageService implements IMessageService {

	private MessageDao messageDao=null;
	public MessageService(){
		messageDao=new MessageDao();
	}
	public Pageinfo queryAllmes(int currentPage,int pageSize,String countSql){
		return messageDao.queryAllmes(currentPage, pageSize, countSql);
	}
	public int messageCount(String sql){
		return messageDao.messageCount(sql);
	}
	public Message selectMessage(int ID){
		return messageDao.selectMessage(ID);
	}
	public void saveMessage(Message message){
		messageDao.saveMessage(message);
	}
	public void deleteMessage(Message message){
		messageDao.deleteMessage(message);
	}
	public Message select(String sql){
		return messageDao.select(sql);
	}
	public void updateMessage(Message message){
		messageDao.updateMessage(message);
	}
}
