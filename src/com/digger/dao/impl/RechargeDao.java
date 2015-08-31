package com.digger.dao.impl;

import com.digger.dao.IRechargeDao;
import com.digger.model.Recharge;
import com.digger.util.TemplateDB;

public class RechargeDao implements IRechargeDao {
	
	public void saveRecharge(Recharge recharge){
		TemplateDB.templateSave(recharge);
	}
	public void deleteRecharge(Recharge recharge){
		TemplateDB.templateDelete(recharge);
	}
	public Recharge selectRecharge(String sql){
		return (Recharge) TemplateDB.templateQuery(Recharge.class, sql);
	}

}
