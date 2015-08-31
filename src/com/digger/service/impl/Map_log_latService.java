package com.digger.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.digger.dao.IMap_log_latDao;
import com.digger.dao.impl.Map_log_latDao;
import com.digger.model.Map_log_lat;
import com.digger.model.Pageinfo;
import com.digger.service.IMap_log_latService;
import com.digger.util.TemplateDB;

public class Map_log_latService implements IMap_log_latService {
	
	private IMap_log_latDao map_log_latDao=null;
	
	public Map_log_latService(){
		map_log_latDao=new Map_log_latDao();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Map_log_lat> getRanks(int ranks ) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("ranks", ranks);
		return (ArrayList<Map_log_lat>)TemplateDB.templateQuery(Map_log_lat.class, queryParams);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Map_log_lat> getType(String mysql ) {
		return (ArrayList<Map_log_lat>)TemplateDB.TemplateQuery(Map_log_lat.class, mysql,null);
	}
	
	public Map_log_lat selectMap(String name){
		return map_log_latDao.selectMap(name);
	}
	
	public Map_log_lat selectRanks(String name){
		return map_log_latDao.selectMap(name);
	}
	
	public Map_log_lat selectType(String name){
		return map_log_latDao.selectMap(name);
	}
	
	public void savaMap(Map_log_lat map_log_lat){
		map_log_latDao.savaMap(map_log_lat);
	}
	
	public void updateMap(Map_log_lat map_log_lat){
		map_log_latDao.updateMap(map_log_lat);
	}
	
	public void deleteMap(Map_log_lat map_log_lat){
		map_log_latDao.deleteMap(map_log_lat);
	}
	
	public Pageinfo queryAllmap(int currentPage ,int pageSize ,String sql){
		return map_log_latDao.queryAllmap(currentPage, pageSize, sql);
	}
	
	public int count(String sql){
		return map_log_latDao.count(sql);
	}

	public Map_log_lat selectId(int id){
		return map_log_latDao.selectId(id);
	}
}
