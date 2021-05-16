<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product</title>
    </head>
    <body>
        <%
            String nameError = (String) session.getAttribute("nameErr");
            String existError = (String) session.getAttribute("existErr");
        %>
        <h1>Create Product Details</h1>
        <h1 style="color: red"><%=existError!=null? existError: ""%></h1>
        <form action="addProductServlet" method="POST">    
            <table>
            <tr>
                <td style="width: 25%"> Name: 
                <td><input type="text" placeholder="<%=(nameError != null ? nameError : "Enter product name")%>" name="productName"></td>
            </tr>
            <tr>
                <td> Brand: 
                <td><input type="text" placeholder="Enter product brand" name="productBrand"></td>
            </tr>
            <tr>
                <td> Type: 
                <td><input type="text" placeholder="Enter product type" name="productType"></td>
            </tr>
            <tr>
                <td> Description: 
                <td><input type="text" placeholder="Enter product description" name="productDescription"></td>
            </tr>
            <tr>
                <td> Price: 
                <td><input type="text" placeholder="Enter product price" name="productPrice"></td>
            </tr>
            <tr>
                <td> Stock: 
                <td><input type="text" placeholder="Enter product stock" name="productStock"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Submit"></td>
                <td><a href="productDetails.jsp">Cancel</a></td>
            </tr>
            </table>
        </form>
    </body>
</html>
