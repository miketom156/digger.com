package com.digger.service;

import java.util.ArrayList;

import com.digger.model.DateMaintain;
import com.digger.model.Pageinfo;
import com.digger.model.showTables;

public interface IDateMaintainService {

	public ArrayList<showTables> showtables();
	public boolean backup(String sql);
	public void saveDateMaintain(DateMaintain dateMaintain);
	public DateMaintain selectDateMaintain(String sql);
	public Pageinfo queryAlldate(int currentPage,int pageSize,String countSql);
	
}
