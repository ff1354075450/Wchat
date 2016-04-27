<%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-3-8
  Time: 下午9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <link href="css/register.css" rel="stylesheet" type="text/css">
    <%--<script>--%>
        <%--function checkname(){--%>
            <%--alert("输出密码")--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>
<script type="text/javascript" src="js/register.js"></script>
<div class="container">
<div class="resigner">

<table >
    <form action="RegisterServlet" method="post">
        <tr>
            <td>用户id</td>
            <td><input type="text" name="userid" onblur="checkname()"></td>
        </tr>

        <tr>
            <td>用户名</td>
            <td><input type="text" name="username" "/></td>
        </tr>

        <tr>
            <td>密码</td>
            <td><input type="password" name="password" ></td>
        </tr>

        <tr>
            <td width="20%">性别　</td>　
            <td width="20%">男<input type="radio" name="sex" value="1" checked>&nbsp;&nbsp;
            女<input type="radio" name="sex" value="2"></td>
        </tr>

        <tr>
            <td>年龄：</td>
            <td><input type="text" name="age" value=""></td>
        </tr>

        <tr>
            <td>出生日期</td>
            <td><input type="text" name="birthday" placeholder="yyyy-MM-dd"> </td>
        </tr>

        <tr>
            <td>居住地</td>
            <td><input type="text" name="home"></td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="确定" >&nbsp;&nbsp;
            <input type="reset" value="取消"></td>
        </tr>

    </form>
</table>
</div>
</div>
</body>
</html>
