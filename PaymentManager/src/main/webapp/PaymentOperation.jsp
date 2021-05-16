

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iot.models.Order"%>
<%@page import="iot.models.OrderItem"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
        <h1>Payment Operation</h1>
        <a href="Main.jsp">Back to Main Page</a>
         <% 
           LinkedList<Order> orders = (LinkedList<Order>) session.getAttribute("submitOrders");
         
          for(Order order : orders) {
       %>
        <br><br><br>
       <table border="1" width="70%">
           <caption> Order No: <%=order.getId()%> </caption>
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Quantity</th>
                <th>price</th>
            </tr>
         <% 
           LinkedList<OrderItem> items = order.getItems();
           int id = 1;
         %>
            <% for(OrderItem item : items) { %>
            <tr>
                <th><%= id++%> </th>
                <th><%= item.getItemName()%></th>
                <th><%= item.getItemPrice()%></th>
                <th><%= item.getQuantity()%> </th>
                <th><%= item.total()%></td>
           </tr>
            <%}%>
            <tr>
               <th  align="center" colspan="5">   OrderStatus:<span style="color: red"><%=" "+order.getStatus()%></span></th>
            </tr>
            <tr>
                 <th  align="center" colspan="5"> OrderDataL <span style="color: red"> <%= " "+ order.getDate()%></span></th>
            </tr>
            <tr>
                <th  align="center" colspan="5">Total: <%=order.total()%></th>
            </tr>
            <tr>
                <th align="center" colspan="5"> <a href="toPaymentServlet?orderId=<%=order.getId()%>">Go to Payment</a> </th>
            </tr>
            
           </table>
            
       <%}%>
        
        
        </center>
    </body>
</html>
