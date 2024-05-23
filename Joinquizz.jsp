<%@page import="com.shiv.dao.RecordsDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.shiv.bean.QuizzBean"%>
<%@page import="com.shiv.dao.QuizzDao"%>
<%@page import="com.shiv.bean.CodesBean"%>
<%@page import="com.shiv.dao.CodesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link rel="stylesheet" href="JoinQuizzCss.css">
    <%
    if (request.getParameter("code") != null) {
        String table = "";
        int time = 0;
        String code = request.getParameter("code");
        RecordsDao rd=new RecordsDao();
        int r=rd.getIdByCode(code);
        if(r==0)
        {
        CodesDao cd = new CodesDao();
        CodesBean cb = cd.getCode(code);
        if (cb != null) {
            table = cb.getTablename();
            time = cb.getTime();
        } else {
            out.println("Code is Invalid");
        }
    %>
    <script>
        function startTimer(duration, display) {
            var timer = duration, minutes, seconds;
            var interval = setInterval(function () {
                minutes = parseInt(timer / 60, 10);
                seconds = parseInt(timer % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                display.textContent = minutes + ":" + seconds;

                if (--timer < 0) {
                    clearInterval(interval);
                    document.getElementById("quizzForm").submit();
                }
            }, 1000);
        }

        window.onload = function () {
            var timeInMinutes = <%= time %>;
            var timeInSeconds = timeInMinutes * 60;
            var display = document.querySelector('#time');
            startTimer(timeInSeconds, display);
        };
    </script>
</head>
<body>
    <div class="timer" align="center">
             <span id="time">00:00</span>
    </div>
<div class="main">
    <%
        QuizzDao qd = new QuizzDao();
        ArrayList<QuizzBean> al = qd.getQuestionByTable(table);
        int size = al.size();
    %>
    <form id="quizzForm" method="post" action="YourResultServlet?size=<%=size%>&code=<%=code%>">
        <table align="center" border="1">
            <%    
            for (int i = 0; i < al.size(); i++) {
                QuizzBean qb = al.get(i);
            %>
            <div class="question-container">
                <tr>
                    <th><%=qb.getQuestion()%></th>
                </tr>
                <tr>
                    <td><input type="radio" name="opt<%=i%>" value="opt1"><%=qb.getOpt1()%></td>
                </tr>
                <tr>
                    <td><input type="radio" name="opt<%=i%>" value="opt2"><%=qb.getOpt2()%></td>
                </tr>
                <tr>
                    <td><input type="radio" name="opt<%=i%>" value="opt3"><%=qb.getOpt3()%></td>
                </tr>
                <tr>
                    <td><input type="radio" name="opt<%=i%>" value="opt4"><%=qb.getOpt4()%></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="ans<%=i%>" value="<%=qb.getAnswer()%>"></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="marks<%=i%>" value="<%=qb.getMarks()%>"></td>
                </tr>
            </div>
            <%
            }
        }
        else
        {
          out.println("<h2>Already Given</h1>");
          response.sendRedirect("Webquizz.jsp");
        }
}
        %>
        </table>
        <br>
        <br>
        <input class="submit" type="submit" value="Submit">
    </form>
</div>
</body>
</html>