package com.digger.model;

public class Recharge {

	private int recharge_id;
	private Double money;
	private String recharge;
	public void setRecharge_id(int recharge_id) {
		this.recharge_id = recharge_id;
	}
	public int getRecharge_id() {
		return recharge_id;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMoney() {
		return money;
	}
	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}
	public String getRecharge() {
		return recharge;
	}
	public String toString(){
		return this.recharge_id+":"+this.money+":"+this.recharge;
	}
}
