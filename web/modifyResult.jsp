<%--
  Created by IntelliJ IDEA.
  User: akdls
  Date: 2023-05-22
  Time: 오후 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modifyOk</title>
</head>
<body>
  <%= session.getAttribute("name")%> 님의 회원정보 수정이 정상 처리<br />
  <a href="logout.jsp">로그아웃</a> &nbsp;&nbsp; <a href="modify.jsp">정보수정</a>
</body>
</html>
