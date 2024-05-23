/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shiv.controller;

import com.shiv.bean.RecordsBean;
import com.shiv.bean.UserBean;
import com.shiv.dao.RecordsDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shive
 */
public class YourResultServlet extends HttpServlet {

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

               int size=Integer.parseInt(request.getParameter("size"));
               int correct=0;
               int total=0;
               int totalmarks=0;
              String code=request.getParameter("code");
               for(int i=0;i<size;++i)
               {
                   int marks=0;
                   String answer="";
                   if(request.getParameter("opt"+i)!=null)
                   {
                   answer=request.getParameter("opt"+i);
                   }
                   String ans=request.getParameter("ans"+i);
                   marks=Integer.parseInt(request.getParameter("marks"+i));
                   totalmarks+=marks;
                   if(answer.equals(ans))
                   {
                       correct++;
                       total+=marks;
                   }
               }
              
               out.println("<h1 align='center'>Correct:"+correct+"/"+size+"</h1>");  
               out.println("<h1 align='center'>Obtained:"+total+"</h1>");   
               out.println("<h1 align='center'>Total Marks:"+totalmarks+"</h1>"); 
               
               
               HttpSession session = request.getSession();
        UserBean ub = (UserBean) session.getAttribute("us");
        if (ub == null) {
            // User is not logged in; handle accordingly
            response.sendRedirect("Login.jsp");
            return;
        }
        
        String name=ub.getUname();
        
        RecordsBean rb=new RecordsBean();
        rb.setName(name);
        rb.setQuizz_codes(code);
        rb.setObtained(total);
        rb.setTotal(totalmarks);
        rb.setCorrect(correct);
        RecordsDao rd=new RecordsDao();
        
        int r=rd.addRecord(rb);
        if(r>0)
        {
        
        out.println("<h1 align='center' color='red' border='1px'><a href='Webquizz.jsp'>Exit</a></h1>");
        }
        else
        {
            out.println("<h1 align='center'>Record Saved failed....please take screenshot</h1>");
            out.println("<h1 align='center' color='red' border='1px'><a href='Webquizz.jsp'>Exit</a></h1>");
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
