<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/list" method="post">
    ⽤用户1编号：<input type="text" name="users[0].id"/><br/>
    ⽤用户1名称：<input type="text" name="users[0].name"/><br/>
    ⽤用户2编号：<input type="text" name="users[1].id"/><br/>
    ⽤用户2名称：<input type="text" name="users[1].name"/><br/>
    ⽤用户3编号：<input type="text" name="users[2].id"/><br/>
    ⽤用户3名称：<input type="text" name="users[2].name"/><br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
