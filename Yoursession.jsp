<%-- 
    Document   : Yoursession
    Created on : 19 May, 2024, 4:41:50 PM
    Author     : shive
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.shiv.bean.CodesBean"%>
<%@page import="com.shiv.dao.CodesDao"%>
<%@page import="com.shiv.bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="YoursessionCss.css">

    </head>
    <body>
        <div class="main">
        <table border="2">
            <tr>
                <th>Codes</th>
                <th>Table</th>
                <th>Delete</th>
                <th>Records</th>
            </tr>
        <%
        UserBean ub = (UserBean) session.getAttribute("us");
        if (ub == null) {
            // User is not logged in; handle accordingly
            response.sendRedirect("Login.jsp");
            return;
        }
        
        int owner_id = ub.getUid();
        CodesDao cd=new CodesDao();
         
        ArrayList<CodesBean>al=cd.getTableByOwnerId(owner_id);
        for(CodesBean cb:al)
        {
        %>
        <tr>
            <td><%=cb.getCode()%></td>
            <td><%=cb.getTablename()%></td>
            <td><span><a href="DeleteTable?code_id=<%=cb.getCode()%>">Delete</a></span></td>
            <td><span><a href="Records.jsp?quizz_code=<%=cb.getCode()%>">Records</a></span></td>
        </tr>
        <%
         }
        %>
        </table>
        </div>
    </body>
</html>
