package com.digger.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.digger.model.Map_log_lat;
import com.digger.service.IMap_log_latService;
import com.digger.service.impl.Map_log_latService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MapAction extends ActionSupport{
	private Map_log_lat map_log_lat;
	private IMap_log_latService mapService;
	public MapAction(){
		mapService=new Map_log_latService();
	}
	
	 public String savaMap()throws Exception{
	    	mapService.savaMap(map_log_lat);
	    	addFieldError("message", "±£´æ³É¹¦£¡");
	    	return "input";
	    }
	  public String updateMap()throws Exception{
	    	HttpSession session=ServletActionContext.getRequest().getSession();
	    	Map_log_lat map=(Map_log_lat)session.getAttribute("map_Id");
	    	System.out.println();
	    	map.setPlace_name(map_log_lat.getPlace_name());
	    	map.setMap_longitude(map_log_lat.getMap_longitude());
	    	map.setMap_latitude(map_log_lat.getMap_latitude());
	    	map.setAddress(map_log_lat.getAddress());
	    	map.setRanks(map_log_lat.getRanks());
	    	map.setType(map_log_lat.getType());
	    	map.setLast_place(map_log_lat.getLast_place());
	    	mapService.updateMap(map);
	    	return SUCCESS;
	    }


	public void setMap_log_lat(Map_log_lat map_log_lat) {
		this.map_log_lat = map_log_lat;
	}

	public Map_log_lat getMap_log_lat() {
		return map_log_lat;
	}

}
