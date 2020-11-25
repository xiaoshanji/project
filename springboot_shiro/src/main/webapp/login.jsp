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
    <h1>系统登录页</h1>
    <form action="${pageContext.request.contextPath}/shiro-user/login" method="post">
        用户名：<input type="text" name="username" /><br />
        密码：<input type="password" name="password" /><br />
        验证码：<input type="text" name="code" /><img src="${pageContext.request.contextPath}/shiro-user/getImage" /><br />
        <input type="submit" value="登录" />
    </form>
</body>
</html>
