package com.digger.dao;

import java.util.ArrayList;

import com.digger.model.DateMaintain;
import com.digger.model.Pageinfo;
import com.digger.model.showTables;

public interface IDateMaintainDao {

	public ArrayList<showTables> showtables();
	public boolean backup(String sql);
	public void saveDateMaintain(DateMaintain dateMaintain);
	public DateMaintain selectDateMaintain(String sql);
	public Pageinfo queryAlldate(int currentPage,int pageSize,String countSql);
}
