package com.digger.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Message;
import com.digger.model.Pageinfo;
import com.digger.model.User;
import com.digger.service.IMessageService;
import com.digger.service.IUserService;
import com.digger.service.impl.MessageService;
import com.digger.service.impl.UserService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MessageAction extends ActionSupport {
	
	private Message message;
	private IMessageService messageService;
	private String send;
	private IUserService userService;
	private int[] mesID;
	public void setSend(String send) {
		this.send = send;
	}
	public String getSend() {
		return send;
	}
	
	public MessageAction(){
		messageService=new MessageService();
		userService=new UserService();
	}
	public void setMessage(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}
	
	public String selectmesinfo()throws Exception{
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.removeAttribute("mescount");
		User u=(User) session.getAttribute("user");
		Message mes=messageService.selectMessage(message.getMes_id());
		session.setAttribute("message",mes);
		mes.setIsn_read(1);
		messageService.updateMessage(mes);
		String mysql=" where isn_read=0 and users="+u.getUser_id();
		int count=messageService.messageCount(mysql);
		session.setAttribute("mescount", count);
		return SUCCESS;
	}
	public String sendMessage() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		Message mes=new Message();
		String sql;
		java.util.Date mydate = new Date(System.currentTimeMillis());
		java.sql.Date sqlDate = new java.sql.Date(mydate.getTime());
		if(getSend().equals("")){
			request.setAttribute("error", "收信人不能为空");
			return ERROR;
		}else{
			if(message.getTitle().equals("")){
				request.setAttribute("error", "主题不能为空");
				return ERROR;
			}else{
				sql=" where user_name='"+getSend()+"'";
				User u=userService.select(sql);
				if(u==null){
					request.setAttribute("error", "收信人不存在");
					return ERROR;
				}else{
					message.setSend(u.getUser_id());
					message.setMes_time(sqlDate);
					message.setUsers(user.getUser_id());
					message.setIsn_read(1);
					messageService.saveMessage(message);
					mes.setUsers(u.getUser_id());
					mes.setSend(user.getUser_id());
					mes.setTitle(message.getTitle());
					mes.setMes_time(sqlDate);
					mes.setContent(message.getContent());
					mes.setIsn_read(0);
					mes.setType("receiving");
					messageService.saveMessage(mes);
					addFieldError("提示信息", "送信成功");
					return SUCCESS;	
				}
			}
		}
	}
	public String selectMesAll()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql=" where users="+user.getUser_id()+" and type='receiving'";
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
		Pageinfo pageinfo=messageService.queryAllmes(v_currPage, v_pageSize, sql);
		request.setAttribute("mesinfo", pageinfo);
		return SUCCESS;
	}
	public String selectMes()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
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
		sql=" where isn_read="+message.getIsn_read()+" and users="+user.getUser_id()+" and type='"+message.getType()+"'";
		Pageinfo pageinfo=messageService.queryAllmes(v_currPage, v_pageSize, sql);
		request.setAttribute("mesinfo", pageinfo);
		return SUCCESS;
	}
	public String selectMesSend()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("user");
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		int v_currPage = 0;
		int v_pageSize = 0;
		String sql=" where users="+user.getUser_id()+" and type='send'";
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
		Pageinfo pageinfo=messageService.queryAllmes(v_currPage, v_pageSize, sql);
		request.setAttribute("mesinfo", pageinfo);
		return SUCCESS;
	}
	public String deleteMessage() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		for(int i=0;i<getMesID().length;i++){
			Message m=messageService.selectMessage(getMesID()[i]);
			messageService.deleteMessage(m);
		}
		request.setAttribute("error", "信件删除成功！");
		return SUCCESS;
	}
	public String deletemes()throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		for(int i=0;i<getMesID().length;i++){
			Message m=messageService.selectMessage(getMesID()[i]);
			messageService.deleteMessage(m);
		}
		request.setAttribute("error", "信件删除成功！");
		return SUCCESS;
	}

	public void setMesID(int[] mesID) {
		this.mesID = mesID;
	}
	public int[] getMesID() {
		return mesID;
	}
	
	

}
