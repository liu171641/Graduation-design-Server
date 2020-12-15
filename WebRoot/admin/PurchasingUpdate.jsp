<%@page import="com.sun.rowset.internal.Row"%>
<%@page import="com.tools.DBHelper"%>
<%@page import="com.tools.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<base href="<%=basePath%>">
<title>My JSP 'newslistAdd.jsp' starting page</title>
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<%
		Tools.Encodeing(request, response);
		int id = Integer.parseInt(request.getParameter("id").trim());
		String sqlString = "select * from purchasing where id=?";
		Object[] objects = new Object[] { id };
		List<HashMap<String, Object>> newslist = DBHelper.getList(
				sqlString, objects);
		for (HashMap row : newslist) {
	%>
	<form action="PurchasingUpdateServlet?id=<%=id%>"  method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">表单</a></li>
			</ul>
		</div>
		<div class="formbody">
			<div class="formtitle">
				<span>修改信息</span>
			</div>
			<ul class="forminfo">
				<li class="tdleibie"><label>商品链接</label><input name="purchasingurl"
					type="text" class="dfinput"
					value='<%=row.get("purchasingurl").toString().trim()%>' /></li>
				<%
					}
				%>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="更新" /><a href="admin/Purchasing.jsp">取消</a></li>
			</ul>
		</div>
	</form>
</body>
</html>
