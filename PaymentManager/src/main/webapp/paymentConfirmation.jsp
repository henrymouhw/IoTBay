

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iot.models.Payment"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>PaymentConfirmation</h1>
        <% 
            Payment p = (Payment) session.getAttribute("payment") ;
        %>
        
        <a href="paymentSubmitServlet?id=<%=p.getPayment_id()%>"> Click here to back</a>
    </body>
</html>
