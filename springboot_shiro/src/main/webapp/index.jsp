<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <h1>系统主页</h1>
    <a href="${pageContext.request.contextPath}/shiro-user/logout">退出</a>


    <ul>
        <shiro:hasAnyRoles name="user,admin">
            <li><a href="">用户管理</a>
                <ul>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href="">添加</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:find:*">
                        <li><a href="">查询</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href="">修改</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href="">删除</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>


        <shiro:hasAnyRoles name="order">
            <li><a href="">订单管理</a>
                <ul>
                    <shiro:hasPermission name="order:add:*">
                        <li><a href="">添加</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:find:*">
                        <li><a href="">查询</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:update:*">
                        <li><a href="">修改</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:delete:*">
                        <li><a href="">删除</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>

        <shiro:hasRole name="admin">
            <li><a href="">菜单管理</a></li>
            <li><a href="">角色管理</a></li>
            <li><a href="">信息管理</a></li>
        </shiro:hasRole>
    </ul>
</body>
</html>