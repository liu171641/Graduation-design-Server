package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * @日期:2020年5月18日 上午10:38:04
 * @作用:删除操作
 */
public class NewsDelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = -1;
		com.tools.Tools.Encodeing(request, response);
		int id = Integer.parseInt(request.getParameter("id").trim());
		String sqlString = "delete from user where id=?";
		Object[] objects = new Object[] { id };
		if (DBHelper.Update(sqlString, objects)) {
			result = 2;// 1为添加成功，2为删除成功
		} else {
			result = 0;
		}
		request.getServletContext().setAttribute("result", result);
		request.getRequestDispatcher("admin/newslistMgr.jsp").forward(request,
				response);
	}

}
