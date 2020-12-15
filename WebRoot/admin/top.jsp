<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>My JSP 'top.jsp' starting page</title>
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="admin/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//顶部导航切换
		$(".nav li a").click(function() {
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		})
	})
</script>
</head>
<body style="background:url(admin/images/topbg.gif) repeat-x;">
	<div class="topleft">
		<a href="admin/main.jsp" target="_parent"><img src="admin/images/logo.png"
			title="系统首页" /></a>
	</div>
	<ul class="nav">
		<li><a href="admin/default.html" target="rightFrame" class="selected"><img
				src="admin/images/icon01.png" title="工作台" />
			<h2>工作台</h2></a></li>
		<li><a href="admin/imgtable.html" target="rightFrame"><img
				src="admin/images/icon02.png" title="模型管理" />
			<h2>模型管理</h2></a></li>
		<li><a href="imglist.html" target="rightFrame"><img
				src="admin/images/icon03.png" title="模块设计" />
			<h2>模块设计</h2></a></li>
		<li><a href="admin/tools.html" target="rightFrame"><img
				src="admin/images/icon04.png" title="常用工具" />
			<h2>常用工具</h2></a></li>
		<li><a href="computer.html" target="rightFrame"><img
				src="images/icon05.png" title="文件管理" />
			<h2>文件管理</h2></a></li>
		<li><a href="admin/tab.html" target="rightFrame"><img
				src="admin/images/icon06.png" title="系统设置" />
			<h2>系统设置</h2></a></li>
	</ul>
	<div class="topright">
		<ul>
			<li><span><img src="admin/images/help.png" title="帮助"
					class="helpimg" /></span><a href="#">帮助</a></li>
			<li><a href="admin/">关于</a></li>
			<li><a href="admin/login.html" target="_parent">退出</a></li>
		</ul>
		<div class="user">
			<span>admin</span> <i>消息</i> <b>5</b>
		</div>
	</div>
</body>
</html>
