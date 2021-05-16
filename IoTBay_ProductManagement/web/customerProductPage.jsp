<%@page import="java.util.*;"%>
<%@page import="iot.models.*"%>
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
         <a href="Main.jsp">Back to Main Page</a>
        <form method="post" action="customerProductPage.jsp">
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
            <input type="hidden" name="Usertype" value="customer"/> 
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
        <% Order order = (Order)session.getAttribute("order");
            int num = 0;
            for (OrderItem item: order.getItems()){
                num = num + item.getQuantity();
            }
        %>
        <a href="Cart.jsp">Cart Detail</a>  Num: <%= num%>
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
                   <% 
                      int stock = p.getProductStock();
                      if(stock>0){
                   %>
                    <td><a href="AddToCartServlet?name=<%=p.getProductName()%>">Add to Cart</a></td> <%}else{%>
                    <td><span style="color: red">Sold out</span> </td> <%}%>
                </tr>
                <%}%>
        </table>
    </div>
    </form>
   
    </body>
</html>