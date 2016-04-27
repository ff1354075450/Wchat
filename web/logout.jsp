<%@ page import="DB.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: ff
  Date: 16-3-29
  Time: 下午5:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();
    response.sendRedirect("Login.jsp");
%>