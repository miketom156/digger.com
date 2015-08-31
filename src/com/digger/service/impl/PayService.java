package com.digger.service.impl;

import com.digger.dao.impl.PayDao;
import com.digger.model.Pay;
import com.digger.service.IPayService;

public class PayService implements IPayService {

	private PayDao payDao=null;
	public PayService(){
		payDao=new PayDao();
	}
	public Pay selectPay(int ID){
		return payDao.selectPay(ID);
	}
	public void savePay(Pay pay){
		payDao.savePay(pay);
	}
	public void updatePay(Pay pay){
		payDao.updatePay(pay);
	}
}
