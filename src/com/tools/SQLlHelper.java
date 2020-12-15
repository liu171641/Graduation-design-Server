package com.tools;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @作者:刘宇航
 * @邮箱:1716413010@qq.com
 * @版本: v1.0
 * @日期:2020年5月11日 上午11:58:34
 * @作用:数据库访问类
 */
public class SQLlHelper {
	// 静态方法也叫类方法，调用是直接用类的名称来调用 例：DBHelper.inConnection
	/*
	 * private static Connection connection = null; private static
	 * PreparedStatement pstm = null; private static ResultSet rset = null;
	 */

	/**
	 * 连接数据库
	 * 
	 * @return
	 */
	public static Connection inConnection() {
		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Test";
		String username = "root";
		String pwd = "123";
		try {
			// 编码问题
			// jdbc:mysql://localhost:3306/jsjdata?useUnicode=true&characterEncoding=utf8
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection cn = DriverManager.getConnection(url, username, pwd);
			return cn;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭
	 * 
	 * @param rs
	 * @param ps
	 * @param cn
	 */
	private static void closeCn(ResultSet rs, PreparedStatement ps,
			Connection cn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param sqlString
	 *            传入的增删改的sql语句
	 * @param objects
	 *            传入参数，用数组来传
	 * @return
	 */
	public static boolean Update(String sqlString, Object[] objects) {
		Connection cn = inConnection();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(sqlString);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
				if (ps.executeUpdate() >= 1) {
					closeCn(null, ps, cn);
					return true;
				}

			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		closeCn(null, ps, cn);
		return false;
	}

	/**
	 * 获取首行收列
	 * 
	 * @param sqlString
	 * @param objects
	 * @return
	 */
	public static String getScalar(String sqlString, Object[] objects) {
		Connection cn = inConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
		try {
			ps = cn.prepareStatement(sqlString);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			rs = ps.executeQuery();
			rs.next();
			result = rs.getString(1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		closeCn(rs, ps, cn);
		return result;
	}

	/**
	 * 尽量少用
	 * 
	 * @param sqlString
	 *            传入的查询的sql语句
	 * @param objects
	 * @return ResultSet游标
	 */
	/*
	 * public static ResultSet Query(String sqlString, Object[] objects) {
	 * connection = inConnection(); try { pstm =
	 * connection.prepareStatement(sqlString); if (objects != null) { for (int i
	 * = 0; i < objects.length; i++) { pstm.setObject(i + 1, objects[i]); } }
	 * rset = pstm.executeQuery(); // ResultSet是游标，不能关闭连接,使用时必须保持持续连接 //
	 * closeCn(rs, ps, cn);
	 * 
	 * } catch (SQLException e) { // TODO 自动生成的 catch 块 e.printStackTrace(); }
	 * // closeCn(null, ps, cn); return rset; }
	 */

	public static java.util.List<HashMap<String, Object>> getList(
			String sqlString, Object[] objects) {
		Connection cn = inConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		java.util.List<HashMap<String, Object>> list = new ArrayList<>();
		try {
			ps = cn.prepareStatement(sqlString);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> row = new HashMap<>();
				for (int i = 1; i < rsmd.getColumnCount(); i++) {
					row.put(rsmd.getColumnName(i).trim(), rs.getObject(i));
				}
				list.add(row);
			}
			// ResultSet是游标，不能关闭连接,使用时必须保持持续连接
			// closeCn(rs, ps, cn);

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		closeCn(rs, ps, cn);
		return list;
	}

	public static HashMap<String, Object> getSingle(String sqlString, Object[] objects) {
		Connection cn= inConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;		
		HashMap<String, Object> row=null;
		try {
			ps = cn.prepareStatement(sqlString);
			// /////////参数处理/////////////
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// ///有可能是空表////////////////
			rs = ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			rs.next();
			row=new HashMap<String, Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				row.put(rsmd.getColumnName(i).trim(), rs.getObject(i));
			}		
			// ///ResultSet是一个邮标，不能关闭连接，使用时必须持续连接/////////
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		closeCn(rs, ps, cn);
		return row;
	}

	/**
	 * 这里关闭的是静态的三个成员
	 */
	/*
	 * public static void closeCn1() { try { rset.close(); pstm.close();
	 * connection.close(); } catch (SQLException e) { // TODO 自动生成的 catch 块
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}
