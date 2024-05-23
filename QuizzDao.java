/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.dao;

import com.shiv.bean.QuizzBean;
import com.shiv.utility.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shive
 */
public class QuizzDao {
    
    static Connection conn;
    
    public int addQuestion(String tablename,QuizzBean qb)
    {
        conn=ConnectionPool.connectDb();
        int r=0;
        String sql="insert into "+tablename+" (question,opt1,opt2,opt3,opt4,ans,marks) values(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,qb.getQuestion());  
            pstmt.setString(2,qb.getOpt1()); 
            pstmt.setString(3,qb.getOpt2());
            pstmt.setString(4,qb.getOpt3());
            pstmt.setString(5,qb.getOpt4());
            pstmt.setString(6,qb.getAnswer());
            pstmt.setInt(7,qb.getMarks());

            r=pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuizzDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public ArrayList<QuizzBean> getQuestionByTable(String table) 
    {
        conn=ConnectionPool.connectDb();
        ArrayList<QuizzBean>al=new ArrayList<>();
        String sql="select *from "+table;
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                QuizzBean qb=new QuizzBean();
                qb.setQuestion(rs.getString("question"));
                qb.setOpt1(rs.getString("opt1"));    
                qb.setOpt2(rs.getString("opt2"));
                qb.setOpt3(rs.getString("opt3"));
                qb.setOpt4(rs.getString("opt4"));
                qb.setAnswer(rs.getString("ans"));
                qb.setMarks(rs.getInt("marks"));
                al.add(qb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizzDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    public static void main(String[] args) {
//        QuizzBean qb=new QuizzBean();
//        qb.setQuestion("fjvntbb");
//        qb.setOpt1("eff"); 
//        qb.setOpt2("eff");
//        qb.setOpt3("eff");
//        qb.setOpt4("eff");
//        qb.setAnswer("opt2");
//        qb.setMarks(5);
//        
//        QuizzDao qd=new QuizzDao();
//        int r=qd.addQuestion("quiz_222", qb);
//        if(r>0)
//        {
//            System.out.println("Added");
//        }
//        else
//        {
//            System.out.println("NNN");
//        }

//       QuizzDao qd=new QuizzDao();
//       ArrayList<QuizzBean>al=qd.getQuestionByTable("quiz_9131");
//       for(QuizzBean qb:al)
//       {
//           System.out.println(""+qb.getQuestion()+"\t"+qb.getOpt1()+"\t"+qb.getAnswer());
//       }
    }
}
