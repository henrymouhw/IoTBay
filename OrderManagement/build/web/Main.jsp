<%-- 
    Document   : Main
    Created on : 2021-4-12, 15:29:10
    Author     : Yangyulin Ai
--%>

<%@page import="iot.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body onload="startTime(); resetSearch();">
        <%
            //String type = (String)session.getAttribute("type");
            User user = (User)session.getAttribute("user");
            if(user.getUserType().equals("Customer"))
            {
                //user = (Customer)session.getAttribute("user");
            }
            else
            {
                //user = (Staff)session.getAttribute("user");
            }
        %>
       
        <div align="right">
        <a class="button" href="Logout.jsp"> Logout </a>
        </div>
        <center>
         <h1>Account Profile</h1>
         <a href = "PersonalInfo.jsp"> Back </a> 
        <form method="post" action="AccessLogServlet">
             <table border=1>
                <thread><th>Name</th><th>Email</th><th>Password</th><th>Phone</th><th>Gender</th><th>Date of Birth</th><th>Account Type</th> <th>view Log</th></thread>
                <tr><td>${user.userName}</td><td>${user.email}</td><td>${user.password}</td><td>${user.userPhoneNum}</td><td>${user.gender}</td><td>${user.dob}</td>             
              
                    <td><p> ${user.userType} </td>
                 
                    <td><input type="submit" value="Access Log"></td>
                </tr>
            </table>
          <input type="hidden" name="email" value=${user.email}>
        </form><br>
                <div>
            
                <% if (user.getUserType().equals("Staff")){ %>
              <a href="productsServlet?type=Staff">  IoT Device Catalogue (Collection) Management </a><br><br>
              <a href="customerInfoManagement.jsp"> Customer Infomation Management </a><br><br>
              <a href="staffInfoManagement.jsp"> Staff Infomation Management</a>
              
   
              <%}else if (user.getUserType().equals("Customer")) {%>
              <a href="productsServlet?type=Customer"> Products View</a><br><br>
              <a href="OrderListServlet?"> view my Orders </a><br><br>
              <a href="PaymentServlet?"> My Payment </a><br><br>
              <a href="ListPaymentServlet?"> Payment List</a>
              <%}%>
                </div>
                </center>
    </body>
</html>
