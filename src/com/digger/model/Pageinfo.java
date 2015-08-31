package com.digger.model;

import java.util.ArrayList;

public class Pageinfo {
	//当前页
	private int currentPage=1;
	//每页记录数
	private int pageSize=3;
	//总记录数
	private int totalCount;
	//总页数
	private int totalPage;
	//当前页的结果集

	@SuppressWarnings("unchecked")
	private ArrayList lstResult;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@SuppressWarnings("unchecked")
	public ArrayList getLstResult() {
		return lstResult;
	}
	@SuppressWarnings("unchecked")
	public void setLstResult(ArrayList lstResult) {
		this.lstResult = lstResult;
	}
	
	
	
}