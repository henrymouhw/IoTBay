<%-- 
    Document   : searchPayment
    Created on : 2021-5-15, 6:26:54
    Author     : 74152
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="iot.models.Payment"%>
<%@page import="java.util.*"%>

<html>
    <head>
        <title>List Page</title>
    </head>
    <body>
        
           <h1>Payment List</h1> 
           <a href="Main.jsp">Back to Main</a>
         
        <form method="post" action="paymentList.jsp">
            <table>
                <tr>
                    <th>Search Way</th>
                    <td>
                        <select name="way">
                            <option value ="payment_id">Search by PaymentID</option>
                            <option value ="date">Search by Date </option>
                        </select>
                    </td>
                   <td> <input type="submit" value="Ok" ></td>
                </tr>
            </table>
        </form>
        
        <%
            String way = request.getParameter("way");
            if(way!=null){
        %>
        
        <form method="get" action="SearchPaymentServlet">
            <table>
                <% if (way.equals("payment_id")){ %>
                <tr>
                    <th>Search by PaymentID</th>
                    <td><input type="number" placeholder= "Enter PaymentID to Search"  name="payment_id" required></td>
                    <td><input type="submit" value="Search" > </td>
                </tr><%}else{%>
                <tr>
                    <th>Search by Date</th>
                    <td><input type="date"   name="date" required></td>
                    <td><input type="submit" value="Search" > </td>
                </tr><%}%>
            <table>
        </form>
        
        <%}%>
        <table border = 1>
            <tr> 
                <th>PaymentID</th>
                <th>Paymethod</th>
                <th>Cardnumber</th>
                <th>Expirydate</th>
                <th>Cvv</th>
                <th>Fullname</th>
                <th>Date</th>
            </tr>
            <%   
                LinkedList<Payment> detials = (LinkedList<Payment>) session.getAttribute("Payments");
                if(detials!=null){
                for(Payment c: detials){
            %>
  
            <tr>
                <td> <%=c.getPayment_id()%></td>
                <td> <%=c.getPaymethod()%> </td>
                <td> <%=c.getCardnumber()%></td>
                <td> <%=c.getExpirydate()%></td>
                <td> <%=c.getCvv()%></td>
                <td> <%=c.getFullname()%></td>
                <td> <%=c.getDate()%></td>
                <td><a href="EditPaymentServlet?payment_id=<%=c.getPayment_id()%>">Update</a></td>
                <td><a href="DeletePaymentServlet?payment_id=<%=c.getPayment_id()%>">Delete</a></td>
             </tr><%}%>
             <%}%>
        </table>
    </body>
</html>

