package com.digger.model;

import java.util.Date;


public class User_info {
	private int user_id;
	private String user_name;
	private String sex;
	private Date brithday;
	private int age;
	private String natives;
	private String province;
	private String city;
	private int tel;
	private String e_mail;
	private String hoody;
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
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	public void setNatives(String natives) {
		this.natives = natives;
	}
	public String getNatives() {
		return natives;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getTel() {
		return tel;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setHoody(String hoody) {
		this.hoody = hoody;
	}
	public String getHoody() {
		return hoody;
	}
	public String toString(){
		return this.user_id+":"+this.user_name+":"+this.sex+":"+this.brithday+":"+this.age+":"+
		this.natives+":"+this.province+":"+this.city+":"+this.tel+":"+this.e_mail+":"+this.hoody;
	}
	
	

}
