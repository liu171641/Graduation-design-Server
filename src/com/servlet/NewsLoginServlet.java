package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tools.DBHelper;
import com.tools.Tools;

/**
 * @����:���
 * @����:1716413010@qq.com
 * @�汾: v1.0
 * @����:2020��7��6�� ����9:43:46
 * @����:
 */
public class NewsLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Tools.Encodeing(request, response);
		PrintWriter out = response.getWriter();
		String zh = request.getParameter("zh").toString();
		String pwd = request.getParameter("pwd").toString();
		String sqlString = "select * from teacher";
//		HashMap<String, Object> row = DBHelper.getList(sqlString, null);
//		String jid = row.get("jid").toString().trim();
//		String password = row.get("password").toString().trim();
//		if (jid != null && pwd != null) {
//			if (zh.equals(jid) && pwd.equals(password)) {
//
//				request.getRequestDispatcher("newslistMgr.jsp").forward(
//						request, response);
//				out.print("<script>alert('��½�ɹ�')</script>");
//			} else {
//
//				request.getRequestDispatcher("login.jsp").forward(request,
//						response);
//				out.print("<script>alert('�����������������')</script>");
//			}
//		} else {
//			out.print("<script>alert('Ϊ���յ�������������')</script>");
//		}
	}
}
