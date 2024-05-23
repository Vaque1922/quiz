/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.dao;

import com.shiv.bean.CodesBean;
import com.shiv.utility.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shive
 */
public class CodesDao {
    static Connection conn;
    
    public int saveCode(CodesBean cb)
    {
        conn=ConnectionPool.connectDb();
        String sql="insert into codes (code,tablename,owner_id,time) values(?,?,?,?)";
        int r=0;
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,cb.getCode());   
            pstmt.setString(2,cb.getTablename());
            pstmt.setInt(3,cb.getOwner_id()); 
            pstmt.setInt(4,cb.getTime());


            r=pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CodesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public CodesBean getCode(String code)
    {
        conn=ConnectionPool.connectDb();
        String sql="select *from codes where code=?";
        CodesBean cb=new CodesBean();
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,code);
            
            ResultSet rs=pstmt.executeQuery();
            if(rs.next())
            {
                cb.setId(rs.getInt("id"));
                cb.setCode(rs.getString("code"));
                cb.setTablename(rs.getString("tablename")); 
                cb.setOwner_id(rs.getInt("owner_id"));
                cb.setTime(rs.getInt("time"));

                return cb;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CodesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ArrayList<CodesBean> getTableByOwnerId(int owner_id)
    {
        conn=ConnectionPool.connectDb();
        ArrayList<CodesBean>al=new ArrayList<>();
        String sql="select code,tablename from codes where owner_id=?";
        
        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,owner_id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next())
            {
                CodesBean cb=new CodesBean();
                cb.setCode(rs.getString("code"));
                cb.setTablename(rs.getString("tablename"));
                al.add(cb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CodesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return al;
    }
    
    
    
    
    
    public static void main(String[] args) {
        
//        CodesDao cd=new CodesDao();
//        ArrayList<CodesBean>al=cd.getTableByOwnerId(1);
//        for(CodesBean cb:al)
//        {
//            System.out.println(""+cb.getCode()+"\t"+cb.getTablename());
//        }
    }
}
