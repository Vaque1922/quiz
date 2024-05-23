/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.dao;

import com.shiv.bean.RecordsBean;
import com.shiv.utility.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author shive
 */
public class RecordsDao {
    static Connection conn;
    
    public int addRecord(RecordsBean rb)
    {
        conn=ConnectionPool.connectDb();
        int r=0;
        String sql="insert into records (name,quizz_code,obtained,correct,total) values(?,?,?,?,?)";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,rb.getName());  
            pstmt.setString(2,rb.getQuizz_codes());
            pstmt.setInt(3,rb.getObtained());     
            pstmt.setInt(4,rb.getCorrect());
            pstmt.setInt(5,rb.getTotal());
            r=pstmt.executeUpdate();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(RecordsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public ArrayList<RecordsBean> getRecordsByCode(String code)
    {
        conn=ConnectionPool.connectDb();
        ArrayList<RecordsBean>al=new ArrayList<>();
        String sql="select *from records where quizz_code="+code+" order by obtained desc";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                
                RecordsBean rb=new RecordsBean();
                rb.setName(rs.getString("name"));   
                rb.setQuizz_codes(rs.getString("quizz_code"));
                rb.setObtained(rs.getInt("obtained"));    
                rb.setCorrect(rs.getInt("correct"));
                rb.setTotal(rs.getInt("total"));
                al.add(rb);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecordsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    
    
    public int getIdByCode(String code)
    {
        conn=ConnectionPool.connectDb();
        int r=0;
        String sql="select id from records where quizz_code="+code;
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                r=rs.getInt("id");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RecordsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    public static void main(String[] args) {
//        RecordsBean rb=new RecordsBean();
//        rb.setName(" P");
//        rb.setQuizz_codes("578");
//        rb.setObtained(5);
//        rb.setCorrect(1);
//        rb.setTotal(10);
//        
//        RecordsDao rd=new RecordsDao();
//        int r=rd.addRecord(rb);
//        if(r>0)
//        {
//            System.out.println("Added");
//        }
//        else
//        {
//            System.out.println("not");
//        }

//         RecordsDao rd=new RecordsDao();
//         
//         ArrayList<RecordsBean>al=rd.getRecordsByCode("5678");
//         for(RecordsBean rb:al)
//         {
//             System.out.println(""+rb.getName()+"\t"+rb.getQuizz_codes()+"\t"+rb.getObtained());
//         }

//  RecordsDao rd=new RecordsDao();
//  
//  int r=rd.getIdByCode("913115");
//        System.out.println(":"+r);
    }
}
