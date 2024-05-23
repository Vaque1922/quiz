package com.shiv.controller;

import com.shiv.bean.CodesBean;
import com.shiv.bean.UserBean;
import com.shiv.dao.CodesDao;
import com.shiv.dao.UserDao;
import com.shiv.utility.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Createquizz extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
     
                      
            HttpSession session = request.getSession();
        UserBean ub = (UserBean) session.getAttribute("us");
        if (ub == null) {
            // User is not logged in; handle accordingly
            response.sendRedirect("Login.jsp");
            return;
        }
        
        int owner_id = ub.getUid();
        String code = request.getParameter("code");
        String tablename = "quiz_" + code;
        int time=Integer.parseInt(request.getParameter("time"));
            try (Connection conn = ConnectionPool.connectDb()) {
                String sql = "CREATE TABLE " + tablename + " ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT, "
                        + "question VARCHAR(225), "
                        + "opt1 VARCHAR(225), "
                        + "opt2 VARCHAR(225), "
                        + "opt3 VARCHAR(225), "
                        + "opt4 VARCHAR(225), "
                        + "ans VARCHAR(45), "
                        + "marks INT)";
                
                try (Statement stmt = conn.createStatement()) {
                    int r = stmt.executeUpdate(sql);
                    if (r == 0) {
                         response.sendRedirect("Createquizz.jsp?id="+code+"");
                    } else {
                        out.println("Table creation failed.");
                    }
                }

                CodesBean cb = new CodesBean();
                cb.setCode(code);
                cb.setTablename(tablename);
                cb.setOwner_id(owner_id);
                cb.setTime(time);
                
                CodesDao cd = new CodesDao();
                int result = cd.saveCode(cb);
            } catch (SQLException ex) {
                Logger.getLogger(Createquizz.class.getName()).log(Level.SEVERE, null, ex);
                out.println("SQL Exception: " + ex.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Createquizz";
    }
}
