package com.sdh.ex;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


@WebServlet("/JoinOk")
public class JoinOk extends HttpServlet {

    private static final long serialVersionUID=1L;

    private Connection conn;
    private Statement stmt;

    private String name, id, pw, phone;
    public JoinOk(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.getRequestDispatcher("/join.jsp").forward(req,resp);
        actionDo(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // System.out.println("id  : " + req.getParameter("id"));
        actionDo(req, resp);
    }

        private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");

        id = request.getParameter("id");
        pw = request.getParameter("pw");
        name = request.getParameter("name");
        phone = request.getParameter("phone");

        String query = "INSERT INTO ttest VALUES('"+id+"','"+pw+"','"+name+"','"+phone + "')";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test","root","1234" );
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(query);
            if (i==1) {
                System.out.println("insert success");
                response.sendRedirect("joinResult.jsp");
            }else {
                System.out.println("insert fail");
                response.sendRedirect("join.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            try {
                if (stmt != null) stmt.close();
                if(conn != null) conn.close();
            }catch (Exception e) {}
        }

    }

}
