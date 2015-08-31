package com.digger.model;

import java.util.Date;

public class DateMaintain {

	private int date_id;
	private String date_name;
	private String table_name;
	private String date_path;
	private Date date_time;
	public void setDate_id(int date_id) {
		this.date_id = date_id;
	}
	public int getDate_id() {
		return date_id;
	}
	public void setDate_name(String date_name) {
		this.date_name = date_name;
	}
	public String getDate_name() {
		return date_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setDate_path(String date_path) {
		this.date_path = date_path;
	}
	public String getDate_path() {
		return date_path;
	}
	public String toString()
	{
		return this.date_id+":"+this.date_name+":"+this.table_name+":"+this.date_path+":"+this.date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	public Date getDate_time() {
		return date_time;
	}
	
}
