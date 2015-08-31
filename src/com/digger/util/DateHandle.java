package com.digger.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandle {
	public static long addYear(long time,int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		//calendar.setTime(new Date(time));
		calendar.add(Calendar.YEAR, amount);
		return calendar.getTimeInMillis();
	}
	
	public static String format(long time){
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}
	
	public static int compareTo(long time){
		return new Date(time).compareTo(new Date());
	}

	public static int getDays(long time) {
		long current = new Date().getTime();//获得系统当前时间
		if(current - time > 0) return (int)(current - time)/(1000*60*60*24);//获得过期天数
		else return 0;//未过期
	}
	
	
}