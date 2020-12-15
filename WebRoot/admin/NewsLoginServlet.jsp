<%@page import="com.tools.DBHelper"%>
<%@page import="com.tools.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'NewsLoginServlet.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	
	<%
		Tools.Encodeing(request, response);

		String zh = request.getParameter("zh").toString();
		String pwd = request.getParameter("pwd").toString();
		String sqlString = "select * from teacher";
		HashMap<String, Object> row = DBHelper.getSingle(sqlString, null);
		String jid = row.get("jid").toString().trim();
		String password = row.get("password").toString().trim();
		if (jid != null && pwd != null) {
			if (zh.equals(jid) && pwd.equals(password)) {
				out.print("<script>alert('登陆成功')</script>");
				request.getRequestDispatcher("main.jsp")
						.forward(request, response);
			} else {
				out.print("<script>alert('密码错误，请重新输入')</script>");
				request.getRequestDispatcher("login.jsp").forward(
						request, response);
			}
		} else {
			out.print("<script>alert('为接收到，请重新输入')</script>");
		}
	%>
</body>
</html>
