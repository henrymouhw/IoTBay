<%-- 
    Document   : Welcome
    Created on : 2021-4-12, 15:29:03
    Author     : Yangyulin Ai
--%>

<%@page import="iot.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Information</title>
    </head>
    <body>
        <%
            String name = "";
            String email = "";
            String password = "";
            String phone = "";
            String gender = "";
            String dob = "";
            
            User currentUser = (User)session.getAttribute("user");
            if(currentUser.getUserType().equals("Customer"))
            {
                Customer user = (Customer)session.getAttribute("user");
                name = user.getUserName();
                email = user.getEmail();
                password = user.getPassword();
                phone = user.getUserPhoneNum();
                gender = user.getGender();
                dob = user.getDob();   
            }
            else
            {
                Staff user = (Staff)session.getAttribute("user");
                name = user.getUserName();
                email = user.getEmail();
                password = user.getPassword();
                phone = user.getUserPhoneNum();
                gender = user.getGender();
                dob = user.getDob();  
            }
        %>
        
       
        <div align="right">
            <a class="button" href="Logout.jsp"> Logout </a>
        </div>
        <center>
        <h1>Welcome <%= name %></h1>
        <p> Your name is: <%= name %></p>
        <p> Your email is: <%= email %></p>
        <p> Your password is: <%= password %></p>
        <p> Your phone number is: <%= phone %></p>
        <p> Your gender is: <%= gender %></p>
        <p> Your birthday is: <%= dob %></p>
         <a class="button" href="Edit.jsp">Update</a>
         <a class="button" href="Main.jsp">Main</a>
         </center>
    </body>
</html>