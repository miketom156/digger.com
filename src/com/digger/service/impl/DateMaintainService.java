package com.digger.service.impl;

import java.util.ArrayList;

import com.digger.dao.impl.DateMaintainDao;
import com.digger.model.DateMaintain;
import com.digger.model.Pageinfo;
import com.digger.model.showTables;
import com.digger.service.IDateMaintainService;

public class DateMaintainService implements IDateMaintainService {

	private DateMaintainDao dateMaintainDao=null;
	public DateMaintainService(){
		dateMaintainDao=new DateMaintainDao();
	}
	public ArrayList<showTables> showtables(){
		return dateMaintainDao.showtables();
		
	}
	public boolean backup(String sql){
		return dateMaintainDao.backup(sql);
	}
	public void saveDateMaintain(DateMaintain dateMaintain){
		dateMaintainDao.saveDateMaintain(dateMaintain);
	}
	public DateMaintain selectDateMaintain(String sql){
		return dateMaintainDao.selectDateMaintain(sql);
	}
	public Pageinfo queryAlldate(int currentPage,int pageSize,String countSql){
		return dateMaintainDao.queryAlldate(currentPage, pageSize, countSql);
	}
}
