<%-- 
    Document   : index
    Created on : 2021年5月8日, 下午6:24:04
    Author     : lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
 
    <body>
        <a href="CListServlet?"> List</a><br>
        <a href="ToAddCustServlet"> Add Customer</a>
            <jsp:include page="/ConnServlet" flush="true" />  
    </body>
</html>
