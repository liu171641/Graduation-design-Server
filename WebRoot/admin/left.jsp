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
<title>My JSP 'left.jsp' starting page</title>
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="admin/js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>
</head>

<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>通讯录
	</div>

	<dl class="leftmenu">

		<dd>
			<div class="title">
				<span><img src="admin/images/leftico01.png" /></span>信息管理
			</div>
			<ul class="menuson">
				<li class="active"><cite></cite><a href="admin/index.jsp"
					target="rightFrame">首页管理</a><i></i></li>
				<li><cite></cite><a href="admin/newslistMgr.jsp"
					target="rightFrame">用户管理</a><i></i></li>
				<li><cite></cite><a href="admin/CommodityMgr.jsp"
					target="rightFrame">商品管理</a><i></i></li>
					<li><cite></cite><a href="admin/Purchasing.jsp"
					target="rightFrame">代购管理</a><i></i></li>
				<li><cite></cite><a href="admin/imgtable.html"
					target="rightFrame">图片数据表</a><i></i></li>
				<li><cite></cite><a href="admin/newslistAdd.jsp"
					target="rightFrame">添加编辑</a><i></i></li>
				<li><cite></cite><a href="admin/imglist.html"
					target="rightFrame">图片列表</a><i></i></li>
				<li><cite></cite><a href="admin/imglist1.html"
					target="rightFrame">自定义</a><i></i></li>
				<li><cite></cite><a href="admin/tools.html" target="rightFrame">常用工具</a><i></i></li>
				<li><cite></cite><a href="admin/filelist.html"
					target="rightFrame">信息管理</a><i></i></li>
				<li><cite></cite><a href="admin/tab.html" target="rightFrame">Tab页</a><i></i></li>
				<li><cite></cite><a href="admin/error.html" target="rightFrame">404页面</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span><img src="admin/images/leftico02.png" /></span>其他设置
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">编辑内容</a><i></i></li>
				<li><cite></cite><a href="#">发布信息</a><i></i></li>
				<li><cite></cite><a href="#">档案列表显示</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span><img src="admin/images/leftico03.png" /></span>编辑器
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">自定义</a><i></i></li>
				<li><cite></cite><a href="#">常用资料</a><i></i></li>
				<li><cite></cite><a href="#">信息列表</a><i></i></li>
				<li><cite></cite><a href="#">其他</a><i></i></li>
			</ul>
		</dd>


		<dd>
			<div class="title">
				<span><img src="admin/images/leftico04.png" /></span>日期管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="#">自定义</a><i></i></li>
				<li><cite></cite><a href="#">常用资料</a><i></i></li>
				<li><cite></cite><a href="#">信息列表</a><i></i></li>
				<li><cite></cite><a href="#">其他</a><i></i></li>
			</ul>

		</dd>

	</dl>

</body>
</html>
