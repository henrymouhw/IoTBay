 <%@page import="iot.models.*"%>
<%-- 
    Document   : edit
    Created on : 2021-4-11, 14:43:36
    Author     : Think
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Page</title>
    </head>
    <body onload="startTime()">
        <%
            User user = (User)session.getAttribute("user");
            String updated = (String) session.getAttribute("updated");
        %>
        
        <div align="right">
            <a class="button" href="Logout.jsp"> Logout </a>
        </div>
         
        <center>
        <h1>Edit Student Information!<span> <%= (updated != null) ? updated :"" %> </span></h1>
        <form method="post" action="EditServlet">
              <input type="hidden" name="updated" value="updated">
            <table>
                <tr><td>Full Name:</td><td><input type="text" name="name" value="${user.userName}"></td></tr>
                <tr><td>Password:</td><td><input type="password"  name="password" value="${user.password}"></td></tr>
                <tr><td>Date of Birth:</td><td><input type="date"  name="dob" value="${user.dob}"></td></tr>
                <tr><td><input type="hidden" name="email" value="${user.email}"></td></tr>
                <tr><td>   <input type="submit" value="Update"> </td>
                    
                </tr>
            </table>
        </form>
              <a class="button" href="EditServlet?option=cancel">Deactivate</a>
              <a class="button" href="Main.jsp">Main</a>
              </center>
    </body>
</html>
