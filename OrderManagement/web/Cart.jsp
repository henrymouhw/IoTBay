

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*;"%>
<%@page import="iot.models.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
    
        <center>
            
        <h1> Cart</h1>
         <a href="Main.jsp" >Back to Main Page</a>
        <table border="1">
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Quantity</th>
                <th>price</th>
                <th>Plus</th>
                <th>Minus</th>
            </tr>
         <% Order order = (Order)session.getAttribute("order");
           if (order!=null){
           LinkedList<OrderItem> items = order.getItems();
           int id = 1;
         %>
            <% for(OrderItem item : items) { %>
            <tr>
                <td><%= id++%> </td>
                <td><%= item.getItemName()%></td>
                <td><%= item.getItemPrice()%></td>
                <td><%= item.getQuantity()%> </td>
                <td><%= item.total()%></td>
                <td align="center"><a href="OrderEditServlet?name=<%=item.getItemName()%>&option=plus"> Plus </a></td>
                <td align="center"><a href="OrderEditServlet?name=<%=item.getItemName()%>&option=minus"> Minus </a></td>
            </tr>
            <%}%>
          <tr>
          <td colspan="7" align="center"><a href="EndOrderServlet?status=cancel">Cancel</a>
          <a href="EndOrderServlet?status=submit">Submit</a></td>
          <tr>
             <%}%>
           </table>
          </center>
     
    </body>
</html>
