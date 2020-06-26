<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>在线交友系统-软工1702-杨宗耀20172202971</h1>
<form action="login.action"  method="post">
    用户名：<input type="text"  name="userin.username" /><br>
    密&nbsp;&nbsp;码：<input type="password"  name="userin.password"/><br>
    <input type="submit" value="登录" />
    <input type="button" value="注册" onclick="window.location='user_register.jsp'" />
</form>
<s:debug></s:debug>
</body>
</html>