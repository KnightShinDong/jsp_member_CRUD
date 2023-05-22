<%--
  Created by IntelliJ IDEA.
  User: akdls
  Date: 2023-05-21
  Time: 오후 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Join</title>
</head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<body>
<form action="/JoinOk" method="post" enctype="application/x-www-form-urlencoded">
  아이디 : <input type="text" name="id"size="10">
  비밀번호 : <input type="password" name="pw"size="10">
  이름 : <input type="text" name="name"size="10">
  전화번호 : <input type="text" name="phone"size="10">
  <input type="submit" value="회웝가입">
  <input type="reset" value="최소">
</form>
</body>
</html>
