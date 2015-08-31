package com.digger.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class DBUtil {

	/**
	 * DRIVER 数据库驱动名称
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * URL 数据库访问地址
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/digger";
	/**
	 * USER 数据库访问用户名
	 */
	private static final String USER = "root";
	/**
	 * PWD 数据库访问密码
	 */
	private static final String PWD = "g";
	
	/**
	 * 私有化DBUtil类对象，并将其初始化
	 */
	private static DBUtil instance = null;
	
	/**
	 * 静态块加载数据库驱动
	 */
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得单例的数据库链接
	 * @return 数据库链接
	 * @throws SQLException 抛出数据库链接异常
	 */
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 获得单例的DBUtil对象
	 * @return 单例的DBUtil对象
	 */
	public static DBUtil getInstance(){
		if(instance == null){
			synchronized(DBUtil.class){
				if(instance == null){
					instance = new DBUtil();
				}
			}
		}
		return instance;
	}

}
