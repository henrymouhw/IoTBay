<%-- 
    Document   : CustomerList
    Created on : 2021年5月8日, 下午6:03:38
    Author     : lin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="iot.models.CustRecord"%>
<%@page import="java.util.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="CustomerList.jsp">
            <table>
                <tr>
                    <th>Search Way</th>
                    <td>
                        <select name="way">
                            <option value ="name">Search by name</option>
                            <option value ="type">Search by type </option>
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
        
        <form method="get" action="SearchCustServlet">
            <table>
                <% if (way.equals("name")){ %>
                <tr>
                    <th>Search by name</th>
                    <td><input type="text" placeholder= "Enter Name to Search"  name="name" required></td>
                    <td> <input type="submit" value="Search" > </td>
                </tr><%}else{%>
                <tr>
                    <th>Search by type</th>
                    <td>
                        <select name="type" required>
                            <option value="Company">Company</option>
                            <option value="Individual">Individual</option>
                        </select>
                    </td>
                    <td> <input type="submit" value="Search" > </td>
                </tr><%}%>
            <table>
        </form>
        
        <%}%>
        <% String outcomelErr = (String) session.getAttribute("outcomelErr");  %>
        <h1>
            <%= (outcomelErr==null)? "": outcomelErr%>
        </h1>
        <table border = 1>
            <tr> 
                <th>Email</th>
                <th>Name</th>
                <th>Type</th>
                <th>adress</th>
                <th>Status</th>
                <th>Operation 1</th>
                <th>Operation 2</th>
            </tr>
            <%   
                LinkedList<CustRecord> customers = (LinkedList<CustRecord>) session.getAttribute("CustRecords");
                
                for(CustRecord c: customers){
            %>
  
            <tr>
                <td> <%=c.getEmail()%></td>
                <td>  <%=c.getName()%> </td>
                <td> <%=c.getType()%></td>
                <td> <%=c.getAddress()%></td>
                <td> <%=c.getStatus()%></td>
                <td><a href="EditCustRecordServlet?email=<%=c.getEmail()%>">Edit</a></td>
                <td><a href="DeleteCustRecordServlet?email=<%=c.getEmail()%>">Delete</a></td>
            </tr>
            <%}%>
              </table>
        </table>
        <br>
        <a href="index.jsp">Back</a>
    </body>
</html>
