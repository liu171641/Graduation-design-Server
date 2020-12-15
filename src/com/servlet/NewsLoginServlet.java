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
 * @作者:刘宇航
 * @邮箱:1716413010@qq.com
 * @版本: v1.0
 * @日期:2020年7月6日 上午9:43:46
 * @作用:
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
//				out.print("<script>alert('登陆成功')</script>");
//			} else {
//
//				request.getRequestDispatcher("login.jsp").forward(request,
//						response);
//				out.print("<script>alert('密码错误，请重新输入')</script>");
//			}
//		} else {
//			out.print("<script>alert('为接收到，请重新输入')</script>");
//		}
	}
}
