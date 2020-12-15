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
 * @����:���
 * @����:1716413010@qq.com
 * @�汾: v1.0
 * @����:2020��5��18�� ����10:38:04
 * @����:ɾ������
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
			result = 2;// 1Ϊ��ӳɹ���2Ϊɾ���ɹ�
		} else {
			result = 0;
		}
		request.getServletContext().setAttribute("result", result);
		request.getRequestDispatcher("admin/newslistMgr.jsp").forward(request,
				response);
	}

}
