<%@ page import="java.util.List" %>
<%@ page import="Mode.User" %>
<%@ page import="DB.Friendlist" %>
<%@ page import="java.net.InetAddress" %><%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-2-29
  Time: 下午1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
    <%--添加css样式--%>
    <link href="css/talk.css" rel="stylesheet" type="text/css">
    <link href="css/talk2.css" rel="stylesheet" type="text/css">
    <link href="css/talk3.css" rel="stylesheet" type="text/css">
    <%--加入js文件--%>
    <script type="text/javascript" src="./js/addAnddel.js"></script>
    <script type="text/javascript" src="./js/message.js"></script>
    <%--聊天内容从下往上显示--%>

</head>

<body>

<span id="serviceip" style="display: none"><%=InetAddress.getLocalHost().getHostAddress()%></span>
<a href="logout.jsp" class="exitall" >退出</a>
    <div class="container">

        <div class="container1">


            <div class="c1top">
                <%--<div id="item0"><%=session.getAttribute("picture")%>  </div> 图片先不管--%>
                <a id="item1" href="changepicture.jsp">
                <img width="45"height="45" src=<%=session.getAttribute("picture")%>>
                </a>
                <span id="item2"><%=session.getAttribute("userid")%></span><br>
                <span class="item3">在线
                </span>
            </div>



            <header class="c1header">
                <span id="c1title">
                好友列表
                </span>
            </header>

            <div class="c1fridenlist">
                <%
                    String userid = (String) session.getAttribute("userid");
                    //读取数据库中的用户好友列表
                    Friendlist friendlist = new Friendlist();
                    List<User> list = friendlist.getlist(userid);
                    for (User user:list
                            ) {
                        String picturepath = "./image/"+ user.getPicture();
                        String status=null;
                        if(user.getStatus() == 1){
                            status = "在线";
                        }else {
                            status = "离线";
                        }
                %>

                <div class="c1friend1" onclick="talk(<%=user.getUserid()%>,'<%=user.getUsername()%>')">
                    <img src=<%=picturepath%> class="fitem1"/>
                    <span class="fitem2"><%=user.getUsername()%>  ( <%=user.getUserid()%> )</span><br>
                    <span class="fitem3"><%=status%></span>
                </div>
                <%
                    }
                %>

            </div>

            <footer class="c1footer">
                <ul class="c1ul">
                    <li class="li">
                        <button id="adduser" onclick="adduser()">添加好友</button>
                    </li>
                    <li class="li">
                        <button id="deluser" onclick="deluser()">删除好友</button>
                    </li>
                </ul>
            </footer>
        </div>

        <div class="container2">

            <header class="c2header">
                <p id="c2headertitle" >请选择对象</p>
                <span id="c2talkfriend" style="display: none"></span>
            </header>

            <div class="c2body"></div>
            <%--聊天内容--%>
            <div id="message">

                <div class="chat-content">
                    <img class="chat_tq"  src="./image/444.jpg" width="40px" height="40px">
                    <p class="chat_nick">用户名</p>
                    <p class="chat_right">聊天内容</p>
                </div>

            </div>

            <footer class="c2footer">
                <div class="c2chattool">
                    <div style="display: inline-flex;width: 100%">
                    <div class="bq"></div>
                   <a href="sp.jsp" class="msp">视频</a>
                    </div>
                    <div style="display: inline-flex;width: 100%">
                    <textarea id="c2input"></textarea>
                    <button class="c2button">
                        <span class="c2send" onclick="send(<%=session.getAttribute("userid")%>)">发送</span>
                    </button>
                    </div>
                    <%--<div>1</div>--%>
                    <%--<div>2</div>--%>
                    <%--<div>3</div>--%>
                </div>

            </footer>

        </div>
    </div>

</body>
</html>
