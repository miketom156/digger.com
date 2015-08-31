package com.digger.dao;

import com.digger.model.Recharge;

public interface IRechargeDao {

	public void saveRecharge(Recharge recharge);
	public void deleteRecharge(Recharge recharge);
	public Recharge selectRecharge(String sql);
}
