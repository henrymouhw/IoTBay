<%-- 
    Document   : CRF
    Created on : 2021-4-11, 15:05:22
    Author     : Yangyulin Ai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Registration Page</title>
</head>
<body onload="startTime()">
    <div><span class="time" id="time"></span></div>
    <%
           String existErr = (String) session.getAttribute("existErr");
           String emailErr = (String) session.getAttribute("emailErr");
           String passErr = (String) session.getAttribute("passErr");
           String nameErr = (String) session.getAttribute("nameErr");
    %>
    
    <h1>Customer Registration Form<span class="message"> <%= (existErr != null ? existErr : "" )%> </span></h1>
     <a href="CancelServlet">Cancel</a>
    <form method="post" action="RegisterServlet">
        <table class="register_table">
            <tr>
                <td style="width: 20%">Customer Name: <span style="color: red">* </span></td>
                <td><input type="text" placeholder="<%=(nameErr != null ? nameErr : "Enter Name") %> " name="name" required></td>
            </tr>
            <tr>
                <td style="width: 20%">Email: <span style="color: red">* </span></td>
                <td><input type="text" placeholder="<%=(emailErr != null ? emailErr : "Enter Email") %> " name="email" required></td>
            </tr>
            <tr>
                <td style="width: 20%">Password: <span style="color: red">* </span></td>
                <td><input type="password" placeholder="<%=(passErr != null ? passErr : "Enter Password") %> " name="password" min="6" max="12" required><small style="color: green">(password should be between 6 and 12 characters)</small></td>
            </tr>
            <tr>
                <td style="width: 20%">Phone: <span style="color: red">* </span></td>
                <td><input type="tel" name="phone" placeholder="Enter Phone Number"></td>
            </tr>
            <tr>
                <td style="width: 20%">Gender: </td>
                <td><input type="radio" name="gender" value="female">female
                    <input type="radio" name="gender" value="male">male</td>
            </tr>
            <tr>
                <td style="width: 20%">Date of Birth: </td>
                <td><input type="date" name="dob" placeholder="Enter date of birth" required></td>
            </tr>
            <tr>
                <td><input type="hidden" name="type" value="Customer"></td> 
            </tr>
    </table>
        <div>
           
            <input class="button" type="submit" value="Sign Up">
        </div>
    </form>
</body>
</html>
