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
        out.print("图片上传完成，请检查您的目录");
    } else{
        out.print("图片没有找到");
    }
%>
</body>
</html>