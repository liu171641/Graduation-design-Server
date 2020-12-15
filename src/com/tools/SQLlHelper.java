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
 * @����:���
 * @����:1716413010@qq.com
 * @�汾: v1.0
 * @����:2020��5��11�� ����11:58:34
 * @����:���ݿ������
 */
public class SQLlHelper {
	// ��̬����Ҳ���෽����������ֱ����������������� ����DBHelper.inConnection
	/*
	 * private static Connection connection = null; private static
	 * PreparedStatement pstm = null; private static ResultSet rset = null;
	 */

	/**
	 * �������ݿ�
	 * 
	 * @return
	 */
	public static Connection inConnection() {
		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Test";
		String username = "root";
		String pwd = "123";
		try {
			// ��������
			// jdbc:mysql://localhost:3306/jsjdata?useUnicode=true&characterEncoding=utf8
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection cn = DriverManager.getConnection(url, username, pwd);
			return cn;
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ر�
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param sqlString
	 *            �������ɾ�ĵ�sql���
	 * @param objects
	 *            �������������������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		closeCn(null, ps, cn);
		return false;
	}

	/**
	 * ��ȡ��������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		closeCn(rs, ps, cn);
		return result;
	}

	/**
	 * ��������
	 * 
	 * @param sqlString
	 *            ����Ĳ�ѯ��sql���
	 * @param objects
	 * @return ResultSet�α�
	 */
	/*
	 * public static ResultSet Query(String sqlString, Object[] objects) {
	 * connection = inConnection(); try { pstm =
	 * connection.prepareStatement(sqlString); if (objects != null) { for (int i
	 * = 0; i < objects.length; i++) { pstm.setObject(i + 1, objects[i]); } }
	 * rset = pstm.executeQuery(); // ResultSet���α꣬���ܹر�����,ʹ��ʱ���뱣�ֳ������� //
	 * closeCn(rs, ps, cn);
	 * 
	 * } catch (SQLException e) { // TODO �Զ����ɵ� catch �� e.printStackTrace(); }
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
			// ResultSet���α꣬���ܹر�����,ʹ��ʱ���뱣�ֳ�������
			// closeCn(rs, ps, cn);

		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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
			// /////////��������/////////////
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// ///�п����ǿձ�////////////////
			rs = ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			rs.next();
			row=new HashMap<String, Object>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				row.put(rsmd.getColumnName(i).trim(), rs.getObject(i));
			}		
			// ///ResultSet��һ���ʱ꣬���ܹر����ӣ�ʹ��ʱ�����������/////////
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		closeCn(rs, ps, cn);
		return row;
	}

	/**
	 * ����رյ��Ǿ�̬��������Ա
	 */
	/*
	 * public static void closeCn1() { try { rset.close(); pstm.close();
	 * connection.close(); } catch (SQLException e) { // TODO �Զ����ɵ� catch ��
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}
