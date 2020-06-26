<%--
  Created by IntelliJ IDEA.
  User: 666
  Date: 2019/12/20
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="chang_pic.jsp"><img src="sculpture/<s:property value="#session.no"/>.jpg" width="80" height="80"/></a>
<a href=try_login.jsp>注销</a>
<a href="apply_action">好友请求</a><br>
<a href="friend_show_action">好友列表</a><br>
<br>
<table border="1" bgcolor="#ffffff" align="center" cellpadding="1">
    <tr>
        <td>头像</td>
        <td>编号</td>
        <td>姓名</td>
        <td>性别</td>
        <td>地址</td>
        <td>生日</td>
    </tr>
    <s:iterator value="#session.friend_list" var="tr">
        <tr>
            <td><img src="sculpture/<s:property value="#session.no"/>.jpg" width="80" height="80"></td>
            <td><s:property value="#tr.id"/></td>
            <td><a href="own_action.action?shan=<s:property value="#tr.shop"/>"><s:property value="#tr.name"/></a></td>
            <td><s:property value="#tr.sex"/> </td>
            <td><s:property value="#tr.address"/></td>
            <td><s:property value="#tr.birthday"/></td>
            <td><a href="friend_show_action!delete?shan=<s:property value="#tr.id"/>&shan2=<s:property value="#tr.name"/>"> 删除 </a></td>
        </tr>
    </s:iterator>
</table>
<form action="friend_action!find" method="post">用户查找:<input type="text" name="back" /><input type="submit"/> </form>

<s:debug></s:debug>
</body>
</html>
