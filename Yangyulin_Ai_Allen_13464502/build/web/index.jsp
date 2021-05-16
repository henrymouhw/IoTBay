<%-- 
    Document   : index
    Created on : 2021-4-11, 13:20:32
    Author     : Think
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IoTBay Home Page</title>
    </head>
    <body onload="startTime()">
        <h1>ISD Demo Home Page</h1>
        <div>
            <a href="RegisterPage.jsp" class="button">Register</a>
            <a href="Login.jsp" class="button">Login</a>
        </div>
    
        <jsp:include page="/ConnServlet" flush="true" />  
    </body>
</html>

