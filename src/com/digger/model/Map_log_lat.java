package com.digger.model;

public class Map_log_lat {
	
	private int map_id;
	private String place_name;
	private Double map_longitude;
	private Double map_latitude;
	private String address;
	private String type;
	private String last_place;
	private int ranks;
	public void setMap_id(int map_id) {
		this.map_id = map_id;
	}
	public int getMap_id() {
		return map_id;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getPlace_name() {
		return place_name;
	}
	
	public void setMap_latitude(Double map_latitude) {
		this.map_latitude = map_latitude;
	}
	public Double getMap_latitude() {
		return map_latitude;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setLast_place(String last_place) {
		this.last_place = last_place;
	}
	public String getLast_place() {
		return last_place;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public int getRanks() {
		return ranks;
	}
	public String toString() {
		return this.map_id+":"+this.place_name+":"+this.map_longitude+":"+this.map_latitude+":"+this.address
		+":"+this.type+":"+this.last_place+":"+this.ranks;
	}
	public void setMap_longitude(Double map_longitude) {
		this.map_longitude = map_longitude;
	}
	public Double getMap_longitude() {
		return map_longitude;
	}

}
