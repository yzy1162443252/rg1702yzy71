<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: 666
  Date: 2019/11/29
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login!change" method="post">
    用&nbsp;户&nbsp;名：<input type="text" name="userin.username" value=<s:property value="#session.username"/>><br>
    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="userin.password" value=<s:property value="#session.password"/>><br>
    姓名：<input type="text" name="userin.name" value=<s:property value="#session.name"/>><br>
    年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄： <input type="text" name="userin.age" value=<s:property value="#session.age"/>><br>
    出 生 日 期:<input type="date" name="userin.birthday" value=<s:property value="#session.birthday"/>><br>
    性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="radio" value="男" name="userin.sex" checked="checked" >男<input type="radio" value="女" name="userin.sex" >女<br>
    学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校：<input type="text" name="userin.school" value=<s:property value="#session.school"/>><br>
    专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：<select id="major" name="userin.major" value=<s:property value="#session.major"/>>
    <option value="1">软件工程</option>
    <option value="0">计算机科学与技术</option>
</select>
    <input type="submit" value="保存"  />
</form>
</body>
</html>
