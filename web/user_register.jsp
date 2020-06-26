<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<body>
<form action="login!register" method="post">
    用&nbsp;户&nbsp;名：<input type="text" name="userin.username" /><br>
    密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="userin.password"/><br>
    确认密码：<input type="password" name="userin.repass"/><br>
    姓名：<input type="password" name="userin.name"/><br>
    年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄： <input type="text" name="userin.age"  /><br>
    出 生 日 期:<input type="date"value="1999-09-09" name="userin.birthday"><br>
    性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="radio" value="男" name="userin.sex" checked="checked" >男<input type="radio" value="女" name="userin.sex" >女<br>
    学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校：<input type="text" name="userin.school"/><br>
    专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：<select id="major" name="userin.major">
    <option value="1">软件工程</option>
    <option value="0">计算机科学与技术</option>
</select>
    <input type="submit" value="注册"  />
</form>
</body>
</html>