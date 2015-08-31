package com.digger.model;

import java.util.Date;

public class Message {
	
	private int mes_id;
	private int users;
	private int send;
	private String title;
	private String content;
	private Date mes_time;
	private int isn_read;
	private String type;

	public void setSend(int send) {
		this.send = send;
	}
	public int getSend() {
		return send;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setMes_time(Date mes_time) {
		this.mes_time = mes_time;
	}
	public Date getMes_time() {
		return mes_time;
	}
	public void setIsn_read(int isn_read) {
		this.isn_read = isn_read;
	}
	public int getIsn_read() {
		return isn_read;
	}
	public String toString(){
		return this.mes_id+":"+this.users+":"+this.send+":"+this.title+":"+this.content+":"+this.mes_time+":"+this.isn_read+":"+this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setMes_id(int mes_id) {
		this.mes_id = mes_id;
	}
	public int getMes_id() {
		return mes_id;
	}
	public void setUsers(int users) {
		this.users = users;
	}
	public int getUsers() {
		return users;
	}

}
