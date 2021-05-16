<%-- 
    Document   : addCustomer
    Created on : 2021年5月8日, 下午9:18:37
    Author     : lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Customer</title>
    
    </head>
    <body>
     
        <%
           String  emailErr = (String) session.getAttribute("emailErr");
           String existErr = (String) session.getAttribute("existErr");
           String nameErr = (String) session.getAttribute("nameErr");
           String success = (String) session.getAttribute("success");
        %>
        <h1>Add Customer</h1>
        <h1 style="color:red"> <%= (success==null)?"":success%></h1>
        <h1 style="color:red"><%=(existErr==null)?"":existErr%></h1>
        <form method="post" action="AddCustomerServlet">
        <table>
            <tr>
                <td>Customer Name:</td>
                <td><input type="text" placeholder= "<%= (nameErr!=null)? nameErr: "Enter Name"%>"  name="name" required></td>
            </tr>
            
            <tr>
                <td>Email: </td>
                <td><input type="email" placeholder="<%= (emailErr!=null)? emailErr:"Enter Email"%>" name="email" required></td>
            </tr>
            
           
            <tr>
                <td> Address </td>
                <td><input type="text" name="address" placeholder="Enter Address" required></td>
            </tr>
            
            <tr>
                <td>Customer Type: </td>
                <td><input type="radio" name="type" value="Company">Company
                    <input type="radio" name="type" value="Individual">Individual</td>
            </tr>
           
            <tr>
                <td>Status: </td>
                <td><input type="radio" name="status" value="active">active
                    <input type="radio" name="status" value="deactivate">deactivate</td>
            </tr>
         </table>
             <br>
              <a href="index.jsp" >Cancel</a>
             <input type="submit" value="Add" >
    </form>
    </body>
</html>
