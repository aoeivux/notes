# jsp 小结

## JSP 内置对象 9 个

1. request：表示⼀次请求，HttpServletRequest。
2. response：表示⼀次响应，HttpServletResponse。
3. pageContext：⻚⾯上下⽂，获取⻚⾯信息，PageContext。
4. session：表示⼀次会话，保存⽤户信息，HttpSession。
5. application：表示当前 Web 应⽤，全局对象，保存所有⽤户共享信息，ServletContext。
6. config：当前 JSP 对应的 Servlet 的 ServletConfig 对象，获取当前 Servlet 的信息。
7. out：向浏览器输出数据，JspWriter。
8. page：当前 JSP 对应的 Servlet 对象，Servlet。
9. exception：表示 JSP ⻚⾯发⽣的异常，Exception

常⽤的是 request、response、session、application、pageContext

## request 常⽤⽅法：

1. String getParameter(String key) 获取客户端传来的参数。
2. void setAttribute(String key,Object value) 通过键值对的形式保存数据。
3. Object getAttribute(String key) 通过 key 取出 value。
4. RequestDispatcher getRequestDispatcher(String path) 返回⼀个 RequestDispatcher 对象，该对象的 forward ⽅法⽤于请求转发。
5. String[] getParameterValues() 获取客户端传来的多个同名参数。
6. void setCharacterEncoding(String charset) 指定每个请求的编码

## HTTP 请求状态码

200：正常
404：资源找不到
400：请求类型不匹配
500：Java 程序抛出异常

## response 常⽤⽅法：

sendRedirect(String path) 重定向，⻚⾯之间的跳转。

转发 getRequestDispatcher 和重定向 sendRedirect 的区别：

转发是将同⼀个请求传给下⼀个⻚⾯，重定向是创建⼀个新的请求传给下⼀个⻚⾯，之前的请求结束⽣命周期。

如果两个⻚⾯之间需要通过 request 来传值，则**必须使⽤转发**，不能使⽤重定向。

⽤户登录，如果⽤户名和密码正确，则跳转到⾸⻚（转发），并且展示⽤户名，否则重新回到登陆⻚⾯（重定向）

> 转发：同⼀个请求在服务器之间传递，地址栏不变，也叫服务器跳转。

> 重定向：由客户端发送⼀次新的请求来访问跳转后的⽬标资源，地址栏改变，也叫客户端跳转。
