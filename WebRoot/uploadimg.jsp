<%@page import="com.eric.UploadImage"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传</title>
</head>
<body>
<%
    String imgEncodedStr = request.getParameter("image");
    String fileName = request.getParameter("filename");
    System.out.println("Filename: "+ fileName);
    if(imgEncodedStr != null){
        UploadImage.convertStringtoImage(imgEncodedStr, fileName);
        out.print("Image upload complete, Please check your directory");
    } else{
        out.print("文件是空的");
    }
%>
</body>
</html>