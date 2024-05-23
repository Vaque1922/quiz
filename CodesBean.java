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
public class CodesBean {
    int id;
    String code;
    String tablename;
    int owner_id;
    int time;

    public CodesBean() {
    }

    public CodesBean(int id, String code, String tablename,int owner_id,int time) {
        this.id = id;
        this.code = code;
        this.tablename = tablename;
        this.owner_id=owner_id;
        this.time=time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    } 

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}
