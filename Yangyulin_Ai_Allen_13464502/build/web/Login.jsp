<%-- 
    Document   : login
    Created on : 2021-5-5, 17:33:19
    Author     : Think
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body onload="startTime();">
          <div><span class="time" id="time"></span></div>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
        %>
            
        <h1> Log in </h1>
        <a href="CancelServlet" class="button"> Cancel </a>
         <span> <%=(existErr != null ? existErr : "") %></span>
        <form action="LoginServlet" method="post">
            <table>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email") %> " name="email" required> </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password") %> " name="password" required> </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input class="button" type="submit" value="Log in">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
