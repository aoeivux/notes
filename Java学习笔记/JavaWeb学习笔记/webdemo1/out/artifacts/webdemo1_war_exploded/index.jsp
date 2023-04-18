<%--
  Created by IntelliJ IDEA.
  User: nero
  Date: 2023/4/17
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>
    <a href="/add.jsp">添加</a>
  </h1>
    <table>
      <thead>
        <tr>
          <th>id</th>
          <th>姓名</th>
          <th>性别</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${list}" var="admins">
          <tr>
            <td>${admins.id}</td>
            <td>${admins.name}</td>
            <td>${admins.gender}</td>
            <td>
              <a href="/delete?id=${admins.id}">删除</a>
              <a href="/update.jsp">修改</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>