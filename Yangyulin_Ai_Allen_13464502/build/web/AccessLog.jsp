<%-- 
    Document   : AccessLog
    Created on : 2021-5-8, 21:17:07
    Author     : Think
--%>

<%@page import="iot.models.*"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Access Log</title>
    </head>
    <body>
        <table>
            <tr>
                <td><p>Email</p></td><td><br><br></td>
                <td><p>Login Date&Time</td><td><br><br></td>
                <td><p>Logout Date&Time</p></td><td><br><br></td>
            </tr>
            <%
                LinkedList<Record> records = (LinkedList<Record>) session.getAttribute("record");
                
                for(Record record: records){
            %>
            <tr>
                <td><%=record.getEmail()%></td><td><br><br></td>
                <td><%=record.getLogin()%></td><td><br><br></td>
                <td><%=record.getLogout()%></td><td><br><br></td>
            </tr>
            <%}%>
        </table>
        <p><a href = "Main.jsp"> Confirm </a></p>
    </body>
</html>
