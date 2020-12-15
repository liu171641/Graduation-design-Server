package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ���� : ��� ���� : 1716413010@qq.com ���� : 2020/11/7 15:58 ���� :���ݿ���� �汾: 1.0
 */
public class DBHelper {
	// //��̬����Ҳ���෽��������ʱֱ�����������������//////
	// DBHelper.iniConnection();
	// //////////����һ�����ݷ�����˵����Ҫ��һЩʲô������
	// 1.��ѯ����
	// 2.��ɾ�Ĳ���
	// 3.�������� select count(*)
	// ///////��ʼ������////////
	private static Connection connection = null;
	private static PreparedStatement pstm = null;
	private static ResultSet rset = null;

	public static Connection iniConnection() {
		// ///1.׼��//////////////////
		// jdbc:mysql://localhost:3306/jsjdata?useUnicode=true&characterEncoding=utf8
		String ip = "cdb-3csxx0hu.cd.tencentcdb.com";
		String url = "jdbc:mysql://" + ip
				+ ":10162/data?useUnicode=true&characterEncoding=utf8";
		String username = "root";
		String pwd = "1716413010@+";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection(url, username, pwd);
			if (cn != null) {
			} else {
			}

			return cn;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return null;
	}

	public static void colseCn(ResultSet rs, PreparedStatement ps, Connection cn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		if (cn != null)
			try {
				cn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
	}

	/**
	 * @param sqlString
	 *            �������ɾ�ĵ�sql���
	 * @param objects
	 *            �������������������
	 * @return
	 */
	public static boolean Update(String sqlString, Object[] objects) {
		Connection cn = iniConnection();
		PreparedStatement ps = null;
		try {
			ps = cn.prepareStatement(sqlString);
			// /////////��������/////////////
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}
			// //////////////////
			if (ps.executeUpdate() >= 1) {
				colseCn(null, ps, cn);
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		colseCn(null, ps, cn);
		return false;
	}

	// ////������������ �� select count(*)////////
	public static String getScalar(String sqlString, Object[] objects) {
		Connection cn = iniConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";
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
			// ///ResultSet��һ���ʱ꣬���ܹر����ӣ�ʹ��ʱ�����������/////////
			rs.next();
			result = rs.getString(1);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		colseCn(rs, ps, cn);
		return result;
	}

	// ///����������Ǿ������� ��Ϊ��Ҫ������������/////�����з���������������Ϊ20////
	// ///���� datacolmn datarow datatable////////////

	// ///����������Ǿ������� ��Ϊ��Ҫ������������/////�����з���������������Ϊ20////
	public static ResultSet Query(String sqlString, Object[] objects) {
		connection = iniConnection();
		try {
			pstm = connection.prepareStatement(sqlString);
			// /////////��������/////////////
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					pstm.setObject(i + 1, objects[i]);
				}
			}
			// ///�п����ǿձ�////////////////
			rset = pstm.executeQuery();
			// ///ResultSet��һ���ʱ꣬���ܹر����ӣ�ʹ��ʱ�����������/////////
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return rset;
	}

	// //listΪ�б�/////
	public static List<HashMap<String, Object>> getList(String sqlString,
			Object[] objects) {
		Connection cn = iniConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<HashMap<String, Object>> list = new ArrayList<>();
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

			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> row = new HashMap<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.put(rsmd.getColumnName(i).trim(), rs.getObject(i));
				}
				list.add(row);
			}
			// ///ResultSet��һ���ʱ꣬���ܹر����ӣ�ʹ��ʱ�����������/////////
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		colseCn(rs, ps, cn);
		return list;
	}

	// /////����رյ��Ǿ�̬��������Ա///
	public static void closeCn1() {
		try {
			rset.close();
			pstm.close();
			connection.close();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}
