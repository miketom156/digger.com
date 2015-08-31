package com.digger.model;

public class Menu {

	private int menu_id;
	private String menu_name;
	private int parent;
	private String url;
	private String menu_type;
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public String toString (){
		return this.menu_id+":"+this.menu_name+":"+this.parent+":"+this.url+":"+this.menu_type;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getParent() {
		return parent;
	}
}
