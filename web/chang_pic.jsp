<%--
  Created by IntelliJ IDEA.
  User: 666
  Date: 2019/12/5
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="pic_action.action" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <input type="submit" value="上传">
</form>
</body>
</html>
