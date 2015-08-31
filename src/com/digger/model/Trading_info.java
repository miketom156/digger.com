package com.digger.model;

import java.util.Date;

public class Trading_info {

	private int pay_id;
	private int payer;
	private int sellers;
	private Double money;
	private int isN_pay;
	private String trading_info;
	private Date trading_time;
	public void setPay_id(int pay_id) {
		this.pay_id = pay_id;
	}
	public int getPay_id() {
		return pay_id;
	}
	public void setPayer(int payer) {
		this.payer = payer;
	}
	public int getPayer() {
		return payer;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getMoney() {
		return money;
	}
	public void setSellers(int sellers) {
		this.sellers = sellers;
	}
	public int getSellers() {
		return sellers;
	}
	public void setTrading_info(String trading_info) {
		this.trading_info = trading_info;
	}
	public String getTrading_info() {
		return trading_info;
	}
	public String toString(){
		return this.pay_id+":"+this.payer+":"+this.sellers+":"+this.money+":"+this.isN_pay+":"+this.trading_info+":"+this.trading_time;
	}
	public void setTrading_time(Date trading_time) {
		this.trading_time = trading_time;
	}
	public Date getTrading_time() {
		return trading_time;
	}
	public void setIsN_pay(int isN_pay) {
		this.isN_pay = isN_pay;
	}
	public int getIsN_pay() {
		return isN_pay;
	}
}
