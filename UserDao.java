/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.dao;

import com.shiv.bean.UserBean;
import com.shiv.utility.ConnectionPool;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;


public class UserDao {
    static Connection conn;
    
    public int registerUser(UserBean ub)
    {
        conn=ConnectionPool.connectDb();
        int r=0;
        String sql="insert into user (uname,username,password) values(?,?,?)";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,ub.getUname());
            pstmt.setString(2,ub.getUsername());
            pstmt.setString(3,ub.getPassword());
            r=pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public int loginCheck(String un,String ps,HttpSession session)
    {
        conn=ConnectionPool.connectDb();
        int uid=0;
        String sql="select uid,uname,username from user where username=? and password=?";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, un);
            pstmt.setString(2,ps);
            
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                uid = rs.getInt("uid");
            String uname = rs.getString("uname");
            String username = rs.getString("username");

            // Create a UserBean and store it in the session
            UserBean user = new UserBean();
            user.setUid(uid);
            user.setUname(uname);
            user.setUsername(username);
            session.setAttribute("us", user);
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uid;
    }
    
    
    
    public static void main(String[] args) {
        
//        UserBean ub=new UserBean();

//        ub.setUname("Shivendra Patel");
//        ub.setUsername("shiv1922");
//        ub.setPassword("Vaque.90@");
//        
//        UserDao ud=new UserDao();
//        int r=ud.registerUser(ub);
//        if(r>0)
//        {
//            System.out.println("Registered");
//        }
//        else
//        {
//            System.out.println("Not");
//        }

//      UserDao ud=new UserDao();
//      int r=ud.loginCheck("shiv1922", "Vaque.90@");
//      if(r>0)
//      {
//          System.out.println("Success");
//      }
//      else
//      {
//          System.out.println("not");
//      }

//        UserDao ud=new UserDao();
//        int r=ud.findLastId();
//        System.out.println(":"+r);
    }
}
