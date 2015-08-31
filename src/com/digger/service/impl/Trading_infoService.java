package com.digger.service.impl;

import com.digger.dao.impl.Trading_infoDao;
import com.digger.model.Pageinfo;
import com.digger.model.Trading_info;
import com.digger.service.ITrading_infoService;

public class Trading_infoService implements ITrading_infoService {

	private Trading_infoDao tradingDao=null;
	public Trading_infoService(){
		tradingDao=new Trading_infoDao();
	}
	public Pageinfo queryAlltrading(int currentPage,int pageSize,String countSql){
		return tradingDao.queryAlltrading(currentPage, pageSize, countSql);
	}
	public Trading_info selectTradingId(int ID){
		return tradingDao.selectTradingId(ID);
	}
	public void updateTrading(Trading_info trading_info){
		tradingDao.updateTrading(trading_info);
	}
	public Trading_info selectTrading(String sql){
		return tradingDao.selectTrading(sql);
	}
}
