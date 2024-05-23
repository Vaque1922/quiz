<%-- 
    Document   : Register.jsp
    Created on : 17 May, 2024, 11:42:25 PM
    Author     : shive
--%>

<%@page import="com.shiv.dao.UserDao"%>
<%@page import="com.shiv.bean.UserBean"%>
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
                <h2>Register</h2>
                Enter Name<br>
                <input type="text" name="uname"><br><br>
                Enter Username<br>
                <input type="text" name="username"><br><br>
                Enter Password<br>
                <input type="password" name="password"><br><br>
                <input type="submit" value="Register">
                <br><br>
                <span>Or&nbsp;<a href="Login.jsp">Already have account</a></span>
            </form>
        </div>
        
        <%
            if(request.getParameter("uname")!=null&&request.getParameter("username")!=null&&request.getParameter("password")!=null)
            {
                
                String nm=request.getParameter("uname");
                String un=request.getParameter("username");
                String ps=request.getParameter("password");
                
                UserDao ud=new UserDao();
        
                UserBean ub=new UserBean();
                ub.setUname(nm);
                ub.setUsername(un);
                ub.setPassword(ps);
                
                int r=ud.registerUser(ub);
                if(r>0)
                {
                    HttpSession s=request.getSession();
                    s.setAttribute("user",un);
                    RequestDispatcher rd=request.getRequestDispatcher("Webquizz.jsp");
                    rd.forward(request, response);
                }
                else
                {
                    out.println("<h2><font color='red'>Registration failed</font></h2>");
                    RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
                    rd.include(request, response);
                }
                
            }
        
        %>
    </body>
</html>
