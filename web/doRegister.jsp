<%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-3-8
  Time: 下午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册后台</title>
</head>
<body>

<jsp:useBean id="user" class="Mode.User" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="userid"></jsp:setProperty>
<jsp:setProperty name="user" property="username"></jsp:setProperty>
<jsp:setProperty name="user" property="password"></jsp:setProperty>
<jsp:setProperty name="user" property="age"></jsp:setProperty>
<jsp:setProperty name="user" property="sex"></jsp:setProperty>
<jsp:setProperty name="user" property="birthday"></jsp:setProperty>
<jsp:setProperty name="user" property="home"></jsp:setProperty>
<jsp:setProperty name="user" property="picture"></jsp:setProperty>

用户ｉｄ<%=user.getUserid()%>
用户名：<%=user.getUsername()%>
密码：<%=user.getPassword()%>
年龄:<%=user.getAge()%>
性别：<%=user.getSex()%>
生日：<%=user.getBirthday()%>
住址：<%=user.getHome()%>
头像图片名称：<%=user.getPicture()%>
</body>
</html>
