<%@page import="iot.models.product"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product Page</title>
    </head>
    <body>
        <% 
            product Product = (product) session.getAttribute("productss");
            String updated = (String)session.getAttribute("updated");
            String nameError = (String) session.getAttribute("nameErr");
         %>
        <h1>Product Edit Page:<span> <%=(updated != null ? updated : "")%></span></h1>
        <form method="POST" action="updateProductServlet">
            <table>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="productName" value="<%=Product.getProductName()%>" placeholder="<%=(nameError != null ? nameError : "Enter name")%>"></td>
                </tr>
                <tr>
                    <td>Brand:</td>
                    <td><input type="text" name="productBrand" value="<%=Product.getProductBrand()%>"></td>
                </tr>
                <tr>
                    <td>Type:</td>
                    <td><input type="text" name="productType" value="<%=Product.getProductType()%>"></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><input type="text" name="productDescription" value="<%=Product.getProductDescription()%>"></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="productPrice" value="<%=Product.getProductPrice()%>"></td>
                </tr>
                <tr>
                    <td>Stock:</td>
                    <td><input type="text" name="productStock" value="<%=Product.getProductStock()%>"></td>
                </tr>
               
                 <tr>
                    <td>State: </td>
                    <td><input type="radio" name="state" value="selling" <%=Product.getState().equals("selling")?"checked":""%>>selling
                        <input type="radio" name="state" value="cancel"  <%=Product.getState().equals("Cancel")?"checked":""%>>cancelled</td>
                </tr>
                
                <tr><td><input type="submit" value="Update"></td></tr>
               
            </table>
        </form>
        <a href="Main.jsp">Back to Main Page</a>
    </body>
</html>
