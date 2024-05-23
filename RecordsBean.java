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
public class RecordsBean {
    
    int id;
    String name;
    String quizz_codes;
    int obtained;
    int correct;
    int total;

    public RecordsBean() {
    }

    public RecordsBean(int id, String name, String quizz_codes, int obtained, int correct, int total) {
        this.id = id;
        this.name = name;
        this.quizz_codes = quizz_codes;
        this.obtained = obtained;
        this.correct = correct;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuizz_codes() {
        return quizz_codes;
    }

    public void setQuizz_codes(String quizz_codes) {
        this.quizz_codes = quizz_codes;
    }

    public int getObtained() {
        return obtained;
    }

    public void setObtained(int obtained) {
        this.obtained = obtained;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
