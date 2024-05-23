v<%-- 
    Document   : Records
    Created on : 20 May, 2024, 8:59:20 PM
    Author     : shive
--%>

<%@page import="com.shiv.bean.RecordsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shiv.dao.RecordsDao"%>
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
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Code</th>
                    <th>Correct</th>
                    <th>Obtained Marks</th>
                    <th>Total Marks</th>
                </tr>
                <%
                  String code=request.getParameter("quizz_code");
                  RecordsDao rd=new RecordsDao();
                  ArrayList<RecordsBean>al=rd.getRecordsByCode(code);
                  for(RecordsBean rb:al)
                 {
                %>
                <tr>
                    <td><%=rb.getName()%></td> 
                    <td><%=rb.getQuizz_codes()%></td>
                    <td><%=rb.getCorrect()%></td>
                    <td><%=rb.getObtained()%></td>
                    <td><%=rb.getTotal()%></td>
                </tr>
                <%
                 }
                %>
            </table>
        </div>
        
    </body>
</html>
