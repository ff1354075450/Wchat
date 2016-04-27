<%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-2-29
  Time: 上午9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录界面</title>
    <%--添加css样式--%>
    <link href="css/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function close(){
            alert("关闭窗口");
        }
    </script>
</head>
<body onbeforeunload="close()">
<div class="container">

    <div class="login">
        <div class="login-screen">
            <%--邮箱图标--%>
            <div class="login-icon">
                <img src="image/login/icon.png" alt="Welcome to Mail App">
                <h4>Welcome to <small>Mail App</small></h4>
            </div>

            <div class="login-form">
                <form action="LoginServlet" method="post">
                    <div class="control-group">
                        <input type="text" class="login-field" value="" placeholder="Enter your name" id="login-name" name="userid">
                        <label class="login-field-icon fui-man-16" for="login-name"></label>
                    </div>

                    <div class="control-group">
                        <input type="password" class="login-field" value="" placeholder="password" id="login-pass" name="password">
                        <label class="login-field-icon fui-lock-16" for="login-pass"></label>
                    </div>

                    <input type="submit" class="btn" value="Login"/>
<%--注册一个新账号*--%>
                    <a class="login-link" href="register.jsp">Register a new account</a>
                </form>
            </div>

        </div>
    </div>

</div>
</body>
</html>
