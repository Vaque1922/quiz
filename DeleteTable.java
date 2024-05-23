package com.shiv.controller;

import com.shiv.utility.ConnectionPool;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTable extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String code = request.getParameter("code_id");
        
        try (PrintWriter out = response.getWriter();
             Connection conn = ConnectionPool.connectDb()) {

            int r = 0;
            int t = 0;
            int s=0;
            // SQL to drop the quiz table
            String sql = "DROP TABLE IF EXISTS quiz_" + code;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                r = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTable.class.getName()).log(Level.SEVERE, null, ex);
                out.println("Error while dropping the table: " + ex.getMessage());
                return;
            }

            // SQL to delete the code entry from the codes table
            sql = "DELETE FROM codes WHERE code = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, code);
                t = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTable.class.getName()).log(Level.SEVERE, null, ex);
                out.println("Error while deleting the code entry: " + ex.getMessage());
                return;
            }
            
              sql = "DELETE FROM records WHERE quizz_code = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, code);
                t = pstmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTable.class.getName()).log(Level.SEVERE, null, ex);
                out.println("Error while deleting the code entry: " + ex.getMessage());
                return;
            }
            
            if (t > 0) {
                response.sendRedirect("Yoursession.jsp");
            } else {
                out.println("Table dropped, but no code entry was deleted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteTable.class.getName()).log(Level.SEVERE, null, ex);
            try (PrintWriter out = response.getWriter()) {
                out.println("Error while connecting to the database: " + ex.getMessage());
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
        return "Servlet for deleting a quiz table and its code entry.";
    }
}
