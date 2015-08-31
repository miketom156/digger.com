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
	 * ����objectģ��������
	 * 
	 * @param object
	 *            ��Ҫ����Ķ���
	 * @return �����Ƿ�ִ�гɹ�
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateSave(Object object) {
		Class clazz = object.getClass();// ���object�����Class
		String classname = clazz.getName();// �������
		String sql = " insert into "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// ִ�в����sql���
		String columns = "";// ��Ҫ������������Ӿ�
		String values = "";// values�����Ӿ�
		Field[] fields = clazz.getDeclaredFields();// ��ò���object�����������ķ���
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// ��ö�������
			String upperName = name.substring(0, 1).toUpperCase()
					+ name.substring(1);// ������������ĸ��д
			Object obj = null;
			try {
				obj = clazz.getDeclaredMethod("get" + upperName).invoke(object);// ��ò���object����������getXXX()������ȡ�÷���ֵ
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
		return executeUpdate(sql);// ����ִ�н��
	}

	/**
	 * ����objectģ����²���
	 * 
	 * @param object
	 *            ��Ҫ���µĶ���
	 * @return �����Ƿ�ִ�гɹ�
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateUpdate(Object object) {
		Class clazz = object.getClass();// ���object�����Class
		String classname = clazz.getName();// �������
		String sql = " update " + classname.substring(classname.lastIndexOf(".") + 1).toLowerCase() + " set ";// ִ�и��µ�sql���
		String where = "";// where�����Ӿ�
		Field[] fields = clazz.getDeclaredFields();// ��ò���object�����������ķ���
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// ��ö�������
			String upperName = name.substring(0, 1).toUpperCase()
					+ name.substring(1);// ������������ĸ��д
			Object obj = null;
			try {
				obj = clazz.getDeclaredMethod("get" + upperName).invoke(object);// ��ò���object����������getXXX()������ȡ�÷���ֵ
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!name.matches("(.*)id")) {
				// ƥ���Ƿ���id�ַ����ֶ�
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
		return executeUpdate(sql);// ����ִ�н��
	}

	/**
	 * ����objectģ��ɾ������
	 * 
	 * @param object
	 *            ��Ҫɾ���Ķ���
	 * @return �����Ƿ�ִ�гɹ�
	 */
	@SuppressWarnings("unchecked")
	public static boolean templateDelete(Object object) {
		Class clazz = object.getClass();// ���object�����Class
		String classname = clazz.getName();// �������
		String sql = " delete from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase() + " where ";// ִ�и��µ�sql���
		Field[] fields = clazz.getDeclaredFields();// ��ò���object�����������ķ���
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// ��ö�������
			if (name.matches("(.*)id")) {
				// ƥ���Ƿ���id�ַ����ֶ�
				String upperName = name.substring(0, 1).toUpperCase()
						+ name.substring(1);// ������������ĸ��д
				Object obj = null;
				try {
					obj = clazz.getDeclaredMethod("get" + upperName).invoke(
							object);// ��ò���object����������getXXX()������ȡ�÷���ֵ
				} catch (Exception e) {
					e.printStackTrace();
				}
				sql += name + " = '" + obj.toString() + "' ";
			}
		}
		System.out.println("sql : " + sql);
		return executeUpdate(sql);// ����ִ�н��
	}

	/**
	 * ȡ��ָ���Ķ����ѯ�Ķ���
	 * 
	 * @param clazz
	 *            ��Ҫ��ѯ����
	 * @param uid
	 *            ��Ҫ��ѯָ���Ķ���
	 * @return ���ػ�õĶ���
	 */
	@SuppressWarnings("unchecked")
	public static Object templateQuery(Class clazz, String name) {

		String classname = clazz.getName();// �������
		Object object = null;// �������մ����ݿ�ȡ���������б�
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// ִ�в�ѯ��sql���
		sql+=name;
System.out.println(sql);
		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// �������
			resultSet = statement.executeQuery(sql);// ִ��sql���
			if (resultSet.next()) {
				object = getValueObject(clazz, resultSet);// ��ȡֵ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return object;// ���ػ�õĶ���
	}
	
	@SuppressWarnings("unchecked")
	public static Object template_Query(Class clazz, int uid) {

		String classname = clazz.getName();// �������
		Object object = null;// �������մ����ݿ�ȡ���������б�
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// ִ�в�ѯ��sql���
		Field[] fields = clazz.getDeclaredFields();// ��ò���object�����������ķ���
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();// ��ö�������
			if (name.matches("(.*)id")) {
				// ƥ���Ƿ���id�ַ����ֶ�
				sql += " where " + name + " = '" + uid + "'";
			}
		}
		System.out.println(sql);

		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// �������
			resultSet = statement.executeQuery(sql);// ִ��sql���
			if (resultSet.next()) {
				object = getValueObject(clazz, resultSet);// ��ȡֵ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return object;// ���ػ�õĶ���
	}
	
	/**
	 * ȡ��ָ���Ķ����ѯ�Ķ��󼯺�
	 * @param clazz ��Ҫ��ѯ����
	 * @param queryParams ��Ҫ��ѯָ���Ķ���Ĳ�������
	 * @return ���ػ�õĶ��󼯺�
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList templateQuery(Class clazz, Map<String, Object> queryParams) {

		String classname = clazz.getName();// �������
		ArrayList list = null;// �������մ����ݿ�ȡ���������б�
		String sql = " select * from " + classname.substring(classname.lastIndexOf(".") + 1).toLowerCase() + " where ";// ִ�в�ѯ��sql���
		String order = "";// ������ַ���
		String limit = "";// ��ҳ���ַ���
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

		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			list = new ArrayList();//��ʼ�������б�
			statement = conn.createStatement();// �������
			resultSet = statement.executeQuery(sql);// ִ��sql���
			while (resultSet.next()) {
				list.add(getValueObject(clazz, resultSet));// ��ȡֵ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return list;// ���ػ�õĶ���
	}

	/**
	 * ȡ��ָ���Ķ����ѯ�Ķ����б�
	 * 
	 * @param clazz
	 *            ��Ҫ��ѯ�Ķ���
	 * @return ���ػ�õĶ����б�
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList TemplateQuery(Class clazz,String mysql, Object[] values) {
		String classname = clazz.getName();// �������
		ArrayList list = null;// �������մ����ݿ�ȡ���������б�
		String sql = " select * from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// ִ�в�ѯ��sql���
		sql+=mysql;
		System.out.println("sql : " + sql);

		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
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
			resultSet = pstmt.executeQuery();// ִ��sql���
			list = new ArrayList();// ��ʼ��
			while (resultSet.next()) {
				list.add(getValueObject(clazz, resultSet));// ����ֵ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(pstmt, conn);
		}
		return list;// ���ػ�õĽ����
	}

	/**
	 * ��ô����ݿ�ȡ����ֵע��clazzָ�������еĶ���
	 * 
	 * @param clazz
	 *            Ҫע��ֵ����
	 * @param rs
	 *            �����ȡ��Ҫע���ֵ
	 * @return ����ָ����ֵ����
	 */
	@SuppressWarnings("unchecked")
	private static Object getValueObject(Class clazz, ResultSet rs) {
		Object object = null;

		try {
			// ͨ��Ĭ�Ϲ��췽������һ���µĶ���
			object = clazz.getConstructor(new Class[] {}).newInstance(
					new Object[] {});
			// ��ö������������
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// ��ȡ��������
				String fieldName = fields[i].getName();
				// ��ȡ�������Ƶ�����ĸ����������ĸתΪ��д��ʽ
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				// ��ú����Զ�Ӧ��setXXX()����������
				String setMethodName = "set" + firstLetter
						+ fieldName.substring(1);
				// ��ú����Զ�Ӧ��setXXX()����
				Method setMethod = clazz.getMethod(setMethodName,
						new Class[] { fields[i].getType() });
				
				Object o = rs.getObject(fieldName.toLowerCase());
				
				if(o != null){
					// ����Ŀ������setXXX()�����������Ը�ֵ
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
	 * �ر����ݿ����Ӻ����
	 * 
	 * @param statement
	 *            ��Ҫ�رյ����ݿ����
	 * @param conn
	 *            ��Ҫ�رյ����ݿ�����
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
	 * ����sql���ģ����£����롢���º�ɾ��������
	 * 
	 * @param sql
	 *            ��Ҫ���롢���º�ɾ����sql���
	 * @return �����Ƿ�ִ�гɹ�
	 */
	private static boolean executeUpdate(String sql) {
		boolean flag = false;// �Ƿ�ִ�гɹ��ı�־
		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		try {
			statement = conn.createStatement();// �������
			int count = statement.executeUpdate(sql);// ִ��sql���
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return flag;
	}
	//������
	@SuppressWarnings("unchecked")
	public static int queryCountRecord(Class clazz, String countSql) {

		int count=0;
		String classname = clazz.getName();// �������
		String sql = " select count(*) from "
				+ classname.substring(classname.lastIndexOf(".") + 1)
						.toLowerCase();// ִ�в�ѯ��sql���
		sql+=countSql;
        System.out.println(sql);
		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = conn.createStatement();// �������
			resultSet = statement.executeQuery(sql);// ִ��sql���
			if (resultSet.next()) {
				count=resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return count;// ���ػ�õĶ���
	}
	@SuppressWarnings("unchecked")
	public static ArrayList executeData() {
		String sql=" show tables";
		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList list = null;
		try {
			statement = conn.createStatement();// �������
			resultSet = statement.executeQuery(sql);// ִ��sql���
			list = new ArrayList();// ��ʼ��
			while (resultSet.next()) {
				list.add(getValueObject(showTables.class, resultSet));// ����ֵ����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return list;
	}
	//���ݻָ�
	public static boolean execute(String sql) {
		Connection conn = DBUtil.getInstance().getConnection();// ������ݿ�����
		Statement statement = null;
		boolean flag=false;
		try {
			statement = conn.createStatement();// �������
			flag=statement.execute(sql);// ִ��sql���
			System.out.println(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// �ر����ݿ����Ӻ����
			closeStatementAndConnection(statement, conn);
		}
		return flag;
	}
}
