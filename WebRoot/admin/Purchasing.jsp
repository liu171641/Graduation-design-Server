<%@page import="javax.xml.crypto.Data"%>
<%@page import="com.tools.DataRow"%>
<%@page import="com.tools.DBHelper"%>
<%@page import="com.tools.DataTable"%>
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
<title>My JSP 'newslistMgr.jsp' starting page</title>
<link href="admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="admin/js/zDrag.js"></script>
<script type="text/javascript" src="admin/js/zDialog.js"></script>
<script type="text/javascript">
	function open3() {
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 300;
		diag.Title = "页面显示数据设置";
		diag.URL = "pagesizeSet.jsp";
		diag.show();
	}
</script>
</head>
<%
	Object result = request.getServletContext().getAttribute("result");
	//用完释放，不然会长期存在于request.getServletContext()当中
	request.getServletContext().removeAttribute("result");
	if (result != null) {
		if (result.toString().trim().equals("1")) {
			out.print("<script>alert('添加成功')</script>");
		} else if (result.toString().trim().equals("2")) {
			out.print("<script>alert('删除成功')</script>");
		} else if (result.toString().trim().equals("3")) {
			out.print("<script>alert('修改成功')</script>");
		} else {
			out.print("<script>alert('操作失败')</script>");
		}
	}
%>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">数据表</a></li>
			<li><a href="#">基本内容</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click" onclick="window.location.href='admin/newslistAdd.jsp'"><span><img
						src="admin/images/t01.png" /></span>添加商品</li>
					
				<li class="click"><span><img src="admin/images/t02.png" /></span>修改选中</li>
				<li><span><img src="admin/images/t04.png" /></span>全选</li>
				<li><span><img src="admin/images/t03.png" /></span>删除所选</li>
				<li><span></span>综合查询：</li>
				<li><input name="" type="text" class="scinput"
					style="border: 0;" /></li>
				<li><label>所属菜单：</label> <select class="select3"
					style="width: 120px;">
						<option>全部</option>
						<option>其他</option>
				</select></li>
				<li><span><img src="admin/images/t04.png" /></span>查询</li>
			</ul>
			<ul class="toolbar1">
				<li onclick="open3()"><span><img
						src="admin/images/t05.png" /></span>设置</li>
			</ul>
		</div>
		<table class="tablelist">
			<thead>
				<tr >
					
					<th>编号<i class="sort"><img src="admin/images/px.gif" /></i></th>
					<th>代购商品标题</th>
					<th>代购商品数量</th>
					<th>代购商品价格</th>
					<th>代购人手机号码</th>
					<th>代购人地址</th>
					<th>商品链接</th>
					<th>回复</th>
				</tr>
			</thead>
			<tbody>
				<%
					String sqlString = "select *  from purchasing";

					//	String sqlString = "select newstable.id,newstype.typename, newstable.title,newstable.istop,newstable.uid,newstable.intime  from newstable,newstype where newstable.typeid=newstype.typeid";
					List<HashMap<String, Object>> newslist = DBHelper.getList(
							sqlString, null);

					/* 			 if(newslist!=null){
					 out.print("有数据");
					 }else{
					 out.print("没有数据");
					 } */
					
					for (HashMap row : newslist) {
				%>
				<tr style=" text-align:center;">
			
					<td><%=row.get("id").toString().trim()%></td>
					<td style="width: 250px;"><%=row.get("purchasingtitle").toString().trim()%></td>
					<td><%=row.get("purchasingamount").toString().trim()%></td>
					<td><%=row.get("purchasingprice").toString().trim()%></td>
					<td><%=row.get("purchasingphonenumber").toString().trim()%></td>
					<td><%=row.get("purchasingaddress").toString().trim()%></td>
					<td><%=row.get("purchasingurl").toString().trim()%></td>
					<td><a
						href="admin/PurchasingUpdate.jsp?id=<%=row.get("id").toString().trim()%>">回复</a>
					
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div class="pagin">
			<div class="message">
				共<i class="blue"><%=newslist.size()%></i>条记录，当前显示第&nbsp;<i class="blue">1&nbsp;</i>页
			</div>
			<ul class="paginList">
				<li class="paginItem"><a href="javascript:;"><span
						class="pagepre"></span></a></li>
				<li class="paginItem"><a href="javascript:;">1</a></li>
				<li class="paginItem current"><a href="javascript:;">2</a></li>
				<li class="paginItem"><a href="javascript:;">3</a></li>
				<li class="paginItem"><a href="javascript:;">4</a></li>
				<li class="paginItem"><a href="javascript:;">5</a></li>
				<li class="paginItem more"><a href="javascript:;">...</a></li>
				<li class="paginItem"><a href="javascript:;">10</a></li>
				<li class="paginItem"><a href="javascript:;"><span
						class="pagenxt"></span></a></li>
			</ul>
		</div>
	</div>
</body>
</html>
