package com.digger.model;

public class Role_right {
	private int right_id;
	private int role;
	private int menu;
	
	public void setMenu(int menu) {
		this.menu = menu;
	}
	public int getMenu() {
		return menu;
	}
	public String toString(){
		return this.right_id+":"+this.role+":"+this.menu;
	}
	public void setRight_id(int right_id) {
		this.right_id = right_id;
	}
	public int getRight_id() {
		return right_id;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getRole() {
		return role;
	}

}
