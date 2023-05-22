package com.sdh.ex;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;


@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection connection;
    private PreparedStatement pstmt;

    private String name, id, pw, phone;

    HttpSession httpSession;

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
        httpSession = request.getSession();

        id = (String) httpSession.getAttribute("id"); // 세션에 저장된 ID 사용
       // id = request.getParameter("id");
        name = request.getParameter("name");
        pw = request.getParameter("pw");
        phone = request.getParameter("phone");

        if (pwConfirm()) {
            System.out.println("ok");
            String query =  "UPDATE ttest SET name=?, phone=? WHERE id=?";


            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "1234");
                pstmt = connection.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, phone);
                pstmt.setString(3, id);


                int i = pstmt.executeUpdate();
                // prepareStatement(query)를 통해 쿼리가 들어있는 PreparedStatement 객체를 생성하였기 때문에
                // 변수로 executeUpdate()안에 query를 줄필요가 없다. 만약 주면 직접문자열을 전달하기 때문에
                // sql문법오류가 발생한다. ?가 그대로 sql로 들어간다는 뜻!
                //Statement는 매번 실행할때마다 쿼리를 작성하여 전달, 컴파일오류감지불가 및 인젝션공격노출
                //prepareStatement는 미리 준비된 매게변수를 사용하여 동적쿼리형성


                if (i == 1) {
                    System.out.println("update success");
                    httpSession.setAttribute("name", name);
                    response.sendRedirect("modifyResult.jsp");
                } else {
                    System.out.println("update fail");
                    response.sendRedirect("modify.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {}
            }
        } else {
            System.out.println("NG");
        }
    }

    private boolean pwConfirm() {
        String pw1 = (String) httpSession.getAttribute("pw");
        return pw1.equals(pw);
    }

//    private boolean pwConfirm() {
//        boolean rs = false;
//        String sessionPw = (String) httpSession.getAttribute("pw");
//
//        if (sessionPw.equals(pw)) {
//            rs = true;
//        } else {
//            rs = false;
//        }
//        return rs;
//    }
}

