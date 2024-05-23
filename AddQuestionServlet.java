/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.controller;

import com.shiv.bean.CodesBean;
import com.shiv.bean.QuizzBean;
import com.shiv.dao.CodesDao;
import com.shiv.dao.QuizzDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shive
 */
public class AddQuestionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String code=request.getParameter("code");
            
                CodesDao cd=new CodesDao();
                CodesBean cb=cd.getCode(code);
            
                String tablename=cb.getTablename();
            
                String que=request.getParameter("question");
                String opt1=request.getParameter("opt1");
                String opt2=request.getParameter("opt2");
                String opt3=request.getParameter("opt3");
                String opt4=request.getParameter("opt4");
                String ans=request.getParameter("answer");
                int marks=Integer.parseInt(request.getParameter("marks"));
                
                
                QuizzBean qb=new QuizzBean();
                qb.setQuestion(que);
                qb.setOpt1(opt1);
                qb.setOpt2(opt2);
                qb.setOpt3(opt3);
                qb.setOpt4(opt4);
                qb.setAnswer(ans);
                qb.setMarks(marks);
                
                QuizzDao qd=new QuizzDao();
                int r=qd.addQuestion(tablename, qb);
                if(r>0)
                {
                    response.sendRedirect("Createquizz.jsp?code="+code+"");
                }
                else
                {
                    out.println("<h1 align='center'>Not added</h1>");
                }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
