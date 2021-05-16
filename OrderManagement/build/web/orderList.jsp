

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
        <h1>Order Histroy</h1>
        <a href="Main.jsp" >Back to Main Page</a>
        
                <form method="post" action="orderList.jsp">
            <table>
                <tr>
                    <th>Search Way</th>
                    <td>
                        <select name="way">
                            <option value ="num">Search by Order Num:</option>
                            <option value ="date">Search by Date </option>
                        </select>
                    </td>
                   <td> <input type="submit" value="Confirm" ></td>
                </tr>
            </table>
        </form>
        
        <%
            String way = request.getParameter("way");
            if(way!=null){
        %>
        
        <form method="get" action="SearchOrderServlet">
            <table>
                <% if (way.equals("num")){ %>
                <tr>
                    <th>Search by name</th>
                    <td><input type="number" placeholder= "Enter Order Num"  name="num" required></td>
                    <td> <input type="submit" value="Search" > </td>
                </tr><%}else{%>
                <tr>
                    <th>select a date</th>
                    <td>
                        <input type="date" name="date" required>
                    </td>
                    <td> <input type="submit" value="Search" > </td>
                </tr><%}%>
            <table>
        </form> 
            <%}%>
       <% 
           LinkedList<Order> orders = (LinkedList<Order>) session.getAttribute("orders");
         
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
           </table>
       <%}%>
    
        </center>
    </body>
</html>
