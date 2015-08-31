package com.digger.model;

public class User {
	
	private int user_id;
	private String user_name;
	private String user_password;
	private int rights;
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_password() {
		return user_password;
	}
	
	public String toString()
	{
		return this.user_id +":"+this.user_name+":"+this.user_password+":"+this.rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}
	public int getRights() {
		return rights;
	}
	
}
