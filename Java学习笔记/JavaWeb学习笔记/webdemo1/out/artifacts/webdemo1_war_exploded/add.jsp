<%--
  Created by IntelliJ IDEA.
  User: nero
  Date: 2023/4/17
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
</head>
<body>
<form action="/add" method="post">
    姓名:<input type="text" name="name"><br>
    性别：<input type="text" name="gender"><br>
    <input type="hidden" value="add">
    <input type="submit" value="提交">
</form>
</body>
</html>
