<%-- 
    Document   : EditCustRecord
    Created on : 2021年5月9日, 上午11:24:39
    Author     : lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iot.models.CustRecord"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit</h1>
        <%
           CustRecord c = (CustRecord)session.getAttribute("CustRecord");
           String success = (String)session.getAttribute("updated");
           String outcomelErr = (String) session.getAttribute("outcomelErr");
           String nameErr = (String) session.getAttribute("nameErr");
        %>
                <form method="post" action="UpdateCustRecordServlet">
        <table>
            <tr>
                <td>Customer Name:</td>
                <td><input type="text"   name="name" value="<%=c.getName()%>" required>
                    <span style="color: red"><%=(nameErr==null||nameErr.equals("Enter Name"))?"":nameErr%> </span>
                </td>
            </tr>
            
            <tr>
                <td>Email: </td>
                <td><input type="email"  name="email" value="<%=c.getEmail()%>" readonly>
                    <span style="color: red">Email can not edit </span>
                </td>
            </tr>
            
           
            <tr>
                <td> Address </td>
                <td><input type="text" name="address" value="<%=c.getAddress()%>" required></td>
            </tr>
          
            <tr>
                <td>Customer Type: </td>
                <td><input type="radio" name="type" value="Company" <%=c.getType().equals("Company")?"checked":""%> >Company
                    <input type="radio" name="type" value="Individual" <%=c.getType().equals("Individual")?"checked":""%> >Individual</td>
            </tr>
           
            <tr>
                <td>Status: </td>
                <td><input type="radio" name="status" value="active" <%=c.getStatus().equals("active")?"checked":""%> >active
                    <input type="radio" name="status" value="deactivate"  <%=c.getStatus().equals("deactivate")?"checked":""%> >deactivate</td>
            </tr>
         </table>
             <br>
              <a href="index.jsp" >Cancel</a>
             <input type="submit" value="Update" >
    </form>
            <br>
         <h1 style="color: red"><%= (success==null)?"":success%></h1>
         <h1 style="color: red"><%= (outcomelErr==null)?"":outcomelErr%></h1>
    </body>
</html>
