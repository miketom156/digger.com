package com.digger.model;

public class Pay {

	private int user_id;
	private String payword;
	private Double money;
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setPayword(String payword) {
		this.payword = payword;
	}
	public String getPayword() {
		return payword;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMoney() {
		return money;
	}
	public String  toString(){
		return this.user_id+":"+this.payword+":"+this.money;
	}
}
