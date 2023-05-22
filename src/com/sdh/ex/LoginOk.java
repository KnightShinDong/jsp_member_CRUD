package com.sdh.ex;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {

    private static final long serialVersionUID=1L;

    private Connection conn;
    private PreparedStatement pstmt;
    private String name, id, pw, phone;
    ResultSet resultSet;

    public LoginOk(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionDo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        actionDo(req, resp);
    }

    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        id = request.getParameter("id");
        pw = request.getParameter("pw");

        String query = "SELECT * FROM ttest WHERE id = ?";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","1234" );
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String pw1 = resultSet.getString("pw");
                if (pw.equals(pw1)) {
                    // 비밀번호가 일치하는 경우에만 로그인 처리
                    name = resultSet.getString("name");
                    phone = resultSet.getString("phone");

                    // 성공시 세션에 저장하기
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("id", id);
                    httpSession.setAttribute("pw", pw);
                    httpSession.setAttribute("name", name);

                    response.sendRedirect("loginResult.jsp");
                } else {
                    // 비밀번호가 일치하지 않는 경우
                    response.sendRedirect("loginFail.jsp");
                }
            } else {
                // 사용자가 존재하지 않는 경우
                response.sendRedirect("loginFail.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}


//package com.sdh.ex;
//
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.*;
//
//@WebServlet("/LoginOk")
//public class LoginOk extends HttpServlet {
//
//    private static final long serialVersionUID=1L;
//
//    private Connection conn;
//    private Statement stmt;
//    private String name,id,pw,phone;
//    ResultSet resultSet;
//
//    public LoginOk(){
//        super();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        actionDo(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        actionDo(req,resp);
//    }
//
//    private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        request.setCharacterEncoding("UTF-8");
//
//        id = request.getParameter("id");
//        pw = request.getParameter("pw");
//
//
//        //String query = "SELECT * FROM ttest WHERE id = '"+id+"' and pw = '"+pw+"'";
//        String query = "SELECT * FROM ttest WHERE id = ? and pw = ?";
//
//
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","1234" );
//            //stmt = conn.createStatement();
//            //resultSet = stmt.executeQuery(query);
//            System.out.println(query);
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, "id");
//            pstmt.setString(2, "pw");
//            resultSet = pstmt.executeQuery();
//
//            while (resultSet.next()){
//            id = resultSet.getString("id");
//            pw = resultSet.getString("pw");
//            name = resultSet.getString("name");
//            phone = resultSet.getString("phone");
//            }
//
//            //성공시 세션에 저장하기
//            HttpSession httpSession = request.getSession();
//            httpSession.setAttribute("id" ,id);
//            httpSession.setAttribute("pw",pw);
//            httpSession.setAttribute("name",name);
//
//            response.sendRedirect("loginResult.jsp");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        try {
//            if(resultSet != null) resultSet.close();
//            if(stmt != null) stmt.close();
//            if(conn != null) conn.close();
//            }catch (Exception e2) {
//             e2.printStackTrace();
//            }
//        }
//
//}
