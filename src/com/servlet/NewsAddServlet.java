package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * @日期:2020年5月17日 上午11:38:04
 * @作用:添加操作
 */
public class NewsAddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = -1;

		// 编码//
		Tools.Encodeing(request, response);
		// //////取数据///////////
		int typeid = Integer.parseInt(request.getParameter("typeid").trim());
		String newstitle = request.getParameter("newstitle").toString().trim();
		String content = request.getParameter("content").toString().trim();
		int istop = Integer.parseInt(request.getParameter("istop").trim());
		int uid = 0;
		PrintWriter out=response.getWriter();


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String intime = sdf.format(date);// ////实例化会自动取当前时间
		// //////////进行插入操作////////////////
		String sqlString = "insert into newstable(typeid,title,content,uid,intime,istop) values(?,?,?,?,?,?)";
		Object[] paObjects = new Object[] { typeid, newstitle, content, uid,
				intime, istop };
		// //////////
		// PrintWriter out=response.getWriter();
		if (DBHelper.Update(sqlString, paObjects)) {
			result = 1;
		} else {
			result = 0;
		}
		request.getServletContext().setAttribute("result", result);
		request.getRequestDispatcher("admin/newslistMgr.jsp").forward(request,
				response);
	}

}
