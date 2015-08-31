package com.digger.service.impl;

import com.digger.dao.impl.RechargeDao;
import com.digger.model.Recharge;
import com.digger.service.IRechargeService;

public class RechargeService implements IRechargeService {

	private RechargeDao rechargeDao=null;
	
	public RechargeService(){
		rechargeDao=new RechargeDao();
	}
	public void saveRecharge(Recharge recharge){
		rechargeDao.saveRecharge(recharge);
	}
	public void deleteRecharge(Recharge recharge){
		rechargeDao.deleteRecharge(recharge);
	}
	public Recharge selectRecharge(String sql){
		return rechargeDao.selectRecharge(sql);
	}
}
