package com.digger.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class DBUtil {

	/**
	 * DRIVER ���ݿ���������
	 */
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	/**
	 * URL ���ݿ���ʵ�ַ
	 */
	private static final String URL = "jdbc:mysql://localhost:3306/digger";
	/**
	 * USER ���ݿ�����û���
	 */
	private static final String USER = "root";
	/**
	 * PWD ���ݿ��������
	 */
	private static final String PWD = "g";
	
	/**
	 * ˽�л�DBUtil����󣬲������ʼ��
	 */
	private static DBUtil instance = null;
	
	/**
	 * ��̬��������ݿ�����
	 */
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��õ��������ݿ�����
	 * @return ���ݿ�����
	 * @throws SQLException �׳����ݿ������쳣
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
	 * ��õ�����DBUtil����
	 * @return ������DBUtil����
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
