<%--
  Created by IntelliJ IDEA.
  User: akdls
  Date: 2023-05-22
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String sessionName, sessionId, sessionPw; %>

<html>
<head>
    <title>LoginResult</title>
</head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<body>

<%
    sessionName = (String)session.getAttribute("name");
    sessionId = (String)session.getAttribute("id");
    sessionPw = (String)session.getAttribute("pw");
%>

  <h1> <%= sessionName%>님 로그인에 성공하셨습니다.</h1>
<a href="modify.jsp">회원정보수정</a>
</body>
</html>
