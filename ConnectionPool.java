/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    static Connection conn;
    
    public static Connection connectDb()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/quizz";
            String un="root";
            String ps="Vaque.90@";
            
            conn=DriverManager.getConnection(url, un, ps);
            System.out.println("Database connected");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static void main(String[] args) {
        connectDb();
    }
    
}
