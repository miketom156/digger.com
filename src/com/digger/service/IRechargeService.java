package com.digger.service;

import com.digger.model.Recharge;

public interface IRechargeService {

	public void saveRecharge(Recharge recharge);
	public void deleteRecharge(Recharge recharge);
	public Recharge selectRecharge(String sql);
}
