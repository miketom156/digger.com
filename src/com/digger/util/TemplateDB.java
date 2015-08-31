package com.digger.util;

import com.digger.model.showTables;
import com.digger.util.DBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TemplateDB {
	/**
	 * 根据object模板插入操作
	 * 
	 * @param object
	 *            需要插入的对象
	 * @return 返回是否执行成功
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateSave(Object object) {
		Class clazz = object.getClass();// 获得object对象的Class
		String classname = clazz.getName();// 获得类名
		String sql = " insert into "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// 执行插入的sql语句
		String columns = "";// 需要插入的列属性子句
		String values = "";// values条件子句
		Field[] fields = clazz.getDeclaredFields();// 获得参数object对象已声明的方法
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// 获得对象域名
			String upperName = name.substring(0, 1).toUpperCase()
					+ name.substring(1);// 对象域名首字母大写
			Object obj = null;
			try {
				obj = clazz.getDeclaredMethod("get" + upperName).invoke(object);// 获得参数object的声明方法getXXX()，调用取得返回值
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (obj != null) {
				if (i > 0) {
					columns += " , ";
					values += " , ";
				}
				columns += name;
				values += "'" + obj.toString() + "' ";
			}
		}
		sql += "(" + columns + " )values( " + values + ")";
		System.out.println("sql : " + sql);
		return executeUpdate(sql);// 返回执行结果
	}

	/**
	 * 根据object模板更新操作
	 * 
	 * @param object
	 *            需要更新的对象
	 * @return 返回是否执行成功
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateUpdate(Object object) {
		Class clazz = object.getClass();// 获得object对象的Class
		String classname = clazz.getName();// 获得类名
		String sql = " update " + classname.substring(classname.lastIndexOf(".") + 1).toLowerCase() + " set ";// 执行更新的sql语句
		String where = "";// where条件子句
		Field[] fields = clazz.getDeclaredFields();// 获得参数object对象已声明的方法
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// 获得对象域名
			String upperName = name.substring(0, 1).toUpperCase()
					+ name.substring(1);// 对象域名首字母大写
			Object obj = null;
			try {
				obj = clazz.getDeclaredMethod("get" + upperName).invoke(object);// 获得参数object的声明方法getXXX()，调用取得返回值
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!name.matches("(.*)id")) {
				// 匹配是否含有id字符的字段
				if(obj != null){
					if (i > 1 && sql.indexOf('=') != -1) sql += " , ";
					sql += name + " = '" + obj.toString() + "' ";
				}
			} else if(i == 0){
				where = " where " + name + " = '" + obj.toString() + "' ";
			}
		}
		sql += where;
		System.out.println("sql : " + sql);
		return executeUpdate(sql);// 返回执行结果
	}

	/**
	 * 根据object模板删除操作
	 * 
	 * @param object
	 *            需要删除的对象
	 * @return 返回是否执行成功
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateDelete(Object object) {
		Class clazz = object.getClass();// 获得object对象的Class
		String classname = clazz.getName();// 获得类名
		String sql = " delete from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase() + " where ";// 执行更新的sql语句
		Field[] fields = clazz.getDeclaredFields();// 获得参数object对象已声明的方法
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// 获得对象域名
			if (name.matches("(.*)id")) {
				// 匹配是否含有id字符的字段
				String upperName = name.substring(0, 1).toUpperCase()
						+ name.substring(1);// 对象域名首字母大写
				Object obj = null;
				try {
					obj = clazz.getDeclaredMethod("get" + upperName).invoke(
							object);// 获得参数object的声明方法getXXX()，调用取得返回值
				} catch (Exception e) {
					e.printStackTrace();
				}
				sql += name + " = '" + obj.toString() + "' ";
			}
		}
		System.out.println("sql : " + sql);
		return executeUpdate(sql);// 返回执行结果
	}

	/**
	 * 取得指定的对象查询的对象
	 * 
	 * @param clazz
	 *            需要查询的类
	 * @param uid
	 *            需要查询指定的对象
	 * @return 返回获得的对象
	 */
	@SuppressWarnings("unchecked")
	public static Object templateQuery(Class clazz, String name) {

		String classname = clazz.getName();// 获得类名
		Object object = null;// 声明接收从数据库取到的数据列表
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// 执行查询的sql语句
		sql+=name;
System.out.println(sql);
		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// 创建语句
			resultSet = statement.executeQuery(sql);// 执行sql语句
			if (resultSet.next()) {
				object = getValueObject(clazz, resultSet);// 获取值对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return object;// 返回获得的对象
	}
	
	@SuppressWarnings("unchecked")
	public static Object template_Query(Class clazz, int uid) {

		String classname = clazz.getName();// 获得类名
		Object object = null;// 声明接收从数据库取到的数据列表
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// 执行查询的sql语句
		Field[] fields = clazz.getDeclaredFields();// 获得参数object对象已声明的方法
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// 获得对象域名
			if (name.matches("(.*)id")) {
				// 匹配是否含有id字符的字段
				sql += " where " + name + " = '" + uid + "'";
			}
		}
		System.out.println(sql);

		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// 创建语句
			resultSet = statement.executeQuery(sql);// 执行sql语句
			if (resultSet.next()) {
				object = getValueObject(clazz, resultSet);// 获取值对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return object;// 返回获得的对象
	}
	
	/**
	 * 取得指定的对象查询的对象集合
	 * @param clazz 需要查询的类
	 * @param queryParams 需要查询指定的对象的参数集合
	 * @return 返回获得的对象集合
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList templateQuery(Class clazz, Map<String, Object> queryParams) {

		String classname = clazz.getName();// 获得类名
		ArrayList list = null;// 声明接收从数据库取到的数据列表
		String sql = " select * from " + classname.substring(classname.lastIndexOf(".") + 1).toLowerCase() + " where ";// 执行查询的sql语句
		String order = "";// 排序的字符串
		String limit = "";// 分页的字符串
		if(queryParams != null){
			Iterator<Map.Entry<String, Object>> i = queryParams.entrySet().iterator();
			while(i.hasNext()){
				Entry<String, Object> e = i.next();
				if(e.getKey() == "order"){
					order = " order by ";
					Map<String,String> map = (Map<String, String>)e.getValue();
					Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
					while(it.hasNext()){
						Entry<String, String> oe = it.next();
						order += oe.getKey() + " " + oe.getValue();
					}
				}else if(e.getKey() == "limit"){
					limit += " limit " + e.getValue();
				}else if(e.getKey() == "like"){
					Map<String,String> map = (Map<String, String>)e.getValue();
					Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
					while(it.hasNext()){
						Entry<String, String> oe = it.next();
						if (sql.indexOf('=') != -1) sql += " , ";
						sql += oe.getKey() + " like '%" + oe.getValue().toString() + "%'";
					}
				}else{
					if (sql.indexOf('=') != -1) sql += " , ";
					sql += e.getKey() + " = '" + e.getValue().toString() + "'";
				}
			}
		}
		sql += order + limit;
		
		System.out.println("sql : " + sql);

		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			list = new ArrayList();//初始化集合列表
			statement = conn.createStatement();// 创建语句
			resultSet = statement.executeQuery(sql);// 执行sql语句
			while (resultSet.next()) {
				list.add(getValueObject(clazz, resultSet));// 获取值对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return list;// 返回获得的对象
	}

	/**
	 * 取得指定的对象查询的对象列表
	 * 
	 * @param clazz
	 *            需要查询的对象
	 * @return 返回获得的对象列表
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList TemplateQuery(Class clazz,String mysql, Object[] values) {
		String classname = clazz.getName();// 获得类名
		ArrayList list = null;// 声明接收从数据库取到的数据列表
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// 执行查询的sql语句
		sql+=mysql;
		System.out.println("sql : " + sql);

		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			if (values != null) {
				for (Object value : values) {
					pstmt.setObject(i++, value);
				}
			}
			resultSet = pstmt.executeQuery();// 执行sql语句
			list = new ArrayList();// 初始化
			while (resultSet.next()) {
				list.add(getValueObject(clazz, resultSet));// 加入值对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(pstmt, conn);
		}
		return list;// 返回获得的结果集
	}

	/**
	 * 获得从数据库取出的值注入clazz指定的类中的对象
	 * 
	 * @param clazz
	 *            要注入值的类
	 * @param rs
	 *            结果集取出要注入的值
	 * @return 返回指定的值对象
	 */
	@SuppressWarnings("unchecked")
	private static Object getValueObject(Class clazz, ResultSet rs) {
		Object object = null;

		try {
			// 通过默认构造方法创建一个新的对象
			object = clazz.getConstructor(new Class[] {}).newInstance(
					new Object[] {});
			// 获得对象的所有属性
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// 获取属性名称
				String fieldName = fields[i].getName();
				// 获取属性名称的首字母，并将该字母转为大写形式
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				// 获得和属性对应的setXXX()方法的名字
				String setMethodName = "set" + firstLetter
						+ fieldName.substring(1);
				// 获得和属性对应的setXXX()方法
				Method setMethod = clazz.getMethod(setMethodName,
						new Class[] { fields[i].getType() });
				
				Object o = rs.getObject(fieldName.toLowerCase());
				
				if(o != null){
					// 调用目标对象的setXXX()方法对其属性赋值
					setMethod.invoke(object, new Object[] {o});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			object = null;
		}

		return object;
	}

	/**
	 * 关闭数据库链接和语句
	 * 
	 * @param statement
	 *            需要关闭的数据库语句
	 * @param conn
	 *            需要关闭的数据库链接
	 */
	@SuppressWarnings("null")
	private static void closeStatementAndConnection(Statement statement,Connection conn) {
		if (statement == null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn == null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据sql语句模板更新（插入、更新和删除）操作
	 * 
	 * @param sql
	 *            需要插入、更新和删除的sql语句
	 * @return 返回是否执行成功
	 */
	private static boolean executeUpdate(String sql) {
		boolean flag = false;// 是否执行成功的标志
		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		try {
			statement = conn.createStatement();// 创建语句
			int count = statement.executeUpdate(sql);// 执行sql语句
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return flag;
	}
	//求结果集
	@SuppressWarnings("unchecked")
	public static int queryCountRecord(Class clazz, String countSql) {

		int count=0;
		String classname = clazz.getName();// 获得类名
		String sql = " select count(*) from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// 执行查询的sql语句
		sql+=countSql;
        System.out.println(sql);
		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// 创建语句
			resultSet = statement.executeQuery(sql);// 执行sql语句
			if (resultSet.next()) {
				count=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return count;// 返回获得的对象
	}
	@SuppressWarnings("unchecked")
	public static ArrayList executeData() {
		String sql=" show tables";
		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList list = null;
		try {
			statement = conn.createStatement();// 创建语句
			resultSet = statement.executeQuery(sql);// 执行sql语句
			list = new ArrayList();// 初始化
			while (resultSet.next()) {
				list.add(getValueObject(showTables.class, resultSet));// 加入值对象
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return list;
	}
	//备份恢复
	public static boolean execute(String sql) {
		Connection conn = DBUtil.getInstance().getConnection();// 获得数据库连接
		Statement statement = null;
		boolean flag=false;
		try {
			statement = conn.createStatement();// 创建语句
			flag=statement.execute(sql);// 执行sql语句
			System.out.println(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭数据库链接和语句
			closeStatementAndConnection(statement, conn);
		}
		return flag;
	}
}
