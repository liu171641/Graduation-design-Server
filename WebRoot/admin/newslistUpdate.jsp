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
		String sqlString = "select * from user where id=?";
		Object[] objects = new Object[] { id };
		List<HashMap<String, Object>> newslist = DBHelper.getList(
				sqlString, objects);
		for (HashMap row : newslist) {
	%>
	<form action="NewsUpdateServlet?id=<%=id%>" enctype="multipart/form-data" method="post">
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
				<li class="tdleibie"><label>用户姓名</label><input name="name"
					type="text" class="dfinput"
					value='<%=row.get("name").toString().trim()%>' /></li>
				<li class="tdleibie"><label>用户头像</label><img
					style="width: 100px;height: 100px;"
					src="<%=row.get("photo").toString().trim()%>" />上传图片 <input
					type="file" name="myfile" /></li>

				<li><label>用户性别</label><select class="layui-form" name="sex"
					style="width: 120px;">
						<option value="男">男</option>
						<option value="女">女</option>
				</select></li>
				<li class="tdleibie"><label>用户手机</label><input
					name="phonenumber" type="text" class="dfinput"
					value='<%=row.get("phonenumber").toString().trim()%>' /></li>
				<li class="tdleibie"><label>用户密码</label><input name="password"
					type="text" class="dfinput"
					value='<%=row.get("password").toString().trim()%>' /></li>
				<li class="tdleibie"><label>用户年龄</label><input name="age"
					type="text" class="dfinput"
					value='<%=row.get("age").toString().trim()%>' /></li>
				<li class="tdleibie"><label>用户地址</label><input name="address"
					type="text" class="dfinput"
					value='<%=row.get("address").toString().trim()%>' /></li>
				<%
					}
				%>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="更新" /> <a href="admin/newslistMgr.jsp">取消</a></li>
			</ul>
		</div>
	</form>
</body>
</html>
