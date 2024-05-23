/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.bean;

/**
 *
 * @author shive
 */
public class UserBean {
    
    int uid;
    String uname;
    String username;
    String password;
    

    public UserBean() {
    }

    public UserBean(int uid, String uname, String username, String password) {
        this.uid = uid;
        this.uname = uname;
        this.username = username;
        this.password = password;
        
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
