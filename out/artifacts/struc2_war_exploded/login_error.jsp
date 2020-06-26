<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
登录失败！username<s:property value="#session.username"/>||password<s:property value="#session.password"/>
||name<s:property value="#session.name"/>||birthday<s:property value="#session.birthday"/>
||age<s:property value="#session.age"/>||sex<s:property value="#session.sex"/>
||school<s:property value="#session.school"/>||major<s:property value="#session.major"/>||
</body>
</html>
