<%-- 
    Document   : Login
    Created on : 17 May, 2024, 11:24:48 PM
    Author     : shive
--%>

<%@page import="java.io.IOException"%>
<%@page import="com.shiv.dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="loginCss.css">
    </head>
    <body>
        <div class="main">
            <form class="login_menu">
                <h2>Login</h2>
                Enter Username<br>
                <input type="text" name="username"><br><br>
                Enter Password<br>
                <input type="password" name="password"><br><br>
                <input type="submit" value="Login">
                <br><br>
                <span>Or&nbsp;<a href="Register.jsp">Register to login</a></span>
            </form>
        </div>
        
        <%
            int r=0;
            
        if(request.getParameter("username")!=null&&request.getParameter("password")!=null)
        {
            String un=request.getParameter("username");
            String ps=request.getParameter("password");
            
            UserDao ud=new UserDao();
            
            r=ud.loginCheck(un, ps,session);
            if(r>0)
            {
                HttpSession s=request.getSession();
                s.setAttribute("user",un);
                RequestDispatcher rd=request.getRequestDispatcher("Webquizz.jsp");
                rd.forward(request, response);
            }
            else
            {
                RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
        }
        
        %>
    </body>
</html>
