<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %><%--
  Created by IntelliJ IDEA.
  User: akdls
  Date: 2023-05-22
  Time: 오후 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String sessionId, sessionName, sessionPw, sessionPhone;
%>


<html>
<head>
    <title>회원정보 수정</title>
</head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
<body>
    <%
    sessionId=(String)session.getAttribute("id");

    String query="SELECT * FROM ttest WHERE id = '"+sessionId+"'";

        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","1234" );
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            sessionPw = resultSet.getString("pw");
            sessionName = resultSet.getString("name");
            sessionPhone = resultSet.getString("phone");
        }
    %>
<form action="ModifyOk" method="post">
    이름:<input type="text" name="name" value=<%= sessionName%> size="10">
    아이디: <%= sessionId%><br />
    비밀번호: <input type="text"name="pw"  size="10">
    전화번호:<input type="text" name="phone" value="<%= sessionPhone%>">
    <input type="submit" value="회원정보수정">
    <input type="button" value="취소" onclick="back();">
</form>

</body>
</html>
<script>
    function back(){
        location.href="/BoardList";
    }
</script>