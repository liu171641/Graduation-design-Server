package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tools.DBHelper;
import com.tools.Tools;

public class PurchasingUpdateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result=-1;
		Tools.Encodeing(request, response);
		/////////ȡid/////////
		int id=Integer.parseInt(request.getParameter("id").trim());
		String purchasingurl=request.getParameter("purchasingurl").trim();
		PrintWriter out=response.getWriter();
		out.print(""+id+purchasingurl);
		//typeid   newstitle  content  istop
		String sqlString="update  purchasing set purchasingurl=?  where id=?";
		Object[] objects=new Object[]{purchasingurl,id};
		if(DBHelper.Update(sqlString, objects)){
			request.getRequestDispatcher("admin/Success.jsp").forward(request, response);
		}else{
			result=0;
		}
	}

}
