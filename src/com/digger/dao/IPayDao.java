package com.digger.dao;

import com.digger.model.Pay;

public interface IPayDao {
	public Pay selectPay(int ID);
	public void savePay(Pay pay);
	public void updatePay(Pay pay);

}
