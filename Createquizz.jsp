<%-- 
    Document   : Createquizz
    Created on : 19 May, 2024, 11:35:08 AM
    Author     : shive
--%>

<%@page import="com.shiv.dao.QuizzDao"%>
<%@page import="com.shiv.bean.QuizzBean"%>
<%@page import="com.shiv.bean.CodesBean"%>
<%@page import="com.shiv.dao.CodesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="CreatequizzCss.css">
    </head>
    <body>
        <%
            String code="";
            if(request.getParameter("id")!=null)
            {
                code=request.getParameter("id");
            }
            if(request.getParameter("code")!=null)
            {
             code=request.getParameter("code");
            }
        %>
        <div class="main">
            <form class="create_menu" action="AddQuestionServlet">
                <h2>Enter details&nbsp;&nbsp;&nbsp;Code:<%=code%></h2>
                Enter Question:
                <input type="text" name="question"><br><br>    
                <input type="hidden" name="code" value="<%=code%>"><br><br>
                Enter option1:<input type="text" name="opt1"><br> <br> 
                Enter option2:<input type="text" name="opt2"><br> <br>
                Enter option3:<input type="text" name="opt3"><br><br>
                Enter option4:<input type="text" name="opt4"><br><br> 
                Enter Answer(opt):<input type="text" name="answer"><br><br>
                Enter Marks:<input type="text" name="marks"><br><br>
                <input class="add_question" type="submit" value="Add Question"><br><br>
                <span><a href="Webquizz.jsp">End</a></span>
            </form>
        </div>
    </body>
</html>
