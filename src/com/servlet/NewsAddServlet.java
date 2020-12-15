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
 * @����:���
 * @����:1716413010@qq.com
 * @�汾: v1.0
 * @����:2020��5��17�� ����11:38:04
 * @����:��Ӳ���
 */
public class NewsAddServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = -1;

		// ����//
		Tools.Encodeing(request, response);
		// //////ȡ����///////////
		int typeid = Integer.parseInt(request.getParameter("typeid").trim());
		String newstitle = request.getParameter("newstitle").toString().trim();
		String content = request.getParameter("content").toString().trim();
		int istop = Integer.parseInt(request.getParameter("istop").trim());
		int uid = 0;
		PrintWriter out=response.getWriter();


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String intime = sdf.format(date);// ////ʵ�������Զ�ȡ��ǰʱ��
		// //////////���в������////////////////
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
