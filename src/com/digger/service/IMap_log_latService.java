package com.digger.service;

import java.util.ArrayList;

import com.digger.model.Map_log_lat;
import com.digger.model.Pageinfo;


public interface IMap_log_latService {
	
	public Map_log_lat selectMap(String name);
	
	public Map_log_lat selectRanks(String name);
	
	public Map_log_lat selectType(String name);
	
	public void savaMap(Map_log_lat map_log_lat);
	
	public void updateMap(Map_log_lat map_log_lat);
	
	public void deleteMap(Map_log_lat map_log_lat);
	
	public ArrayList<Map_log_lat> getRanks(int ranks );
	
	public ArrayList<Map_log_lat> getType(String mysql );
	
	public Pageinfo queryAllmap(int currentPage ,int pageSize ,String sql);
	
	public int count(String sql);
	
	public Map_log_lat selectId(int id);

}
