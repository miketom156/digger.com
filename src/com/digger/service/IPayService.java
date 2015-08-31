package com.digger.service;

import com.digger.model.Pay;

public interface IPayService {

	public Pay selectPay(int ID);
	public void savePay(Pay pay);
	public void updatePay(Pay pay);
}
