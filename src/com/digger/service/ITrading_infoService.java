package com.digger.service;

import com.digger.model.Pageinfo;
import com.digger.model.Trading_info;

public interface ITrading_infoService {

	public Pageinfo queryAlltrading(int currentPage,int pageSize,String countSql);
	public Trading_info selectTradingId(int ID);
	public void updateTrading(Trading_info trading_info);
	public Trading_info selectTrading(String sql);
}
