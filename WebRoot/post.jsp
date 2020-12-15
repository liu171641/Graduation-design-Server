<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String password = request.getParameter("password");
	String username = request.getParameter("username");
	if (password != null && username != null) {
		username = new String(username.getBytes("iso-8859-1"), "utf-8");
		password = new String(password.getBytes("iso-8859-1"), "utf-8");
		out.print("访问成功");
		if ("root".equals(username) && "123".equals(password)) {
%>
<%="ok"%>
<%
	}
%>
<%
	}
%>

