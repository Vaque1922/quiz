<%-- 
    Document   : Webquizz
    Created on : 18 May, 2024, 10:09:48 AM
    Author     : shive
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="WebquizzCss.css">
    </head>
    <body>
        <%
            String user=(String)session.getAttribute("user");
            if(user==null){
                response.sendRedirect("Login.jsp");
            }
        %>
        
        <div class="main">
            <form class="quizz_menu" action="Joinquizz.jsp">
                <h2>Enter Quiz Code</h2>
                <input class="code" type="text" name="code" required><br><br>
                <input class="start_now" type="submit" value="Join Quizz">
            </form>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <form class="quizz_menu2" action="Createquizz">
                <h2>Enter Code</h2>
                <input class="code2" type="text" name="code" required><br><br>
                <h2>Enter Time(min)</h2>
                <input class="code2" type="text" name="time" required><br><br>
                <input class="start_now" type="submit" value="Create Quizz">
            </form>
        </div>
        <div class="main2">
            <span><a href="Yoursession.jsp">Your Session</a></span>   
        </div>
    </body>
</html>
