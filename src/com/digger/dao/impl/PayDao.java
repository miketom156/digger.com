package com.digger.dao.impl;

import com.digger.dao.IPayDao;
import com.digger.model.Pay;
import com.digger.util.TemplateDB;

public class PayDao implements IPayDao {

	public Pay selectPay(int ID){
		return (Pay) TemplateDB.template_Query(Pay.class, ID);
	}
	public void savePay(Pay pay){
		TemplateDB.templateSave(pay);
	}
	public void updatePay(Pay pay){
		TemplateDB.templateUpdate(pay);
	}
}
