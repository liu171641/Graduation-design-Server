package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tools.Tools;

/**
 * @作者:刘宇航
 * @邮箱:1716413010@qq.com
 * @版本: v1.0
 * @日期:2020年6月17日 上午9:41:35
 * @作用:
 */
public class Test extends HttpServlet {

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
//		int typeid = Integer.parseInt(request.getParameter("typeid").trim());
		String newstitle = request.getParameter("newstitle").toString().trim();
		String content = request.getParameter("content").toString().trim();
		int istop = Integer.parseInt(request.getParameter("istop").trim());
		int uid = 0;
		PrintWriter out = response.getWriter();
		out.print(newstitle);

	}
}