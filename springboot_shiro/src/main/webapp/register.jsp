<%--
  Created by IntelliJ IDEA.
  User: 小杉杉
  Date: 2020/10/27
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>系统注册页</h1>
    <form action="${pageContext.request.contextPath}/shiro-user/register" method="post">
        用户名：<input type="text" name="username" /><br />
        密码：<input type="password" name="password" /><br />
        <input type="submit" value="注册" />
    </form>
</body>
</html>
