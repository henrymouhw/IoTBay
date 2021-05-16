<%-- 
    Document   : payment
    Created on : 2021-5-10, 17:11:48
    Author     : 74152
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.util.*"%>
<%@page import="java.util.*;"%>
<%@page import="iot.models.*"%>
<html>
    <body>
        <div>Payment</div>
       
      
                <table border="1">
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Item Price</th>
                <th>Quantity</th>
                <th>price</th>
            </tr>
         <% Order order = (Order)session.getAttribute("orderToPay");
           int oId = order.getId();
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
            </tr>
            <%}%>
            <tr>
                <td colspan="5"> Total: <%= order.total()%> </td>
                </tr>
             <%}%>
           </table>
        
        
        
        <form method="post" action="AddPaymentServlet">
            <table class="table">
                <tr>
                <td>Payment method: </td>
                <td><input type="radio" name="paymethod" value="Credit Card">Credit Card
                    <input type="radio" name="paymethod" value="Debit Card">Debit Card</td>
                </tr>
                <tr><td>Card number:</td><td><input type="text"  name="cardnumber" required></td></tr>
                <tr><td>Expiry date:</td><td><input type="text"  name="expirydate" required></td></tr>
                <tr><td>cvv:</td><td><input type="text"  name="cvv" required></td></tr>
                <tr><td>Fullname:</td><td><input type="text"  name="fullname" required></td></tr>
                <tr><td><input type="submit" value="pay" ></td></tr>
            </table>
            <br><br>
            <a href="Main.jsp">Back to Main</a>
        </form>
    </body>
</html>
