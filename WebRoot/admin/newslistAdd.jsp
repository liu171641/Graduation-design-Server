<%@page import="com.tools.DBHelper"%>
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





	</form>
	<form action="UploadServlet" enctype="multipart/form-data"
		method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">表单</a></li>
			</ul>
		</div>
		<div class="formbody">
			<div class="formtitle">
				<span>基本信息</span>
			</div>
			<ul class="forminfo">
				<li class="tdleibie"><label>商品标题</label><input name="title"
					type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
				<li><label>商品类型</label><select name="category"
					style="width: 120px;">
						<%
							String sqlString = "Select category From commodity Group By category Having Count(*)>1";

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
						<option value="<%=row.get("category").toString().trim()%>"><%=row.get("category").toString().trim()%></option>


						<%
							}
						%>


				</select></li>
				<li class="tdleibie"><label>商品数量</label><input name="amount"
					type="text" class="dfinput" /><i></i></li>
				<li class="tdleibie"><label>商品价格</label><input name="price"
					type="text" class="dfinput" /><i></i></li>
				<li>上传图片 <input type="file" name="myfile" /><br></li>
				<li><label>&nbsp;</label><input name="" type="submit"
					class="btn" value="添加" /></li>
			</ul>
		</div>
	</form>
</body>
</html>
