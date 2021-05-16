

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="iot.models.Payment"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit</h1>
        <%
           Payment c = (Payment)session.getAttribute("Payment");
           String success = (String)session.getAttribute("updated");
           String outcomelErr = (String) session.getAttribute("outcomelErr");
           String nameErr = (String) session.getAttribute("nameErr");
        %>
        <form method="post" action="UpdatePaymentServlet">
        <table>
            <tr>
                <td>PaymentID:</td>
                <td><input type="text"   name="payment_id" value="<%=c.getPayment_id()%>" required>
                    <span>PaymentID: can not edit </span></span>
                </td>
            </tr>
            
            <tr>
                <td>Payment Method: </td>
                <td><input type="radio" name="paymethod" value="Credit Card" <%=c.getPaymethod().equals("Credit Card")?"checked":""%> >Credit Card
                    <input type="radio" name="paymethod" value="Debit Card" <%=c.getPaymethod().equals("Debit Card")?"checked":""%> >Debit Card</td>
            </tr>
            
            <tr>
                <td>Cardnumber: </td>
                <td><input type="text"  name="cardnumber:" value="<%=c.getCardnumber()%>" required>
                </td>
            </tr>
            
            <tr>
                <td>Expirydate </td>
                <td><input type="text"  name="expirydate" value="<%=c.getExpirydate()%>" required>
                </td>
            </tr>
            
            <tr>
                <td>Cvv </td>
                <td><input type="text"  name="cvv" value="<%=c.getCvv()%>" required>
                </td>
            </tr>
           
            <tr>
                <td>Fullname </td>
                <td><input type="text"  name="fullname" value="<%=c.getFullname()%>" required>
                </td>
            </tr>
            
          
         </table>
             <br>
              <a href="Main.jsp" >Cancel</a>
             <input type="submit" value="Update" >
    </form>
            <br>
         <h1><%= (success==null)?"":success%></h1>
         
    </body>
</html>
