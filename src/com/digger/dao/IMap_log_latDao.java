package com.digger.dao;

import com.digger.model.Map_log_lat;
import com.digger.model.Pageinfo;

public interface IMap_log_latDao {
	
	public Map_log_lat selectMap(String name);
	
	public void savaMap(Map_log_lat map_log_lat);
	
	public void deleteMap(Map_log_lat map_log_lat);
	
	public void updateMap(Map_log_lat map_log_lat);
	
	public Pageinfo queryAllmap(int currentPage,int pageSize,String countSql);
	
	public int count(String sql);

	public Map_log_lat selectId(int id);
}
