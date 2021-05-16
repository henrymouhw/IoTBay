<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="iot.models.product"%>
<%@page import="iot.models.dao.productDBManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
    </head>
    <body>       
        <h1>Product Details</h1>
        <a href="index.jsp">Back to Main Page</a>
        <h2><a href="addProduct.jsp">Add Product</a></h2>
        
        <form method="post" action="productDetails.jsp">
            <table>
                <tr>
                    <th>Search By</th>
                    <td>
                        <select name="search">
                            <option value="name">NAME</option>
                            <option value="type">TYPE</option>
                        </select>
                    </td>
                    <td><input type="submit" value="Confirm"></td>
                </tr>
            </table>
        </form>
        
        <%
            String search = request.getParameter("search");
            if(search != null) {
        %>
        <form method="get" action="productSearchServlet">
             <input type="hidden" name="Usertype" value="staff"/> 
            <table>
                <%if(search.equals("name")) {%>
                <tr>
                    <th>Search by name</th>
                    <td><input type="text" placeholder="Enter name" name="name" required></td>
                    <td><input type="submit" value="Confirm"></td>
                </tr><%}else{%>
                <tr>
                    <th>Search by type</th>
                    <td>
                        <select name="type">
                        <option value="Sensor">Sensor</option>
                        <option value="Switch">Switch</option>
                        <option value="Router">Router</option>
                        <option value="Data processing module">Data processing module</option>
                        </select>
                    </td>
                    <td><input type="submit" value="Confirm"></td>
                </tr>
                <%}%>
            </table>
        </form>
        <%}%>   
                
        <div align="center">
        <table border="1" cellpadding="5">
                <caption><h2>List of Products</h2></caption>
            <tr>
                <th>Name</th>
                <th>Brand</th>
                <th>Type</th>
                <th>Description</th>
                <th>Price</th>
                <th>Stock</th>
                <th>State</th>
                <th>Action</th>
            </tr>
            <%
                ArrayList<product> products = (ArrayList<product>)session.getAttribute("Products");
                
                for(product p: products) {
            %>
                <tr>
                    <td><%=p.getProductName()%></td>
                    <td><%=p.getProductBrand()%></td>
                    <td><%=p.getProductType()%></td>
                    <td><%=p.getProductDescription()%></td>
                    <td><%=p.getProductPrice()%></td>
                    <td><%=p.getProductStock()%></td>
                    <td><%=p.getState() %></td>
                    <td><a href="editProductServlet?name=<%=p.getProductName() %>">Edit </a>
                     <a href="productDeleteServlet?name=<%=p.getProductName() %>">Delete</a></td>
                </tr>
                <%}%>
        </table>
    </div>
    </form>
    
    </body>
</html>
